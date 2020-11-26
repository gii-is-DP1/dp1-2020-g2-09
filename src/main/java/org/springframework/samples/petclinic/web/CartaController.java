package org.springframework.samples.petclinic.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartaController {

	@Autowired
	private CartaService CartaService;
	@Autowired
	private PizzaService PizzaService;
	@Autowired
	private OtrosService OtrosService;
	@Autowired
	private BebidaService BebidaService;


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allCartas" })
	public String showCartaList(Map<String, Object> model) {
		List<Carta> cartas= CartaService.findCartas();
		model.put("cartas", cartas);
		return "cartas/cartasList";
	}
	

	//añadir una Carta nueva
	@GetMapping(value = "/cartas/new")
	public String initCreationForm(Map<String, Object> model) {
		Carta Carta = new Carta();
		model.put("carta", Carta);
		return "cartas/createOrUpdateCartaForm";
	}

	//mandar nueva Carta
	@PostMapping(value = "/cartas/new")
	public String processCreationForm(@Valid Carta carta, BindingResult result) {
		if (result.hasErrors()) {
			return "cartas/createOrUpdateCartaForm";
		}
		else {
			this.CartaService.saveCarta(carta);
			return "redirect:/allCartas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/cartas/{cartaId}/edit")
	public String initUpdateForm(@PathVariable("cartaId") int cartaId, ModelMap model) {
		Carta Carta = this.CartaService.findCartaById(cartaId);
		model.put("carta", Carta);
		return "cartas/createOrUpdateCartaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/cartas/{cartaId}/edit")
	public String processUpdateCartaForm(@Valid Carta carta, BindingResult result,
			@PathVariable("cartaId") int cartaId) {
		if (result.hasErrors()) {
			return "cartas/createOrUpdateCartaForm";
		}
		else {
			carta.setId(cartaId);
			this.CartaService.saveCarta(carta);
			return "redirect:/allCartas";
		}
	}
	
	//borrar Carta
	@GetMapping(value = "/cartas/{cartaId}/delete")
	public String initDeleteCarta(@PathVariable("cartaId") int cartaId, ModelMap model) {
		Carta carta = this.CartaService.findCartaById(cartaId);
		this.CartaService.deleteCarta(carta);
		return "redirect:/allCartas";
	}
	
	//buscar pizzas de la carta
	@GetMapping(value = "/cartas/pizzas/{cartaId}")
	public String initCartaPizza(@PathVariable("cartaId") int cartaId, ModelMap model) {
		List<Pizza> lista= PizzaService.findByCarta(cartaId);
		model.put("pizzas", lista);
		return "redirect:/allCartas";
	}
	
	//buscar bebidas de la carta
		@GetMapping(value = "/cartas/bebidas/{cartaId}")
		public String initCartaBebida(@PathVariable("cartaId") int cartaId, ModelMap model) {
			List<Bebida> lista= BebidaService.findByCarta(cartaId);
			model.put("bebidas", lista);
			return "redirect:/allCartas";
		}
		
		//buscar otros de la carta
		@GetMapping(value = "/cartas/otros/{cartaId}")
		public String initCartaOtros(@PathVariable("cartaId") int cartaId, ModelMap model) {
			List<Otros> lista= OtrosService.findByCarta(cartaId);
			model.put("otros", lista);
			return "redirect:/allCartas";
		}

	@DeleteMapping(value = "/carta/{cartaId}/delete")
	public String deleteCarta(@PathVariable("cartaId") int cartaId) {
		Carta carta = this.CartaService.findCartaById(cartaId);
		this.CartaService.deleteCarta(carta);
		return "redirect:/allCartas";
	}
	
//	@ModelAttribute("Carta")
//	public Carta findCarta(@PathVariable("CartaId") int CartaId) {
//		return this.findCarta(CartaId);
//	}
}
