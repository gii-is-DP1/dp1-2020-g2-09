package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Bebidas;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BebidaController {

	private final BebidaService bebidaService;

	@Autowired
	public BebidaController(BebidaService bebidaService) {
		this.bebidaService = bebidaService;
	}

	@InitBinder("bebida") 
	public void initBebidaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new BebidaValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = { "/allBebidas" })
	public String showBebidasList(Map<String, Object> model) {
		Bebidas bebidas = new Bebidas();
		bebidas.getBebidasList().addAll(this.bebidaService.findBebidas());
		model.put("bebidas", bebidas);
		log.info("Mostrando bebidas");
		return "bebidas/bebidasList";
	}

	// crear nuevo Bebida
	@GetMapping(value = "/bebidas/new")
	public String initCreationForm(Map<String, Object> model) {
		Bebida bebida = new Bebida();
		model.put("bebida", bebida);
		log.info("Iniciando creacion de bebida");
		return "bebidas/createOrUpdateBebidaForm";
	}

	// mandar nuevo Bebida
	@PostMapping(value = "/bebidas/new")
	public String processCreationForm(@Valid Bebida bebida, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("bebida", bebida);//importanteeee
			log.error("Error en la creacion de una bebida");
			return "bebidas/createOrUpdateBebidaForm";
		} else {
//			BebidaValidator bebidaValidator = new BebidaValidator();
//			ValidationUtils.invokeValidator(bebidaValidator, bebida, result);
			this.bebidaService.saveBebida(bebida);
			log.info("Bebida guardada");
			return "redirect:/allBebidas";
		}
	}

	// iniciar actualizacion
	@GetMapping(value = "/bebidas/{bebidaId}/edit")
	public String initUpdateForm(@PathVariable("bebidaId") int bebidaId, ModelMap model) {
		Bebida bebida = this.bebidaService.findById(bebidaId);
		model.put("bebida", bebida);
		log.info("Iniciando actualizacion de bebida");
		return "bebidas/createOrUpdateBebidaForm";
	}

	// mandar actualizacion
	@PostMapping(value = "/bebidas/{bebidaId}/edit")
	public String processUpdateBebidaForm(@Valid Bebida bebida, BindingResult result,  ModelMap model,
			@PathVariable("bebidaId") int bebidaId, @RequestParam(value = "version", required=false) Integer version) {
		
		Bebida bebidaToUpdate=this.bebidaService.findById(bebidaId);
		if(bebidaToUpdate.getVersion()!=version) {
		model.put("message","Problema de concurrencia a la hora de editar la bebida. "
				+ "Inténtelo de nuevo más tarde.");
		log.info("Problema de concurrencia a la hora de editar la bebida.");
		return initUpdateForm(bebidaId,model);
		}
		
		if (result.hasErrors()) {
			log.error("Error en la actualizacion de bebida");
			return "bebidas/createOrUpdateBebidaForm";
		} else {
			bebida.setId(bebidaId);
//			BebidaValidator bebidaValidator = new BebidaValidator();
//			ValidationUtils.invokeValidator(bebidaValidator, bebida, result);
			this.bebidaService.saveBebida(bebida);
			log.info("Bebida actualizada");
			return "redirect:/allBebidas";
		}
	}

	// borrar Bebida
	@GetMapping(value = "/bebidas/{bebidaId}/delete")
	public String initDeleteBebida(@PathVariable("bebidaId") int bebidaId, ModelMap model) {
		Bebida bebida = this.bebidaService.findById(bebidaId);
		this.bebidaService.deleteBebida(bebida);
		log.info("Bebida borrada");
		return "redirect:/allBebidas";
	}


}
