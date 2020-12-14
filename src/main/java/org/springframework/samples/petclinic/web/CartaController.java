package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Bebidas;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.OtrosLista;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
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
	
	//Acceso a la carta
	@GetMapping(value = "/cartas/{cartaId}/VerCarta")
	public String verCarta(@PathVariable("cartaId") Integer cartaId, ModelMap model) {
		
		model.put("cartaId", cartaId);
		//Recogemos las pizzas de la tabla y la guardamos en el modelo
		List<Integer> listaIdPizzas = PizzaService.findIdPizzaById(cartaId);
		Pizzas listaPizzas = new Pizzas();
		for(int i=0; i<listaIdPizzas.size(); i++) {
			Integer pizzaId = listaIdPizzas.get(i);
			Pizza pizza = this.PizzaService.findPizzaById(pizzaId);
			listaPizzas.getPizzasList().add(pizza);
		}
		model.put("pizzas", listaPizzas);
		
		List<Integer> listaIdBebidas = BebidaService.findIdBebidaByCartaId(cartaId);
		Bebidas listaBebidas = new Bebidas();
		for(int i=0; i<listaIdBebidas.size(); i++) {
			Integer bebidaId = listaIdBebidas.get(i);
			Bebida bebida = this.BebidaService.findById(bebidaId);
			listaBebidas.getBebidasList().add(bebida);
		}
		model.put("bebidas", listaBebidas);
		
		List<Integer> listaIdOtros = OtrosService.findIdOtroById(cartaId);
		OtrosLista listaOtros = new OtrosLista();
		for(int i=0; i<listaIdOtros.size(); i++) {
			Integer otroId = listaIdOtros.get(i);
			Otros bebida = this.OtrosService.findOtrosById(otroId);
			listaOtros.getOtrosList().add(bebida);
		}
		model.put("otros", listaOtros);

		
		return "cartas/verCarta";
	}
	
	@GetMapping(value = { "/cartas/{cartaId}/pizzas" })
	public String showPizzaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Pizzas pizzas = new Pizzas();
		pizzas.getPizzasList().addAll(this.PizzaService.findPizzas());
		model.put("Pizzas", pizzas);  //si pongo Pizzas me pone la tabla vacia, si pongo pizza me da un error de tamaño
	
		return "pizzas/pizzasList";
	}
	
	@GetMapping(value = { "/cartas/{cartaId}/bebidas" })
	public String showBebidaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Bebidas bebidas = new Bebidas();
		bebidas.getBebidasList().addAll(this.BebidaService.findBebidas());
		model.put("bebidas", bebidas);
		
		return "bebidas/bebidasList";
	}
	
	@GetMapping(value = { "/cartas/{cartaId}/otros" })
	public String showOtrosLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		List<Otros> Otros = new ArrayList<Otros>();
		Otros.addAll(this.OtrosService.findOtros());
		model.put("Otros", Otros);
		
		return "Otros/OtrosList";
	}
	
	
	@GetMapping(value = "/cartas/{cartaId}/anadirPizzaACarta/{pizzaId}")
    public String añadirPizzaACarta(@PathVariable("pizzaId") int pizzaId, @PathVariable("cartaId") int cartaId) {
    	this.PizzaService.añadirPizzaACarta(pizzaId, cartaId);
    	return "redirect:/cartas/{cartaId}/VerCarta";
    }
	
	@GetMapping(value = "/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}")
    public String añadirBebidaACarta(@PathVariable("bebidaId") int bebidaId, @PathVariable("cartaId") int cartaId) {
    	this.BebidaService.añadirBebidaACarta(bebidaId, cartaId);
    	return "redirect:/cartas/{cartaId}/VerCarta";
    }
	
	@GetMapping(value = "/cartas/{cartaId}/anadirOtroACarta/{otroId}")
    public String añadirOtroACarta(@PathVariable("otroId") int otroId, @PathVariable("cartaId") int cartaId) {
    	this.OtrosService.añadirOtroACarta(otroId, cartaId);
    	return "redirect:/cartas/{cartaId}/VerCarta";
    }
	
//	//Borrar carta
//	@DeleteMapping(value = "/carta/{cartaId}/delete")
//	public String deleteCarta(@PathVariable("cartaId") int cartaId) {
//		Carta carta = this.CartaService.findCartaById(cartaId);
//		this.CartaService.deleteCarta(carta);
//		return "redirect:/allCartas";
//	}
	
	
	
	
	
	
	
//	//buscar pizzas de la carta
//	@GetMapping(value = "/cartas/pizzas/{cartaId}")
//	public String initCartaPizza(@PathVariable("cartaId") Carta carta, ModelMap model) {
//		List<Pizza> lista= PizzaService.findByCarta(carta);
//		model.put("pizzas", lista);
//		return "redirect:/allCartas";
//	}
//	
//	//buscar bebidas de la carta
//		@GetMapping(value = "/cartas/bebidas/{cartaId}")
//		public String initCartaBebida(@PathVariable("cartaId") Carta carta, ModelMap model) {
//			List<Bebida> lista= BebidaService.findByCarta(carta);
//			model.put("bebidas", lista);
//			return "redirect:/allCartas";
//		}
//		
//		//buscar otros de la carta
//		@GetMapping(value = "/cartas/otros/{cartaId}")
//		public String initCartaOtros(@PathVariable("cartaId") int cartaId, ModelMap model) {
//			List<Otros> lista= OtrosService.findByCarta(cartaId);
//			model.put("otros", lista);
//			return "redirect:/allCartas";
//		}
	
//	@ModelAttribute("Carta")
//	public Carta findCarta(@PathVariable("CartaId") int CartaId) {
//		return this.findCarta(CartaId);
//	}
}
