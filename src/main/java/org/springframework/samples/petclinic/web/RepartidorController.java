package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.Repartidores;
import org.springframework.samples.petclinic.service.RepartidorService;
import org.springframework.samples.petclinic.service.UserService;
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
public class RepartidorController {

	private final RepartidorService repartidorService;

	@Autowired
	public RepartidorController(RepartidorService repartidorService) {
		this.repartidorService = repartidorService;
	}

	@InitBinder("repartidor")
	public void initrepartidorBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new RepartidorValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allRepartidores" })
	public String showRepartidoresList(Map<String, Object> model) {
		Repartidores repartidores = new Repartidores();
		repartidores.getRepartidoresList().addAll(this.repartidorService.findRepartidores());
		model.put("listarepartidores", repartidores);
		log.info("Mostrando los repartidores");
		return "repartidores/repartidoresList";
	}

	//crear nuevo repartidor
	@GetMapping(value = "/repartidores/new")
	public String initCreationForm(Map<String, Object> model) {
		Repartidor repartidor = new Repartidor();
		model.put("repartidor", repartidor);
		return "repartidores/createOrUpdateRepartidorForm";
	}

	//mandar nuevo repartidor
	@PostMapping(value = "/repartidores/new")
	public String processCreationForm(@Valid Repartidor repartidor, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("repartidor", repartidor);
			log.warn("Fallo en la creacion de repartidor");
			return "repartidores/createOrUpdateRepartidorForm";
		}
		else {
//			RepartidorValidator repValidator = new RepartidorValidator();
//			ValidationUtils.invokeValidator(repValidator, repartidor, result);
			repartidor.setFechaInicioContrato(LocalDate.now());
			this.repartidorService.saveRepartidor(repartidor);
			log.info("Guardar repartidor");
			return "redirect:/allRepartidores";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/repartidores/{repartidorId}/edit")
	public String initUpdateForm(@PathVariable("repartidorId") int repartidorId, ModelMap model) {
		Repartidor repartidor = this.repartidorService.findRepartidorById(repartidorId);
		model.put("repartidor", repartidor);
		return "repartidores/createOrUpdateRepartidorForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/repartidores/{repartidorId}/edit")
	public String processUpdateCocineroForm(@Valid Repartidor repartidor, BindingResult result,
			@PathVariable("repartidorId") int repartidorId, ModelMap model) {
		if (result.hasErrors()) { 
			model.put("repartidores", repartidor);
			log.warn("Fallo al actualizar repartidor");
			return "repartidores/createOrUpdateRepartidorForm";
		}
		else {
			repartidor.setId(repartidorId);
			Repartidor antiguo = this.repartidorService.findRepartidorById(repartidorId);
			repartidor.setFechaInicioContrato(antiguo.getFechaInicioContrato());
			if(antiguo.getFechaFinContrato()!=null) {
				repartidor.setFechaFinContrato(antiguo.getFechaFinContrato());
			}
			this.repartidorService.saveRepartidor(repartidor);
			log.info("Repartidor actualizado");
			return "redirect:/allRepartidores";
		}
	}
	
	//Alta y baja
		@GetMapping(value = "/repartidores/{repartidorId}/altaobaja")
		public String darAltayBaja(@PathVariable("repartidorId") int repartidorId, ModelMap model) {
			Repartidor repartidor = this.repartidorService.findRepartidorById(repartidorId);
			if(repartidor.getFechaFinContrato()!=null) {
				repartidor.setFechaInicioContrato(LocalDate.now());
				repartidor.setFechaFinContrato(null);
			}else {
				if(repartidor.getFechaInicioContrato().plusDays(31)
						.isBefore(LocalDate.now())){
					repartidor.setFechaFinContrato(LocalDate.now());
				}else {
					//mandar mensaje
					Boolean noDarDeBaja = true;
					model.put("noDarDebaja", noDarDeBaja);
					log.warn("No se puede dar de baja");
					return "redirect:/NoEsPosibleDarDeBaja";
				}
			}
					
			
			this.repartidorService.saveRepartidor(repartidor);
			return "redirect:/allRepartidores";
		}
		
		//borrar repartidor
		@GetMapping(value = "/repartidores/{repartidorId}/delete")
		public String initDeleteCuenta(@PathVariable("repartidorId") int repartidorId, ModelMap model) {
			Repartidor rep = this.repartidorService.findRepartidorById(repartidorId);
			this.repartidorService.deleteRepartidor(rep);
			log.info("Repartidor borrado");
			return "redirect:/allRepartidores";
		}	
}
