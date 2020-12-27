package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
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
public class PizzaController {

	private final PizzaService pizzaService;
	private final IngredienteService ingredienteService;
	private final PedidoService pedidoService;

	@Autowired
	public PizzaController(PizzaService PizzaService, IngredienteService ingredienteService, PedidoService pedidoService) {
		this.pizzaService = PizzaService;
		this.ingredienteService = ingredienteService;
		this.pedidoService=pedidoService;
	}

	
	@InitBinder("pizza")
	public void initPizzaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PizzaValidator());
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
	public String processCreationForm(@Valid Pizza pizza, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("pizza", pizza);//importanteeee
			return "pizzas/createOrUpdatePizzaForm";
		} else {
//			PizzaValidator pizzaValidator = new PizzaValidator();
//			ValidationUtils.invokeValidator(pizzaValidator, pizza, result);
			this.pizzaService.savePizza(pizza);
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
//			PizzaValidator pizzaValidator = new PizzaValidator();
//			ValidationUtils.invokeValidator(pizzaValidator, Pizza, result);
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
	
	// editar pizzas de un pedido
			@GetMapping(value = "/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit")
			public String actualizarPizza(@PathVariable("pedidoId") int pedidoId,@PathVariable("cartaId") int cartaId,
					@PathVariable("pizzaId") int pizzaId, ModelMap model) {
				Pizza pizza = this.pizzaService.findPizzaById(pizzaId);
				Pedido pedido= this.pedidoService.findPedidoById(pedidoId);
				model.put("pizza", pizza);
				model.put("pedido", pedido);
				model.put("cartaId", cartaId);
				return "/pizzas/UpdatePizzaFormPedido";
			}

			// mandar actualizacion
			@PostMapping(value = "/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit")
			public String processUpdatePizzaForm2(@Valid Pizza Pizza, BindingResult result,
					@PathVariable("pizzaId") int pizzaId) {
				if (result.hasErrors()) {
					return "pizzas/UpdatePizzaFormPedido";
				} else {
					Pizza.setId(pizzaId);
					Pizza.setCoste(Pizza.getCoste());
					Pizza.setIngredientes(Pizza.getIngredientes());
					Pizza.setNombre(Pizza.getNombre());
					Pizza.setContador(Pizza.getContador());
					PizzaValidator pizzaValidator = new PizzaValidator();
					ValidationUtils.invokeValidator(pizzaValidator, Pizza, result);
					System.out.println(Pizza);
					this.pizzaService.savePizza(Pizza);
					return "redirect:/allPizzas";
				}
			}

	@ModelAttribute("tipoMasa")
    public Collection<tipoMasa> populateTipoMasa() {
        return this.pizzaService.findTipoMasa();
    }
    
    @ModelAttribute("tamanyo")
    public Collection<TamanoProducto> populateTamaño() {
        return this.pizzaService.findTamaño();
    }
  
   @ModelAttribute("ingredientes")
    public Collection<Ingrediente> populateIngrediente() {
    	Collection<Ingrediente> c = this.ingredienteService.findIngredientes();
    	return c;
   }
    
}

