package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Mesas;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MesaController {

	
	private final MesaService mesaService;

	@Autowired
	public MesaController(MesaService mesaService) {
		this.mesaService = mesaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allMesas" })
	public String showMesaList(Map<String, Object> model) {
		Mesas mesas = new Mesas();
		mesas.getMesasList().addAll(this.mesaService.findMesas());
		model.put("mesas", mesas);
		return "mesas/mesasList";
	}

	//añadir una mesa nueva
	@GetMapping(value = "/mesas/new")
	public String initCreationForm(Map<String, Object> model) {
		Mesa mesa = new Mesa();
		model.put("mesa", mesa);
		return "mesas/createOrUpdateMesaForm";
	}

	//mandar nueva mesa
	@PostMapping(value = "/mesas/new")
	public String processCreationForm(@Valid Mesa mesa, BindingResult result) {
		if (result.hasErrors()) {
			return "mesas/createOrUpdateMesaForm";
		}
		else {
			this.mesaService.saveMesa(mesa);
			return "redirect:/allMesas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/mesas/{mesaId}/edit")
	public String initUpdateForm(@PathVariable("mesaId") int mesaId, ModelMap model) {
		Mesa mesa = this.mesaService.findMesaById(mesaId);
		model.put("mesa", mesa);
		return "mesas/createOrUpdateMesaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/mesas/{mesaId}/edit")
	public String processUpdateMesaForm(@Valid Mesa mesa, BindingResult result,
			@PathVariable("mesaId") int mesaId) {
		if (result.hasErrors()) {
			return "mesas/createOrUpdateMesaForm";
		}
		else {
			mesa.setId(mesaId);
			this.mesaService.saveMesa(mesa);
			return "redirect:/allMesas";
		}
	}
	
	//borrar mesa
	@GetMapping(value = "/mesas/{mesaId}/delete")
	public String initDeleteMesa(@PathVariable("mesaId") int mesaId, ModelMap model) {
		Mesa mesa = this.mesaService.findMesaById(mesaId);
		this.mesaService.deleteMesa(mesa);
		return "redirect:/allMesas";
	}
	
//	@ModelAttribute("mesa")
//	public Mesa findMesa(@PathVariable("mesaId") int mesaId) {
//		return this.findMesa(mesaId);
//	}

}