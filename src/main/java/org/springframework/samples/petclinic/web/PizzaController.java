package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PizzaController {

	private final PizzaService pizzaService;

	@Autowired
	public PizzaController(PizzaService PizzaService) {
		this.pizzaService = PizzaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = { "/allPizzas" })
	public String showPizzaList(Map<String, Object> model) {
		Pizzas pizzas = new Pizzas();
		pizzas.getPizzasList().addAll(this.pizzaService.findPizzas());
		model.put("Pizzas", pizzas);  //si pongo Pizzas me pone la tabla vacia, si pongo pizza me da un error de tamaño
	
		return "pizzas/pizzasList";
	}

	// crear nuevo Pizza
	@GetMapping(value = "/pizzas/new")
	public String initCreationForm(Map<String, Object> model) {
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}

	// mandar nuevo Pizza
	@PostMapping(value = "/pizzas/new")
	public String processCreationForm(@Valid Pizza Pizza, BindingResult result) {
		if (result.hasErrors()) {
			return "pizzas/createOrUpdatePizzaForm";
		} else {
			this.pizzaService.savePizza(Pizza);
			return "redirect:/allPizzas";
		}
	}

	// iniciar actualizacion
	@GetMapping(value = "/pizzas/{pizzaId}/edit")
	public String initUpdateForm(@PathVariable("pizzaId") int pizzaId, ModelMap model) {
		Pizza pizza = this.pizzaService.findPizzaById(pizzaId);
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}

	// mandar actualizacion
	@PostMapping(value = "/pizzas/{pizzaId}/edit")
	public String processUpdatePizzaForm(@Valid Pizza Pizza, BindingResult result,
			@PathVariable("pizzaId") int pizzaId) {
		if (result.hasErrors()) {
			return "pizzas/createOrUpdatePizzaForm";
		} else {
			Pizza.setId(pizzaId);
			this.pizzaService.savePizza(Pizza);
			return "redirect:/allPizzas";
		}
	}

	// borrar Pizza
	@GetMapping(value = "/pizzas/{pizzaId}/delete")
	public String initDeletePizza(@PathVariable("pizzaId") int pizzaId, ModelMap model) {
		Pizza pizza = this.pizzaService.findPizzaById(pizzaId);
		this.pizzaService.deletePizza(pizza);
		return "redirect:/allPizzas";
	}


}
