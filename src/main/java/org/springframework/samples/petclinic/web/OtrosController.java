package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OtrosController {
	
	private final OtrosService OtrosService;

	@Autowired
	public OtrosController(OtrosService OtroService) {
		this.OtrosService = OtroService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allOtros" })
	public String showOtrosList(Map<String, Object> model) {
		List<Otros> Otros = new ArrayList<Otros>();
		Otros.addAll(this.OtrosService.findOtros());
		model.put("Otros", Otros);
		return "Otros/OtrosList";
	}

	//crear nuevo Otros
	@GetMapping(value = "/Otros/new")
	public String initCreationForm(Map<String, Object> model) {
		Otros Otros = new Otros();
		model.put("Otros", Otros);
		
		return "Otros/createOrUpdateOtrosForm";
	}

	//mandar nuevo Otros
	@PostMapping(value = "/Otros/new")
	public String processCreationForm(@Valid Otros Otros, BindingResult result) {
		if (result.hasErrors()) {
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			this.OtrosService.saveOtros(Otros);
			return "redirect:/allOtros";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/Otros/{OtrosId}/edit")
	public String initUpdateForm(@PathVariable("OtrosId") int OtrosId, ModelMap model) {
		Otros Otros = this.OtrosService.findOtrosById(OtrosId);
		model.put("Otros", Otros);
		
		return "Otros/createOrUpdateOtrosForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/Otros/{OtrosId}/edit")
	public String processUpdateOtrosForm(@Valid Otros Otros, BindingResult result,
			@PathVariable("OtrosId") int OtrosId) {
		if (result.hasErrors()) {
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			Otros.setId(OtrosId);
			this.OtrosService.saveOtros(Otros);
			return "redirect:/allOtros";
		}
	}
	
	//borrar Otros
	@GetMapping(value = "/Otros/{OtrosId}/delete")
	public String initDeleteOtros(@PathVariable("OtrosId") int OtrosId, ModelMap model) {
		Otros Otros = this.OtrosService.findOtrosById(OtrosId);
		this.OtrosService.deleteOtros(Otros);
		return "redirect:/allOtros";
	}
	
	
}