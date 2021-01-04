package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Bebidas;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class CartaController {

	
	private CartaService CartaService;
	
	private PizzaService PizzaService;
	
	private OtrosService OtrosService;
	
	private BebidaService BebidaService;

	private IngredienteService IngredienteService;

	@InitBinder("pizza")
	public void initPizzaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PizzaValidator());
	}
	
	@InitBinder("bebida")
	public void initBebidaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new BebidaValidator());
	}
	
	@InitBinder("otro")
	public void initOtrosBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new OtrosValidator());
	}
	
	@InitBinder("carta")//añadido nuevo
	public void initCartaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CartaValidator());
	}
	
	@Autowired
	public CartaController(CartaService CartaService, PizzaService PizzaService,
			OtrosService OtrosService, BebidaService BebidaService,
			IngredienteService IngredienteService) {
		this.CartaService = CartaService;
		this.PizzaService = PizzaService;
		this.OtrosService = OtrosService;
		this.BebidaService = BebidaService;
		this.IngredienteService = IngredienteService;
	}


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
	public String processCreationForm(@Valid Carta carta, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.put("carta", carta);//si se ha roto-> preguntar a maria
			return "cartas/createOrUpdateCartaForm";
		}
		else {
			this.CartaService.saveCarta(carta);
			return "redirect:/allCartas";
		}
	}

	//iniciar actualizacion de carta
	@GetMapping(value = "/cartas/{cartaId}/edit")
	public String initUpdateForm(@PathVariable("cartaId") int cartaId, ModelMap model) {
		Carta Carta = this.CartaService.findCartaById(cartaId);
		model.put("carta", Carta);
		return "cartas/createOrUpdateCartaForm";
	}
	
	//mandar actualizacion de carta
	@PostMapping(value = "/cartas/{cartaId}/edit")
	public String processUpdateCartaForm(@Valid Carta carta, BindingResult result,
			@PathVariable("cartaId") int cartaId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("carta", carta);
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
		Otros listaOtros = new Otros();
		for(int i=0; i<listaIdOtros.size(); i++) {
			Integer otroId = listaIdOtros.get(i);
			Otro otro = this.OtrosService.findOtrosById(otroId);
			listaOtros.getOtrosLista().add(otro);
		}
		model.put("otros", listaOtros);

		
		return "cartas/verCarta";
	}
	
	//GETTER's
	@GetMapping(value = "/cartas/{cartaId}/pizzas")
	public String showPizzaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Pizzas pizzas = new Pizzas();
		pizzas.getPizzasList().addAll(this.PizzaService.findPizzas());
		model.put("Pizzas", pizzas);  //si pongo Pizzas me pone la tabla vacia, si pongo pizza me da un error de tamaño
	
		return "pizzas/pizzasList";
	}
	
	@GetMapping(value = "/cartas/{cartaId}/bebidas")
	public String showBebidaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Bebidas bebidas = new Bebidas();
		bebidas.getBebidasList().addAll(this.BebidaService.findBebidas());
		model.put("bebidas", bebidas);
		
		return "bebidas/bebidasList";
	}
	
	@GetMapping(value = "/cartas/{cartaId}/otros" )
	public String showOtrosLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Otros otros = new Otros();
		otros.getOtrosLista().addAll(this.OtrosService.findOtros());
		model.put("otros", otros);
		
		return "Otros/OtrosList";
	}
	
	//AÑADIR A LA CARTA
	@GetMapping(value = "/cartas/{cartaId}/anadirPizzaACarta/{pizzaId}")
    public String añadirPizzaACarta(@PathVariable("pizzaId") int pizzaId,
    		@PathVariable("cartaId") int cartaId) {
		List<Integer> listaIds = this.PizzaService.findIdPizzaById(cartaId);
		Boolean duplicada = false;
		for(int i=0; i < listaIds.size() && !duplicada; i++) {
			if(listaIds.get(i).equals(pizzaId)) {
				duplicada = true;
			}
		}
		if(!duplicada) {
			this.PizzaService.añadirPizzaACarta(pizzaId, cartaId);
			return "redirect:/cartas/{cartaId}/VerCarta";
		}else {
			return "redirect:/PizzaDuplicadaEnCarta";
		}
    	
    }
	
	@GetMapping(value = "/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}")
    public String añadirBebidaACarta(@PathVariable("bebidaId") int bebidaId,
    		@PathVariable("cartaId") int cartaId) {
		List<Integer> listaIds = this.BebidaService.findIdBebidaByCartaId(cartaId);
		Boolean duplicada = false;
		for(int i=0; i < listaIds.size() && !duplicada; i++) {
			if(listaIds.get(i).equals(bebidaId)) {
				duplicada = true;
			}
		}
		if(!duplicada) {
			this.BebidaService.añadirBebidaACarta(bebidaId, cartaId);
	    	return "redirect:/cartas/{cartaId}/VerCarta";
		}else {
			return "redirect:/BebidaDuplicadaEnCarta";
		}
    }
	
	@GetMapping(value = "/cartas/{cartaId}/anadirOtroACarta/{otroId}")
    public String añadirOtroACarta(@PathVariable("otroId") int otroId,
    		@PathVariable("cartaId") int cartaId) {
		List<Integer> listaIds = this.OtrosService.findIdOtroById(cartaId);
		Boolean duplicada = false;
		for(int i=0; i < listaIds.size() && !duplicada; i++) {
			if(listaIds.get(i).equals(otroId)) {
				duplicada = true;
			}
		}
		if(!duplicada) {
			this.OtrosService.añadirOtroACarta(otroId, cartaId);
	    	return "redirect:/cartas/{cartaId}/VerCarta"; 
		}else {
			return "redirect:/OtroDuplicadaEnCarta";
		}
    	
    }
	
	
	//POST's NEW
	@GetMapping(value = "/cartas/{cartaId}/pizza/new" )
	public String initCreationForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		return "pizzas/createOrUpdatePizzaForm";
	}
	@PostMapping(value = "/cartas/{cartaId}/pizza/new")
	public String processCreationForm(@Valid Pizza Pizza, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("pizza", Pizza);//importanteeee
			return "pizzas/createOrUpdatePizzaForm";
		} else {
//			PizzaValidator pizzaValidator = new PizzaValidator();
//			ValidationUtils.invokeValidator(pizzaValidator, Pizza, result);
			this.PizzaService.savePizza(Pizza);
			return "redirect:/cartas/{cartaId}/pizzas";
		}
	}
	
	@GetMapping(value = "/cartas/{cartaId}/bebida/new" )
	public String initBebidaCreationForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Bebida bebida = new Bebida();
		model.put("bebida", bebida);
		return "bebidas/createOrUpdateBebidaForm";
	}
	@PostMapping(value = "/cartas/{cartaId}/bebida/new")
	public String processBebidaCreationForm(@Valid Bebida bebida, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("bebida", bebida);//importanteeee
			return "bebidas/createOrUpdateBebidaForm";
		} else {
//			BebidaValidator bebidaValidator = new BebidaValidator();
//			ValidationUtils.invokeValidator(bebidaValidator, bebida, result);
			this.BebidaService.saveBebida(bebida);//el tamaño se pone a null
			return "redirect:/cartas/{cartaId}/bebidas";
		}
	}
	
	//crear nuevo Otros
		@GetMapping(value = "/cartas/{cartaId}/otro/new")
		public String initCreationOtrosForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
			Otro otro = new Otro();
			model.put("otro", otro);
			model.put("cartaId", cartaId);
			return "Otros/createOrUpdateOtrosForm";
		}

		//mandar nuevo Otros
		@PostMapping(value = "/cartas/{cartaId}/otro/new")
		public String processCreationOtrosForm(@Valid Otro otro, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
					model.put("otro", otro);//importanteeee
				return "Otros/createOrUpdateOtrosForm";
			}
			else {
//				OtrosValidator ostrosValidator = new OtrosValidator();
//				ValidationUtils.invokeValidator(ostrosValidator, otros, result);
				this.OtrosService.saveOtros(otro);
				return "redirect:/cartas/{cartaId}/otros";
			}
		}
	
	
	//EDIT's
	// iniciar actualizacion de pizza
		@GetMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/edit")
		public String initUpdatePizzaForm(@PathVariable("cartaId") Integer cartaId, @PathVariable("pizzaId") int pizzaId, ModelMap model) {
			Pizza pizza = this.PizzaService.findPizzaById(pizzaId);
			model.put("cartaId", cartaId);
			model.put("pizza", pizza);
			return "pizzas/createOrUpdatePizzaForm";
		}

		// mandar actualizacion de pizza
		@PostMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/edit")
		public String processUpdatePizzaForm(@Valid Pizza Pizza, BindingResult result,
				@PathVariable("pizzaId") int pizzaId) {
			if (result.hasErrors()) {
				return "pizzas/createOrUpdatePizzaForm";
			} else {
				Pizza.setId(pizzaId);
//				PizzaValidator pizzaValidator = new PizzaValidator();
//				ValidationUtils.invokeValidator(pizzaValidator, Pizza, result);
				this.PizzaService.savePizza(Pizza);
				return "redirect:/cartas/{cartaId}/pizzas";
			}
		}
		
		// iniciar actualizacion de bebida
		@GetMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/edit")
		public String initUpdateBebidaForm(@PathVariable("cartaId") Integer cartaId, @PathVariable("bebidaId") int bebidaId, ModelMap model) {
			Bebida bebida = this.BebidaService.findById(bebidaId);
			model.put("bebida", bebida);
			model.put("cartaId", cartaId);
			return "bebidas/createOrUpdateBebidaForm";
		}

		// mandar actualizacion de bebida
		@PostMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/edit")
		public String processUpdateBebidaForm(@Valid Bebida bebida, BindingResult result,
				@PathVariable("bebidaId") int bebidaId) {
			if (result.hasErrors()) {
				return "bebidas/createOrUpdateBebidaForm";
			} else {
				bebida.setId(bebidaId);
//				BebidaValidator bebidaValidator = new BebidaValidator();
//				ValidationUtils.invokeValidator(bebidaValidator, bebida, result);
				this.BebidaService.saveBebida(bebida);
				return "redirect:/cartas/{cartaId}/bebidas";
			}
		}
		
		//iniciar actualizacion de otro
		@GetMapping(value = "/cartas/{cartaId}/otro/{OtrosId}/edit")
		public String initUpdateOtrosForm(@PathVariable("cartaId") Integer cartaId,
				@PathVariable("OtrosId") int OtrosId, ModelMap model) {
			Otro otro = this.OtrosService.findOtrosById(OtrosId);
			model.put("otro", otro);
			model.put("cartaId", cartaId);
			return "Otros/createOrUpdateOtrosForm";
		}
		
		//mandar actualizacion de otro
		@PostMapping(value = "/cartas/{cartaId}/otro/{OtrosId}/edit")
		public String processUpdateOtrosForm(@Valid Otro otro, BindingResult result,
				@PathVariable("OtrosId") int OtrosId, ModelMap model) {
			if (result.hasErrors()) {
				model.put("otro", otro);
				return "Otros/createOrUpdateOtrosForm";
			}
			else {
//				OtrosValidator otrosValidator = new OtrosValidator();
//				ValidationUtils.invokeValidator(otrosValidator, otros, result);
				otro.setId(OtrosId);
				this.OtrosService.saveOtros(otro);
				return "redirect:/cartas/{cartaId}/otros";
			}
		}

		//DELETE's
		// borrar Pizza
		@GetMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/delete")
		public String initDeletePizza(@PathVariable("cartaId") Integer cartaId,
				@PathVariable("pizzaId") int pizzaId, ModelMap model) {
			Pizza pizza = this.PizzaService.findPizzaById(pizzaId);
			this.PizzaService.deletePizza(pizza);
			model.put("cartaId", cartaId);
			return "redirect:/cartas/{cartaId}/pizzas";
		}
		//borrar Bebida
		@GetMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/delete")
		public String initDeleteBebida(@PathVariable("cartaId") Integer cartaId,
				@PathVariable("bebidaId") int bebidaId, ModelMap model) {
			model.put("cartaId", cartaId);
			Bebida bebida = this.BebidaService.findById(bebidaId);
			this.BebidaService.deleteBebida(bebida);
			return "redirect:/cartas/{cartaId}/bebidas";
		}
		
		//borrar Otros
		@GetMapping(value = "/cartas/{cartaId}/otro/{OtrosId}/delete")
		public String initDeleteOtros(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("OtrosId") int OtrosId, ModelMap model) {
			model.put("cartaId", cartaId);
			Otro Otros = this.OtrosService.findOtrosById(OtrosId);
			this.OtrosService.deleteOtros(Otros);
			return "redirect:/cartas/{cartaId}/otros";
		}
		
		@GetMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/deleteFromCarta")
		public String deletePizzaFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("pizzaId") int pizzaId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.PizzaService.deletePizzaFromComposicionCarta(pizzaId);
			return "redirect:/cartas/{cartaId}/VerCarta";
		}
		
		@GetMapping(value = "/cartas/{cartaId}/otros/{otrosId}/deleteFromCarta")
		public String deleteOtroFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("otrosId") int otrosId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.OtrosService.deleteOtroFromComposicionCarta(otrosId);
			return "redirect:/cartas/{cartaId}/VerCarta";
		}
		
		@GetMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/deleteFromCarta")
		public String deleteBebidaFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("bebidaId") int bebidaId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.BebidaService.deleteBebidaFromComposicionCarta(bebidaId);
			return "redirect:/cartas/{cartaId}/VerCarta";
		}
		
		
	//MODEL ATTRIBUTES
	@ModelAttribute("tipoMasa")
    public Collection<tipoMasa> populateTipoMasa() {
        return this.PizzaService.findTipoMasa();
    }
    
    @ModelAttribute("tamanyo")
    public Collection<TamanoProducto> populateTamaño() {
        return this.PizzaService.findTamaño();
    }
  
   @ModelAttribute("ingredientes")
    public Collection<Ingrediente> populateIngrediente() {
    	Collection<Ingrediente> c = this.IngredienteService.findIngredientes();
    	return c;
   }

   
}
