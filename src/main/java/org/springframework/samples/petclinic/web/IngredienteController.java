package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredienteController {
	
	private final IngredienteService IngredienteService;

	@Autowired
	public IngredienteController(IngredienteService IngredienteService) {
		this.IngredienteService = IngredienteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("Ingrediente")
	public void initPedidoBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new IngredienteValidator());
	}
	
	@GetMapping(value = { "/allIngredientes" })
	public String showIngredienteList(Map<String, Object> model) {
		List<Ingrediente> Ingredientes = new ArrayList<Ingrediente>();
		Ingredientes.addAll(this.IngredienteService.findIngredientes());
		model.put("Ingredientes", Ingredientes);
		log.info("Mostrando ingredientes");
		return "Ingredientes/ingredientesList";
	}

	//crear nuevo Ingrediente
	@GetMapping(value = "/Ingredientes/new")
	public String initCreationForm(Map<String, Object> model) {
		Ingrediente Ingrediente = new Ingrediente();
		model.put("Ingrediente", Ingrediente);
		
		return "Ingredientes/createOrUpdateIngredienteForm";
	}

	//mandar nuevo Ingrediente
	@PostMapping(value = "/Ingredientes/new")
	public String processCreationForm(@Valid Ingrediente Ingrediente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("Ingrediente", Ingrediente);
			log.warn("Fallo en la creacion de ingrediente");
			return "Ingredientes/createOrUpdateIngredienteForm";
		}
		else {
			this.IngredienteService.saveIngrediente(Ingrediente);
			log.info("Guardar ingrediente");
			return "redirect:/allIngredientes";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/Ingredientes/{IngredienteId}/edit")
	public String initUpdateForm(@PathVariable("IngredienteId") int IngredienteId, ModelMap model) {
		Ingrediente Ingrediente = this.IngredienteService.findIngredienteById(IngredienteId);
		model.put("Ingrediente", Ingrediente);
		
		return "Ingredientes/createOrUpdateIngredienteForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/Ingredientes/{IngredienteId}/edit")
	public String processUpdateIngredienteForm(@Valid Ingrediente Ingrediente, BindingResult result,
			@PathVariable("IngredienteId") int IngredienteId) {
		if (result.hasErrors()) {
			log.warn("Fallo en la actualizacion de ingrediente");
			return "Ingredientes/createOrUpdateIngredienteForm";
		}
		else {
			Ingrediente.setId(IngredienteId);
			IngredienteValidator ingrValidator = new IngredienteValidator();
			ValidationUtils.invokeValidator(ingrValidator, Ingrediente, result);
			this.IngredienteService.saveIngrediente(Ingrediente);
			log.info("Ingrediente actualizado");
			return "redirect:/allIngredientes";
		}
	}
	

}