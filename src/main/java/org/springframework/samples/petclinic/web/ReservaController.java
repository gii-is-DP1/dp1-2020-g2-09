package org.springframework.samples.petclinic.web;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.Reservas;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReservaController {
	
	
	private  ReservaService reservaService;

	private MesaService mesaService;
	private UserService userService;
	private ClienteService clienteService;
	@Autowired
	public ReservaController(ReservaService reservaService, MesaService mesaService, UserService userService, ClienteService clienteService) {
		this.reservaService = reservaService;
		this.mesaService = mesaService;
		this.userService = userService;
		this.clienteService = clienteService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("reserva")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReservaValidator());
	}
	
	private Cliente getClienteActivo() {
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        User usuario = this.userService.findUser(userName).get();
        Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
        return  (Cliente) cliente;
	}
	
	@GetMapping(value = { "/allReservas" })
	public String showReservaList(Map<String, Object> model) {
		Clientes clientes = new Clientes();
		List<Cliente> listaClientes = clientes.getClientesList();
		List<Reserva> reservas=reservaService.findReservas();
		for(Reserva r: reservas) {
			Cliente cliente = r.getCliente();
			listaClientes.add(cliente);
		}
		log.info("Mostrando listado de reservas...");
		model.put("listaClientes", listaClientes);
		model.put("reservas", reservas); 
		return "reservas/reservasList";
	}

	@GetMapping(value = "/reservas/new")
	public String initCreationForm(Map<String, Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	
	@PostMapping(value = "/reservas/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("reserva",reserva);
			log.warn("Error a la hora de crear una reserva.");
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			Cuenta cliente = getClienteActivo();
			reserva.setCliente((Cliente) cliente);
			this.reservaService.saveReserva(reserva);
			log.info("Reserva creada correctamente.");
			return "redirect:/reservas/" + String.valueOf(reserva.getId()) + "/allMesasDisponibles";
		}
	}
	

	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles")
	public String mesasDisponibles(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("miReserva", reserva);
		Integer numPersonas = reserva.getNumeroPersonas();
		
		List<Mesa> mesas = this.mesaService.findMesas();
		List<Mesa> mesasDisponibles = new ArrayList<Mesa>();
		 
		for(Mesa m: mesas) { 
			if(m.getCapacidad()>=numPersonas) {
				List<Integer> reservasId = this.reservaService.findReservasIdByMesaId(m.getId());
				if(reservasId.size()==0) {
					mesasDisponibles.add(m); 
					
				} else {
					List<Reserva> reservas = this.reservaService.calcularReservasAPartirIds(reservasId);
					List<Reserva> reservasFechaMiReserva = reservas.stream().filter(r->r.getFechaReserva().
							equals(reserva.getFechaReserva())).collect(Collectors.toList());
					
					if(reservasFechaMiReserva.size()==0) {
						mesasDisponibles.add(m);
					} else if(reservasFechaMiReserva.size()==1) {
						if(this.reservaService.unaHoraEntreReservas(reserva.getHora(), reservasFechaMiReserva.get(0).getHora())){
							mesasDisponibles.add(m);
					}
					else if(reservasFechaMiReserva.size()>1) {
						Boolean horaEntreReservas = true;
					for(Reserva r: reservasFechaMiReserva) {
						if(!this.reservaService.unaHoraEntreReservas(reserva.getHora(), r.getHora()) && horaEntreReservas) {
							horaEntreReservas = false;
						}	
							}
					if(horaEntreReservas)
						mesasDisponibles.add(m);
						}
					}
				}
			}
		}
		model.put("mesasDisponibles", mesasDisponibles);
		log.info("Mostrando listado de mesas disponibles para reservar.");
		return "mesas/mesasDisponibles";
	}

	
		
	
	@GetMapping(value ="/reservas/{reservaId}/verDetalles")
	public String detallesReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
		
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("reserva", reserva);
		Cliente cliente = reserva.getCliente();
		User usuario = cliente.getUser();
		model.put("usuario", usuario);
		model.put("cliente", cliente);
		
		Integer mesaId = this.mesaService.findIdMesaByReserva(reservaId);
		Mesa mesa = this.mesaService.findById(mesaId);
		model.put("mesa", mesa);
		log.info("Mostrando detalles de la reserva seleccionada.");
		return "reservas/verDetallesReserva";
	}
	
	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles/{mesaId}")
	public String anadirMesaAReserva(@PathVariable("reservaId") int reservaId, @PathVariable("mesaId") int mesaId, ModelMap model) {
		model.put("reserva", reservaId);
		this.reservaService.anadirMesaAReserva(reservaId, mesaId);
		log.info("Mesa reservada.");
		return "redirect:/reservas/user";
	}
	
	@GetMapping("/reservas/user")
	public String showMisReservas(Map<String, Object> model) {
		Reservas reservas = new Reservas();
		Cliente cliente = getClienteActivo();
		Integer clienteId = cliente.getId();
	    reservas.getReservasList().addAll(this.reservaService.findReservasByCliente(clienteId));
		model.put("reservas", reservas);
		log.info("Mostrando reservas del usuario que ha iniciado sesión.");
		return "reservas/reservaUser";
	} 
	

	@GetMapping(value = "/reservas/{reservaId}/edit")
	public String initUpdateForm(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	
	@PostMapping(value = "/reservas/{reservaId}/edit")
	public String processUpdateReservaForm(@Valid Reserva reserva, BindingResult result,
			@PathVariable("reservaId") int reservaId,ModelMap model) {
		if (result.hasErrors()) {
			reserva.setId(reservaId);
			model.put("reserva",reserva);
			log.warn("Error a la hora de actualizar los datos de una reserva.");
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			reserva.setId(reservaId);
			this.reservaService.saveReserva(reserva);
			log.info("Reserva actualizada.");
			return "redirect:/reservas/{reservaId}/allMesasDisponibles";
		}
	}
	
	@GetMapping(value = "/reservas/{reservaId}/delete")
	public String initDeleteReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		this.reservaService.deleteReserva(reserva);
		log.info("Reserva eliminada.");
		return "welcome";
	}
	
		
	@ModelAttribute("tipoReserva")
    public Collection<tipoReserva> populateTipoReserva() {
        return this.reservaService.findTipoReserva();
    }
}
