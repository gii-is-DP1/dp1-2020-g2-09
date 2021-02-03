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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CocineroController {

	
	private final CocineroService cocineroService;

	@Autowired
	public CocineroController(CocineroService cocineroService) {
		this.cocineroService = cocineroService;
	}

	@InitBinder("cocina")
	public void initCocinaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CocineroValidator());
	}
	
	/*@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}*/
	
	@GetMapping(value = { "/allCocineros" })
	public String showCocineroList(Map<String, Object> model) {
		Cocinas cocinas = new Cocinas();
		cocinas.getCocinerosList().addAll(this.cocineroService.findCocineros());
		model.put("cocinas", cocinas);
		log.info("Mostrando cocineros");
		return "cocineros/cocinerosList";
	}

	//crear nuevo cocinero
	@GetMapping(value = "/cocineros/new")
	public String initCreationForm(Map<String, Object> model) {
		Cocina cocina = new Cocina();
		model.put("cocina", cocina);
		log.info("Iniciar creacion de un cocinero");
		return "cocineros/createOrUpdateCocinaForm";
	}

	//mandar nuevo cocinero
	@PostMapping(value = "/cocineros/new")
	public String processCreationForm(@Valid Cocina cocina, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("cocina", cocina);//importanteeee
			log.error("Fallo en la creacion de un cocinero");
			return "cocineros/createOrUpdateCocinaForm";
		}
		else {
//			CocineroValidator cocineroValidator = new CocineroValidator();
//			ValidationUtils.invokeValidator(cocineroValidator, cocinero, result);
			cocina.setFechaInicioContrato(LocalDate.now());
			this.cocineroService.saveCocinero(cocina);
			log.info("Cocinero guardado");
			return "redirect:/allCocineros";
		}
	} 

	//iniciar actualizacion
	@GetMapping(value = "/cocineros/{cocineroId}/edit")
	public String initUpdateForm(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		Cocina cocina = this.cocineroService.findCocineroById(cocineroId);
		model.put("cocina", cocina);
		log.info("Inicia actualizacion de cocinero");
		return "cocineros/createOrUpdateCocinaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/cocineros/{cocineroId}/edit")
	public String processUpdateCocineroForm(@Valid Cocina cocinero, BindingResult result,
			@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		if (result.hasErrors()) {

			cocinero.setId(cocineroId);
			model.put("cocina", cocinero);
			log.error("Fallo en la actualizacion de cocinero");

			return "cocineros/createOrUpdateCocinaForm";
		}
		else {
//			CocineroValidator cocineroValidator = new CocineroValidator();
//			ValidationUtils.invokeValidator(cocineroValidator, cocinero, result);
			cocinero.setId(cocineroId);
			Cocina antiguo = this.cocineroService.findCocineroById(cocineroId);
			cocinero.setFechaInicioContrato(antiguo.getFechaInicioContrato());
			if(antiguo.getFechaFinContrato()!=null) {
				cocinero.setFechaFinContrato(antiguo.getFechaFinContrato());
			}
			this.cocineroService.saveCocinero(cocinero);
			log.info("Cocinero actualizado");
			return "redirect:/allCocineros";
		}
	}
	
	//borrar cocinero
	@GetMapping(value = "/cocineros/{cocineroId}/delete")
	public String initDeleteCuenta(@PathVariable("cocineroId") int cocineroId, ModelMap model) {
		Cocina cocinero = this.cocineroService.findCocineroById(cocineroId);
		this.cocineroService.deleteCocinero(cocinero);
		log.info("Cocinero borrado");
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
			if(cocinero.getFechaInicioContrato().plusDays(31)
					.isBefore(LocalDate.now())){
				cocinero.setFechaFinContrato(LocalDate.now());
			}else {
				//mandar mensaje
//				Boolean noDarDeBaja = true;
//				model.put("noDarDebaja", noDarDeBaja);
				log.warn("No se puede dar de baja");
				return "redirect:/NoEsPosibleDarDeBaja";
			}
		}
		this.cocineroService.saveCocinero(cocinero);
		return "redirect:/allCocineros";
	}
	
}
