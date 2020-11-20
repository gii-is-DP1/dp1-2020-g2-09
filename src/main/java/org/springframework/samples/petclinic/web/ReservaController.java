package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.Reservas;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservaController {
	
	private final ReservaService reservaService;

	@Autowired
	public ReservaController(ReservaService reservaService) {
		this.reservaService = reservaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allReservas" })
	public String showReservaList(Map<String, Object> model) {
		Reservas reservas = new Reservas();
		reservas.getReservasList().addAll(this.reservaService.findReservas());
		model.put("reservas", reservas);
		return "reservas/reservasList";
	}

	//crear nueva reserva
	@GetMapping(value = "/reservas/new")
	public String initCreationForm(Map<String, Object> model) {
		Reserva reserva = new Reserva();
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}

	//mandar nueva reserva
	@PostMapping(value = "/reservas/new")
	public String processCreationForm(@Valid Reserva reserva, BindingResult result) {
		if (result.hasErrors()) {
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			this.reservaService.saveReserva(reserva);
			return "redirect:/allReservas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/reserva/{reservaId}/edit")
	public String initUpdateForm(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findReservaById(reservaId);
		model.put("reserva", reserva);
		return "reservas/createOrUpdateReservaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/reservaId/{reservaId}/edit")
	public String processUpdateReservaForm(@Valid Reserva reserva, BindingResult result,
			@PathVariable("reservaId") int reservaId) {
		if (result.hasErrors()) {
			return "reservas/createOrUpdateReservaForm";
		}
		else {
			reserva.setId(reservaId);
			this.reservaService.saveReserva(reserva);
			return "redirect:/allReservas";
		}
	}
	
	//borrar reserva
	@GetMapping(value = "/reservas/{reservaId}/delete")
	public String initDeleteReserva(@PathVariable("reservaId") int reservaId, ModelMap model) {
		Reserva reserva = this.reservaService.findReservaById(reservaId);
		this.reservaService.deleteReserva(reserva);
		return "redirect:/allReservas";
	}

//	@DeleteMapping(value = "/reserva/{reservaId}/delete")
//	public String deleteReserva(@PathVariable("reservaId") int reservaId) {
//		Reserva reserva = this.reservaService.findReservaById(reservaId);
//		this.reservaService.deleteReserva(reserva);
//		return "redirect:/allReservas";
//	}
}
