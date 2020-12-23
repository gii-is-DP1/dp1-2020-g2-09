package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Ofertas;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.service.OfertaService;
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

@Controller

public class OfertaController {
	
	private final OfertaService ofertaService;

	@Autowired
	public OfertaController(OfertaService ofertaService) {
		this.ofertaService = ofertaService;
	}

	@InitBinder("oferta")
	public void initOfertaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new OfertaValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@ModelAttribute("tamanoOferta")
	public Collection<TamanoOferta> populateTamanoOferta() {
		return this.ofertaService.findTamanoOferta();
	}
	
	@ModelAttribute("nivelSocio")
	public Collection<NivelSocio> populateNivelSocio() {
		return this.ofertaService.findNivelSocio();
	}
	
	@GetMapping(value = { "/allOfertas" })
	public String showPedidoList(Map<String, Object> model) {
		Ofertas ofertas = new Ofertas();
		ofertas.getOfertasList().addAll(this.ofertaService.findOfertas());
		model.put("ofertas", ofertas);
		model.put("hoy",LocalDate.now());
		return "ofertas/ofertasList";
	}

	//crear nueva oferta
	@GetMapping(value = "/ofertas/new")
	public String initCreationForm(Map<String, Object> model) {
		Oferta oferta = new Oferta();
		model.put("oferta", oferta);
		return "ofertas/createOrUpdateOfertaForm";
	}

	//mandar nueva oferta
	@PostMapping(value = "/ofertas/new")
	public String processCreationForm(@Valid Oferta oferta,  BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("oferta", oferta);//importanteeee
			return "ofertas/createOrUpdateOfertaForm";
		}
		else {
			OfertaValidator ofValidator = new OfertaValidator();
			ValidationUtils.invokeValidator(ofValidator, oferta, result);
			this.ofertaService.saveOferta(oferta);
			return "redirect:/allOfertas";
		}
	}

	//iniciar actualizacion oferta
	@GetMapping(value = "/ofertas/{ofertaId}/edit")
	public String initUpdateForm(@PathVariable("ofertaId") int ofertaId, ModelMap model) {
		Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
		model.put("oferta", oferta);
		return "ofertas/createOrUpdateOfertaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/ofertas/{ofertaId}/edit")
	public String processUpdatePedidoForm(@Valid Oferta oferta, BindingResult result,
			@PathVariable("ofertaId") int ofertaId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("oferta", oferta);
			return "ofertas/createOrUpdateOfertaForm";
		}
		else { 
			OfertaValidator ofValidator = new OfertaValidator();
			ValidationUtils.invokeValidator(ofValidator, oferta, result);
			oferta.setId(ofertaId);
			this.ofertaService.saveOferta(oferta);
			return "redirect:/allOfertas";
		}
	}
	
	//borrar oferta
	@GetMapping(value = "/ofertas/{ofertasId}/delete")
	public String initDeleteOferta(@PathVariable("ofertasId") int ofertaId, ModelMap model) {
		Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
		this.ofertaService.deleteOferta(oferta);
		return "redirect:/allOfertas";
	}

}
