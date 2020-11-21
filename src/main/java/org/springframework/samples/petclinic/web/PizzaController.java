package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	private final PizzaService PizzaService;

	@Autowired
	public PizzaController(PizzaService PizzaService) {
		this.PizzaService = PizzaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = { "/allPizzas" })
	public String showPizzaList(Map<String, Object> model) {
		Pizzas Pizzas = new Pizzas();
		Pizzas.getPizzasList().addAll(this.PizzaService.findPizzas());
		model.put("Pizzas", Pizzas);
		return "Pizzas/PizzasList";
	}

	// crear nuevo Pizza
	@GetMapping(value = "/s/new")
	public String initCreationForm(Map<String, Object> model) {
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		return "Pizzas/createOrUpdatePizzaForm";
	}

	// mandar nuevo Pizza
	@PostMapping(value = "/pizzas/new")
	public String processCreationForm(@Valid Pizza Pizza, BindingResult result) {
		if (result.hasErrors()) {
			return "Pizzas/createOrUpdatePizzaForm";
		} else {
			this.PizzaService.savePizza(Pizza);
			return "redirect:/allPizzas";
		}
	}

	// iniciar actualizacion
	@GetMapping(value = "/pizzas/{pizzaId}/edit")
	public String initUpdateForm(@PathVariable("Id") int pizzaId, ModelMap model) {
		Pizza Pizza = this.PizzaService.findPizzaById(pizzaId);
		model.put("pizza", Pizza);
		return "Pizzas/createOrUpdatePizzaForm";
	}

	// mandar actualizacion
	@PostMapping(value = "/pizzas/{pizzaId}/edit")
	public String processUpdatePizzaForm(@Valid Pizza Pizza, BindingResult result,
			@PathVariable("pizzaId") int pizzaId) {
		if (result.hasErrors()) {
			return "Pizzas/createOrUpdatePizzaForm";
		} else {
			Pizza.setId(pizzaId);
			this.PizzaService.savePizza(Pizza);
			return "redirect:/alls";
		}
	}

	// borrar Pizza
	@GetMapping(value = "/pizzas/{pizzaId}/delete")
	public String initDeletePizza(@PathVariable("pizzaId") int pizzaId, ModelMap model) {
		Pizza Pizza = this.PizzaService.findPizzaById(pizzaId);
		this.PizzaService.deletePizza(Pizza);
		return "redirect:/allPizzas";
	}


}