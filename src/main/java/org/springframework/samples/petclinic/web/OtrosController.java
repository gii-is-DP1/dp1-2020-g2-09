package org.springframework.samples.petclinic.web;


import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.OtrosLista;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OtrosService;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservas/{reservaId}")
public class OtrosController {
	
	private final OtrosService OtrosService;

	private final IngredienteService IngredienteService;
	@Autowired
	public OtrosController(OtrosService OtroService, IngredienteService IngredienteService) {
		this.OtrosService = OtroService;
		this.IngredienteService = IngredienteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allOtros" })
	public String showOtrosList(Map<String, Object> model) {
		OtrosLista Otros = new OtrosLista();
		Otros.getOtrosList().addAll(this.OtrosService.findOtros());
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
	public String processCreationForm(@Valid Otros otros, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
				model.put("otros", otros);//importanteeee
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			OtrosValidator ostrosValidator = new OtrosValidator();
			ValidationUtils.invokeValidator(ostrosValidator, otros, result);
			this.OtrosService.saveOtros(otros);
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
	public String processUpdateOtrosForm(@Valid Otros otros, BindingResult result,
			@PathVariable("OtrosId") int OtrosId) {
		if (result.hasErrors()) {
			return "Otros/createOrUpdateOtrosForm";
		}
		else {
			OtrosValidator otrosValidator = new OtrosValidator();
			ValidationUtils.invokeValidator(otrosValidator, otros, result);
			otros.setId(OtrosId);
			this.OtrosService.saveOtros(otros);
			return "redirect:/allOtros";
		}
	}
	
	//borrar Otros
	@GetMapping(value = "/Otros/{OtrosId}/delete")
	public String initDeleteOtros(@PathVariable("OtrosId") int OtrosId, ModelMap model) {
		Otros Otros = this.OtrosService.findOtrosById(OtrosId);
		this.OtrosService.deleteOtros(Otros);
		return "redirect:/cartas/{cartaId}/otros";
	}
	
	 @ModelAttribute("ingredientes")
	    public Collection<Ingrediente> populateIngrediente() {
	    	Collection<Ingrediente> c = this.IngredienteService.findIngredientes();
	    	return c;
	   }
	 
}