package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Administradores;
import org.springframework.samples.petclinic.service.AdministradorService;
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
public class AdministradorController {

	private final AdministradorService administradorService;

	@Autowired
	public AdministradorController(AdministradorService administradorService) {
		this.administradorService = administradorService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	/*@InitBinder("administrador")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new AdministradorValidator());
	}*/
	
	@GetMapping(value = { "/allAdministradores" })
	public String showAdministradoresList(Map<String, Object> model) {
		Administradores administradores = new Administradores();
		administradores.getAdministradoresList().addAll(this.administradorService.findAdministradores());
		model.put("administradores", administradores);
		return "administradores/administradoresList";
	}

	//crear nuevo administrador
	@GetMapping(value = "/administradores/new")
	public String initCreationForm(Map<String, Object> model) {
		Administrador administrador = new Administrador();
		model.put("administradores", administrador);
		return "administradores/createOrUpdateAdministradorForm";
	}

	//mandar nuevo cliente
	@PostMapping(value = "/administradores/new")
	public String processCreationForm(@Valid Administrador administrador, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("administradores", administrador);//importanteeee
			return "administradores/createOrUpdateAdministradorForm";
		}
		else {
			AdministradorValidator adminValidator = new AdministradorValidator();
			ValidationUtils.invokeValidator(adminValidator, administrador, result);
			this.administradorService.saveAdministrador(administrador);
			return "redirect:/allAdministradores";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/administradores/{administradorId}/edit")
	public String initUpdateForm(@PathVariable("administradorId") int administradorId, ModelMap model) {
		Administrador administrador = this.administradorService.findAdministradorById(administradorId);
		model.put("administradores", administrador);
		return "administradores/createOrUpdateAdministradorForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/administradores/{administradorId}/edit")
	public String processUpdateCuentaForm(@Valid Administrador administrador, BindingResult result,
			@PathVariable("administradorId") int administradorId) {
		if (result.hasErrors()) {
			//model.put("cuenta", administrador);
			return "administradores/createOrUpdateAdministradorForm";
		}
		else {
			administrador.setId(administradorId);
			AdministradorValidator adminValidator = new AdministradorValidator();
			ValidationUtils.invokeValidator(adminValidator, administrador, result);
			this.administradorService.saveAdministrador(administrador);
			return "redirect:/allAdministradores";
		}
	}
	
	//borrar cliente
	@GetMapping(value = "/administradores/{administradorId}/delete")
	public String initDeleteCuenta(@PathVariable("administradorId") int administradorId, ModelMap model) {
		Administrador administrador = this.administradorService.findAdministradorById(administradorId);
		this.administradorService.deleteAdministrador(administrador);
		return "redirect:/allAdministradores";
	}
	
	
}
