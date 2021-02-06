package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Mesas;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MesaController {

	
	private final MesaService mesaService;

	@Autowired
	public MesaController(MesaService mesaService) {
		this.mesaService = mesaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("mesa")
	public void initMesaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new MesaValidator());
	}
	
	@GetMapping(value = { "/allMesas" })
	public String showMesaList(Map<String, Object> model) {
		Mesas mesas = new Mesas();
		mesas.getMesasList().addAll(this.mesaService.findMesas());
		model.put("mesas", mesas);
		log.info("Mostrando listado de mesas.");
		return "mesas/mesasList";
	}

	//añadir una mesa nueva
	@GetMapping(value = "/mesas/new")
	public String initCreationForm(Map<String, Object> model) {
		Mesa mesa = new Mesa();
		model.put("mesa", mesa);
		return "mesas/createOrUpdateMesaForm";
	}

	//mandar nueva mesa
	@PostMapping(value = "/mesas/new")
	public String processCreationForm(@Valid Mesa mesa, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("mesa", mesa);
			log.warn("Error a la hora de crear una mesa.");
			return "mesas/createOrUpdateMesaForm";
		}
		else {
			MesaValidator mesaValidator = new MesaValidator();
			ValidationUtils.invokeValidator(mesaValidator, mesa, result);
			this.mesaService.saveMesa(mesa);
			log.info("Mesa creada correctamente.");
			return "redirect:/allMesas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/mesas/{mesaId}/edit")
	public String initUpdateForm(@PathVariable("mesaId") int mesaId, ModelMap model) {
		Mesa mesa = this.mesaService.findById(mesaId);
		model.put("mesa", mesa);
		return "mesas/createOrUpdateMesaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/mesas/{mesaId}/edit")
	public String processUpdateMesaForm(@Valid Mesa mesa, BindingResult result, ModelMap model,
			@PathVariable("mesaId") int mesaId, @RequestParam(value = "version", required=false) Integer version) {
		
		Mesa mesaToUpdate=this.mesaService.findById(mesaId);
		if(mesaToUpdate.getVersion()!=version) {
		model.put("message","Problema de concurrencia a la hora de editar la mesa. "
				+ "Inténtelo de nuevo más tarde.");
		log.info("Problema de concurrencia a la hora de editar la mesa.");
		return initUpdateForm(mesaId,model);
		}

		if (result.hasErrors()) {
			mesa.setId(mesaId);
			log.warn("Error a la hora de actualizar una mesa.");
			return "mesas/createOrUpdateMesaForm";
		}
		else {
			mesa.setId(mesaId);
			this.mesaService.saveMesa(mesa);
			log.info("Mesa actualizada correctamente.");
			return "redirect:/allMesas";
		}
	}
	
	//borrar mesa
	@GetMapping(value = "/mesas/{mesaId}/delete")
	public String initDeleteMesa(@PathVariable("mesaId") int mesaId, ModelMap model) {
		Mesa mesa = this.mesaService.findById(mesaId);
		this.mesaService.deleteMesa(mesa);
		log.info("Mesa eliminada correctamente.");
		return "redirect:/allMesas";
	}


//	@ModelAttribute("mesa")
//	public Mesa findMesa(@PathVariable("mesaId") int mesaId) {
//		return this.findMesa(mesaId);
//	}

}
