package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservaController {
	
	
	private  ReservaService reservaService;

	//@Autowired
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
	
	public Cuenta getClienteActivo() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
	    User usuario = this.userService.findUser(userName).get();
	    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
	    return cliente;
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
		model.put("listaClientes", listaClientes);
		model.put("reservas", reservas); //reservas por queeeeeeeeeeeeeeeeeeeeeeeeee
		return "reservas/reservasList";
	}

	//crear nueva reserva
	@GetMapping(value = "/reservas/new")
	public String initCreationForm(Map<String, Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	//Creo una reserva -> Me lleva a un listado de mesas disponibles según número de personas 
	// -> selecciono la mesa -> y entonces ahí asocio mesa y reserva y creo la reserva
	//mandar nueva reserva
	@PostMapping(value = "/reservas/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("reserva",reserva);
			return "reservas/createOrUpdateReservaForm";
		}
		else {
//			ReservaValidator reservaValidator = new ReservaValidator();
//			ValidationUtils.invokeValidator(reservaValidator, reserva, result);
			Cuenta cliente = getClienteActivo();
			
			//Asigno el cliente que ha hecho la reservas
			reserva.setCliente((Cliente) cliente);
			this.reservaService.saveReserva(reserva);
			return "redirect:/reservas/" + String.valueOf(reserva.getId()) + "/allMesasDisponibles";
		}
	}
	
	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles")
	public String mesasDisponibles(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("miReserva", reserva);
		Integer numPersonas = reserva.getNumeroPersonas();
		List<Mesa> mesas = this.mesaService.findMesas();
		List<Mesa> mesasPorCapacidad = new ArrayList<Mesa>();
		for(Mesa m: mesas) {
			//Si la capacidad de la mesa es mayor que el número de personas 
			// 
			if(m.getCapacidad()>=numPersonas /* y que para esta mesa ver si 
			hay alguna reserva y en ese caso que estén diferenciadas 1h por ej*/
					/*y que la reserva */) {
				mesasPorCapacidad.add(m);
			}
			
		}
		
		model.put("mesasPorCapacidad", mesasPorCapacidad);
		return "mesas/mesasDisponibles";
	}
	//Por un lado, voy a necesitar algunos datos del cliente -> tomo el cliente a partir del atributo reservacliente 
	//Por otro lado, voy a necesitar algunos datos de la mesa -> coger la mesa a partir del id de la reserva (buscar en la tabla intermedia)
	@GetMapping(value ="/reservas/{reservaId}/verDetalles")
	public String detallesReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
		
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("reserva", reserva);
		
		//Tomo el cliente. DEP esto no funciona -> una consulta a la tabla:
		//Selecciono el reservacliente (clave ajena de cliente) de la tabla reservas donde el id de la reserva sea reservaId 
		//A continuacion selecciono el cliente a partir del reservacliente
		Cliente cliente = reserva.getCliente();
		User usuario = cliente.getUser();
		model.put("usuario", usuario);
		model.put("cliente", cliente);
		
		//Tomo la mesa asociada a la reserva
		Integer mesaId = this.mesaService.findIdMesaByReserva(reservaId);
		Mesa mesa = this.mesaService.findById(mesaId);
		model.put("mesa", mesa);
		
		return "reservas/verDetallesReserva";
	}
	
	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles/{mesaId}")
	public String anadirMesaAReserva(@PathVariable("reservaId") int reservaId, @PathVariable("mesaId") int mesaId, ModelMap model) {
		model.put("reserva", reservaId);
		this.reservaService.anadirMesaAReserva(reservaId, mesaId);
		return "redirect:/allReservas";
	}
	
	//RESERVAS DE UN CLIENTE QUE HA INICIADO SESIÓN
	@GetMapping("/reservas/user")
	public String showMisReservas(Map<String, Object> model) {
		Reservas reservas = new Reservas();
		Cuenta cliente = getClienteActivo();
		Integer clienteId = cliente.getId();
	    reservas.getReservasList().addAll(this.reservaService.findReservasByCliente(clienteId));
		model.put("reservas", reservas);
		return "reservas/reservaUser";
	} 
	

	
//	//AÑADIR MESA A UNA RESERVA 
//		@GetMapping("/reservas/{reservaId}/anadirMesaAReserva/{mesaId}")
//		public String anadirMesa(Map<String, Object> model, @PathVariable("reservaId") int reservaId,
//					@PathVariable("mesaId") int mesaId) {
//			this.reservaService.anadirMesaAReserva(reservaId, mesaId);
//			return "redirect:/allReservas";
//		}

	//iniciar actualizacion
	@GetMapping(value = "/reservas/{reservaId}/edit")
	public String initUpdateForm(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/reservas/{reservaId}/edit")
	public String processUpdateReservaForm(@Valid Reserva reserva, BindingResult result,
			@PathVariable("reservaId") int reservaId,ModelMap model) {
		if (result.hasErrors()) {
			model.put("reserva",reserva);
			return "reservas/createOrUpdateReservaForm";
		}
		else {
//			ReservaValidator reservaValidator = new ReservaValidator();
//			ValidationUtils.invokeValidator(reservaValidator, reserva, result);
			reserva.setId(reservaId);
			this.reservaService.saveReserva(reserva);
			return "redirect:/allReservas";
		}
	}
	
	//borrar reserva
	@GetMapping(value = "/reservas/{reservaId}/delete")
	public String initDeleteReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		this.reservaService.deleteReserva(reserva);
		return "redirect:/allReservas";
	}
	//buscar mesas de la reserva
		@GetMapping(value = "/reservas/mesas/{reservaId}")
		public String initReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
			List<Mesa> lista= mesaService.findByReserva(reservaId);
			model.put("mesas", lista);
			return "redirect:/allReservas";
		}
		

	@DeleteMapping(value = "/reserva/{reservaId}/delete")
	public String deleteReserva(@PathVariable("reservaId") int reservaId) {
		Reserva reserva = this.reservaService.findById(reservaId);
		this.reservaService.deleteReserva(reserva);
		return "redirect:/allReservas";
	}
	@ModelAttribute("tipoReserva")
    public Collection<tipoReserva> populateTipoReserva() {
        return this.reservaService.findTipoReserva();
    }
}
