package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cartas;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartaController {

	
	private final CartaService CartaService;

	@Autowired
	public CartaController(CartaService CartaService) {
		this.CartaService = CartaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allCartas" })
	public String showCartaList(Map<String, Object> model) {
		Cartas Cartas = new Cartas();
		Cartas.getCartasList().addAll(this.CartaService.findCartas());
		model.put("Cartas", Cartas);
		return "Cartas/CartasList";
	}

	//añadir una Carta nueva
	@GetMapping(value = "/Cartas/new")
	public String initCreationForm(Map<String, Object> model) {
		Carta Carta = new Carta();
		model.put("Carta", Carta);
		return "Cartas/createOrUpdateCartaForm";
	}

	//mandar nueva Carta
	@PostMapping(value = "/Cartas/new")
	public String processCreationForm(@Valid Carta Carta, BindingResult result) {
		if (result.hasErrors()) {
			return "Cartas/createOrUpdateCartaForm";
		}
		else {
			this.CartaService.saveCarta(Carta);
			return "redirect:/allCartas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/Cartas/{CartaId}/edit")
	public String initUpdateForm(@PathVariable("CartaId") int CartaId, ModelMap model) {
		Carta Carta = this.CartaService.findCartaById(CartaId);
		model.put("Carta", Carta);
		return "Cartas/createOrUpdateCartaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/Cartas/{CartaId}/edit")
	public String processUpdateCartaForm(@Valid Carta Carta, BindingResult result,
			@PathVariable("CartaId") int CartaId) {
		if (result.hasErrors()) {
			return "Cartas/createOrUpdateCartaForm";
		}
		else {
			Carta.setId(CartaId);
			this.CartaService.saveCarta(Carta);
			return "redirect:/allCartas";
		}
	}
	
	//borrar Carta
	@GetMapping(value = "/Cartas/{CartaId}/delete")
	public String initDeleteCarta(@PathVariable("CartaId") int CartaId, ModelMap model) {
		Carta Carta = this.CartaService.findCartaById(CartaId);
		this.CartaService.deleteCarta(Carta);
		return "redirect:/allCartas";
	}
	
//	@ModelAttribute("Carta")
//	public Carta findCarta(@PathVariable("CartaId") int CartaId) {
//		return this.findCarta(CartaId);
//	}
}
