package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
public class PizzaController {

	private final PizzaService pizzaService;
	private final IngredienteService ingredienteService;
	private final PedidoService pedidoService;
	private final ClienteService ClienteService;
	private final UserService UserService;

	@Autowired
	public PizzaController(PizzaService PizzaService, IngredienteService ingredienteService, PedidoService pedidoService, ClienteService ClienteService, UserService UserService) {
		this.pizzaService = PizzaService;
		this.ingredienteService = ingredienteService;
		this.pedidoService=pedidoService;
		this.ClienteService =ClienteService;
		this.UserService =UserService;
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
		pizzas.getPizzasList().addAll(this.pizzaService.findPizzaNoPersonalizada());
		model.put("Pizzas", pizzas);  //si pongo Pizzas me pone la tabla vacia, si pongo pizza me da un error de tamaño
		return "pizzas/pizzasList";
	}

	// crear nuevo Pizza
	@GetMapping(value = "/pizzas/admin/new")
	public String initCreationFormAdmin(Map<String, Object> model) {
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}

	// mandar nuevo Pizza
	@PostMapping(value = "/pizzas/admin/new")
	public String processCreationFormAdmin(@Valid Pizza pizza, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("pizza", pizza);//importanteeee
			return "pizzas/createOrUpdatePizzaForm";
		} else {

			pizza.setCliente(null);
			pizza.setPersonalizada(false);

			this.pizzaService.savePizza(pizza);
			return "redirect:/allPizzas";
		}
	}
	
	@GetMapping(value = "/pizzas/cliente/new")
	public String initCreationFormCliente(Map<String, Object> model) {
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}

	// mandar nuevo Pizza
	@PostMapping(value = "/pizzas/cliente/new")
	public String processCreationFormCliente(@Valid Pizza pizza, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("pizza", pizza);//importanteeee
			return "pizzas/createOrUpdatePizzaForm";
		} else {
			Cliente c = getClienteActivo();
			pizza.setCliente(c);
			pizza.setPersonalizada(true);
			PizzaValidator pizzaValidator = new PizzaValidator();
			ValidationUtils.invokeValidator(pizzaValidator, pizza, result);
			this.pizzaService.savePizza(pizza);
			return "redirect:/allPizzas";
		}
	}
	
	
	

	// iniciar actualizacion
	@GetMapping(value = "/pizzas/admin/{pizzaId}/edit")
	public String initUpdateForm(@PathVariable("pizzaId") int pizzaId, ModelMap model) {
		Pizza pizza = this.pizzaService.findPizzaById(pizzaId);
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}

	// mandar actualizacion
	@PostMapping(value = "/pizzas/admin/{pizzaId}/edit")
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
	@GetMapping(value = "/pizzas/admin/{pizzaId}/delete")
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
	private Cliente getClienteActivo() {
		UserDetails userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
          userDetails = (UserDetails) principal;
        }
        String userName = userDetails.getUsername();
        User usuario = this.UserService.findUser(userName).get();
        Cuenta cliente= this.ClienteService.findCuentaByUser(usuario);
        return  (Cliente) cliente;
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

