package org.springframework.samples.petclinic.web;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reclamaciones;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReclamacionController {

	private final ReclamacionService reclamacionService;
	
	@Autowired
	public ReclamacionController(ReclamacionService reclamacionService) {
		this.reclamacionService = reclamacionService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allReclamaciones" })
	public String showReclamacionList(Map<String, Object> model) {
		Reclamaciones reclamaciones = new Reclamaciones();
		reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
		model.put("reclamaciones", reclamaciones);
		return "reclamaciones/reclamacionesList";
	}
	
	//añadir una reclamacion nueva
		@GetMapping(value = "/reclamaciones/new")
		public String initCreationForm(Map<String, Object> model) {
			Reclamacion reclamacion = new Reclamacion();
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar nueva reclamacion
		@PostMapping(value = "/reclamaciones/new")
		public String processCreationForm(@Valid Reclamacion reclamacion, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				model.put("reclamacion", reclamacion);//importanteeee
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
				ReclamacionValidator reclamacionValidator = new ReclamacionValidator();
				ValidationUtils.invokeValidator(reclamacionValidator, reclamacion, result);
				this.reclamacionService.saveReclamacion(reclamacion);
				return "redirect:/";
			} 
		}
		
	/*	
		//iniciar actualizacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String initUpdateForm(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar actualizacion
		@PostMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String processUpdateReclamacionForm(@Valid Reclamacion reclamacion, BindingResult result,
				@PathVariable("reclamacionId") int reclamacionId) {
			if (result.hasErrors()) {
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
				reclamacion.setId(reclamacionId);
				this.reclamacionService.saveReclamacion(reclamacion);
				return "redirect:/allReclamaciones";
			}
		}
		
		//borrar reclamacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/delete")
		public String initDeleteReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			this.reclamacionService.deleteReclamacion(reclamacion);
			return "redirect:/allReclamaciones";
		} */
		
//		@ModelAttribute("reclamacion")
//		public Reclamacion findReclamacion(@PathVariable("reclamacionId") int reclamacionId) {
//			return this.findReclamacion(reclamacionId);
//		}
}