package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.Cocinas;
import org.springframework.samples.petclinic.service.CocineroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CocineroController {

	
	private final CocineroService cocineroService;

	@Autowired
	public CocineroController(CocineroService cocineroService) {
		this.cocineroService = cocineroService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allCocineros" })
	public String showCocineroList(Map<String, Object> model) {
		Cocinas cocineros = new Cocinas();
		cocineros.getCocinerosList().addAll(this.cocineroService.findCocineros());
		model.put("cocineros", cocineros);
		return "cocineros/cocinerosList";
	}

	//crear nuevo cocinero
	@GetMapping(value = "/cocineros/new")
	public String initCreationForm(Map<String, Object> model) {
		Cocina cocina = new Cocina();
		model.put("cocinero", cocina);
		return "cocineros/createOrUpdateCocinaForm";
	}

	//mandar nuevo cocinero
	@PostMapping(value = "/cocineros/new")
	public String processCreationForm(@Valid Cocina cocinero, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("cocinero", cocinero);//importanteeee
			return "cocineros/createOrUpdateCocinaForm";
		}
		else {
			cocinero.setFechaInicioContrato(LocalDate.now());
			CocineroValidator cocineroValidator = new CocineroValidator();
			ValidationUtils.invokeValidator(cocineroValidator, cocinero, result);
			this.cocineroService.saveCocinero(cocinero);
			return "redirect:/allCocineros";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/cocineros/{cocineroId}/edit")
	public String initUpdateForm(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		Cocina cocinero = this.cocineroService.findCocineroById(cocineroId);
		model.put("cocinero", cocinero);
		return "cocineros/createOrUpdateCocinaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/cocineros/{cocineroId}/edit")
	public String processUpdateCocineroForm(@Valid Cocina cocinero, BindingResult result,
			@PathVariable("cocineroId") int cocineroId) {
		if (result.hasErrors()) {
			return "cocineros/createOrUpdateCocinaForm";
		}
		else {
			cocinero.setId(cocineroId);
			CocineroValidator cocineroValidator = new CocineroValidator();
			ValidationUtils.invokeValidator(cocineroValidator, cocinero, result);
			this.cocineroService.saveCocinero(cocinero);
			return "redirect:/allCocineros";
		}
	}
	
	//borrar cocinero
	@GetMapping(value = "/cocineros/{cocineroId}/delete")
	public String initDeleteCuenta(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		Cocina cocinero = this.cocineroService.findCocineroById(cocineroId);
		this.cocineroService.deleteCocinero(cocinero);
		return "redirect:/allCocineros";
	}
	
	//Alta y baja
	@GetMapping(value = "/cocineros/{cocineroId}/altaobaja")
	public String darAltayBaja(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		Cocina cocinero = this.cocineroService.findCocineroById(cocineroId);
		if(cocinero.getFechaFinContrato()!=null) {
			cocinero.setFechaInicioContrato(LocalDate.now());
			cocinero.setFechaFinContrato(null);
		}else {
			cocinero.setFechaFinContrato(LocalDate.now());
		}
		this.cocineroService.saveCocinero(cocinero);
		return "redirect:/allCocineros";
	}
}
