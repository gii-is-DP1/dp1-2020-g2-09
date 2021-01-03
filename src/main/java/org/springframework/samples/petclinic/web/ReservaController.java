package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.ReservaService;
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
	
	@Autowired
	public ReservaController(ReservaService reservaService, MesaService mesaService) {
		this.reservaService = reservaService;
		this.mesaService = mesaService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("reserva")
	public void initReservaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReservaValidator());
	}
	
	@GetMapping(value = { "/allReservas" })
	public String showReservaList(Map<String, Object> model) {
		List<Reserva> reservas=reservaService.findReservas();
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
			this.reservaService.saveReserva(reserva);
			return "redirect:/allReservas";
		}
	}
	
	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles")
	public String mesasDisponibles(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findById(reservaId);
		Integer numPersonas = reserva.getNumeroPersonas();
		List<Mesa> mesas = this.mesaService.findMesas();
		List<Mesa> mesasPorCapacidad = new ArrayList<Mesa>();
		for(Mesa m: mesas) {
			//Si la capacidad de la mesa es mayor que el número de personas 
			// 
			if(m.getCapacidad()>=numPersonas /* y que para esta mesa ver si 
			hay alguna reserva y en ese caso que estén diferenciadas 40 min por ej*/) {
				mesasPorCapacidad.add(m);
			}
			
		}
		
		model.put("mesasPorCapacidad", mesasPorCapacidad);
		return "mesas/mesasDisponibles";
	}
	
	@GetMapping(value = "/reservas/{reservaId}/allMesasDisponibles/{mesaId}")
	public String anadirMesaAReserva(@PathVariable("reservaId") int reservaId, @PathVariable("mesaId") int mesaId, ModelMap model) {
		model.put("reserva", reservaId);
		this.reservaService.anadirMesaAReserva(reservaId, mesaId);
		return "redirect:/allReservas";
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
