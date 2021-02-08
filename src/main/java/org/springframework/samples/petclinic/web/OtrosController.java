package org.springframework.samples.petclinic.web;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OtrosController {
	private final OtrosService OtrosService;

	private final IngredienteService IngredienteService;
	
	@Autowired
	public OtrosController(OtrosService OtroService, IngredienteService IngredienteService) {
		this.OtrosService = OtroService;
		this.IngredienteService = IngredienteService;
	}
	
	@InitBinder("otro")
	public void initBebidaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new OtrosValidator());
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	//crear nuevo Otros
	@GetMapping(value = "/Otros/new")
	public String initCreationForm( Map<String, Object> model) {
		Otro otro = new Otro();
		model.put("otro", otro);
		
		return "Otros/createOrUpdateOtrosForm";
	}

	//mandar nuevo Otros
	@PostMapping(value = "/Otros/new")
	public String processCreationForm(@Valid Otro otros, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("otro", otros);
			log.warn("Fallos en la creacion de otro");	
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			this.OtrosService.saveOtros(otros);
			log.info("Otro guardado");
			return "redirect:/allOtros";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/Otros/{OtrosId}/edit")
	public String initUpdateForm(@PathVariable("OtrosId") int OtrosId, ModelMap model) {
		Otro otro = this.OtrosService.findOtrosById(OtrosId);
		model.put("otro", otro);
		
		return "Otros/createOrUpdateOtrosForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/Otros/{OtrosId}/edit")
	public String processUpdateOtrosForm(@Valid Otro otros, BindingResult result,
			@PathVariable("OtrosId") int OtrosId) {
		if (result.hasErrors()) {
			log.warn("Fallos en la actualizacion de otro");
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			otros.setId(OtrosId);
			this.OtrosService.saveOtros(otros);
			log.info("Otro actualizado");
			return "redirect:/allOtros";
		}
	}
	 
}