package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Bebidas;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CartaController {

	
	private CartaService CartaService;
	
	private PizzaService PizzaService;
	
	private OtrosService OtrosService;
	
	private BebidaService BebidaService;

	private IngredienteService IngredienteService;
	
	private OfertaService OfertaService;
	

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
	
	@InitBinder("carta")
	public void initCartaBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new CartaValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@Autowired
	public CartaController(CartaService CartaService, PizzaService PizzaService,
			OtrosService OtrosService, BebidaService BebidaService,
			IngredienteService IngredienteService,OfertaService OfertaService) {
		this.CartaService = CartaService;
		this.PizzaService = PizzaService;
		this.OtrosService = OtrosService;
		this.BebidaService = BebidaService;
		this.IngredienteService = IngredienteService;
		this.OfertaService = OfertaService;
	}

	@GetMapping(value = { "/allCartas" })
	public String showCartaList(Map<String, Object> model) {
		List<Carta> cartas= CartaService.findCartas();
		model.put("cartas", cartas);
		log.info("Mostrando todas las cartas");
		return "cartas/cartasList";
	}
	
	@GetMapping(value = { "/cartas/cartaActiva" })
	public String showCartaActiva(Map<String, Object> model) {
		LocalDate hoy = LocalDate.now();
		Carta carta = CartaService.findCartaByFechaCreacionYFechaFinal(hoy);
		Integer cartaId = carta.getId();
		log.info("Mostrando carta activa");
		return "redirect:/cartas/"+cartaId+"/VerCarta";
	}
	
	
	//añadir una Carta nueva
	@GetMapping(value = "/cartas/new")
	public String initCreationForm(Map<String, Object> model) {
		Carta Carta = new Carta();
		model.put("carta", Carta);
		log.info("Iniciar creacion de una carta");
		return "cartas/createOrUpdateCartaForm";
	}

	//mandar nueva Carta
	@PostMapping(value = "/cartas/new")
	public String processCreationForm(@Valid Carta carta, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			log.warn("Fallo al crear una carta");
			model.put("carta", carta);
			return "cartas/createOrUpdateCartaForm";
		}
		else { 
			List<Carta> cartas = this.CartaService.findCartas();
			LocalDate fechaCreacion = cartas.get(cartas.size()-1).getFechaCreacion().plusYears(1);
			LocalDate fechaFinal = cartas.get(cartas.size()-1).getFechaFinal().plusYears(1);
			carta.setFechaCreacion(fechaCreacion);
			carta.setFechaFinal(fechaFinal);
			this.CartaService.saveCarta(carta);
			log.info("Carta guardada");
			return "redirect:/allCartas";
		}
	}

	//borrar Carta
	@GetMapping(value = "/cartas/{cartaId}/delete")
	public String initDeleteCarta(@PathVariable("cartaId") int cartaId, ModelMap model) {
		Carta carta = this.CartaService.findCartaById(cartaId);
		this.CartaService.deleteCarta(carta);
		log.info("Borrar una carta");
		return "redirect:/allCartas";
	}
	
	//Acceso a la carta
	@GetMapping(value = "/cartas/{cartaId}/VerCarta") 
	public String verCarta(@PathVariable("cartaId") Integer cartaId, ModelMap model) {
		
		model.put("cartaId", cartaId);
		
		List<Integer> listaIdPizzas = this.PizzaService.findIdPizzaById(cartaId);
		Pizzas listaPizzas = new Pizzas();
		for(int i=0; i<listaIdPizzas.size(); i++) {
			Integer pizzaId = listaIdPizzas.get(i);
			Pizza pizza = this.PizzaService.findPizzaById(pizzaId);
			listaPizzas.getPizzasList().add(pizza);
		}
		model.put("pizzas", listaPizzas);
		log.info("Obtenidas las pizzas para la carta");
		
		List<Integer> listaIdBebidas = this.BebidaService.findIdBebidaByCartaId(cartaId);
		Bebidas listaBebidas = new Bebidas();
		for(int i=0; i<listaIdBebidas.size(); i++) {
			Integer bebidaId = listaIdBebidas.get(i);
			Bebida bebida = this.BebidaService.findById(bebidaId);
			listaBebidas.getBebidasList().add(bebida);
		}
		model.put("bebidas", listaBebidas);
		log.info("Obtenidas las bebidas para la carta");
		
		List<Integer> listaIdOtros = this.OtrosService.findIdOtroById(cartaId);
		Otros listaOtros = new Otros();
		for(int i=0; i<listaIdOtros.size(); i++) {
			Integer otroId = listaIdOtros.get(i);
			Otro otro = this.OtrosService.findOtrosById(otroId);
			listaOtros.getOtrosLista().add(otro);
		}
		model.put("otros", listaOtros);
		log.info("Obtenidas los otros para la carta");
		
		LocalDate hoy=LocalDate.now();
		
		List<Oferta>  ofertas = OfertaService.findOfertasTrueEnTiempo(hoy);
		model.put("ofertas",ofertas);
		
		return "cartas/verCarta";
	}
	
	//GETTER's
	@GetMapping(value = "/cartas/{cartaId}/pizzas")
	public String showPizzaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Pizzas pizzas = new Pizzas();
		pizzas.getPizzasList().addAll(this.PizzaService.findPizzas());
		model.put("Pizzas", pizzas);  
		log.info("Obtenidas las pizzas");
		return "pizzas/pizzasList";
	}
	
	@GetMapping(value = "/cartas/{cartaId}/bebidas")
	public String showBebidaLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Bebidas bebidas = new Bebidas();
		bebidas.getBebidasList().addAll(this.BebidaService.findBebidas());
		model.put("bebidas", bebidas);
		log.info("Obtenidas las bebidas");
		return "bebidas/bebidasList";
	}
	
	@GetMapping(value = "/cartas/{cartaId}/otros" )
	public String showOtrosLista(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Otros otros = new Otros();
		otros.getOtrosLista().addAll(this.OtrosService.findOtros());
		model.put("otros", otros);
		log.info("Obtenidos los otros");
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
			log.info("Pizza añadida a la carta");
			return "redirect:/cartas/{cartaId}/VerCarta";
		}else {
			log.warn("No se pueden meter una pizza que ya esta en esa carta");
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
			log.info("Bebida añadida a la carta");
	    	return "redirect:/cartas/{cartaId}/VerCarta";
		}else {
			log.warn("No se puede meter una bebida que ya esta en esa carta");
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
			log.info("Otro añadido a la carta");
	    	return "redirect:/cartas/{cartaId}/VerCarta"; 
		}else {
			log.warn("No se puede meter un otro que ya esta en esa carta");
			return "redirect:/OtroDuplicadaEnCarta";
		}
    	
    }
	
	
	//POST's NEW
	@GetMapping(value = "/cartas/{cartaId}/pizza/new" )
	public String initCreationForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Pizza pizza = new Pizza();
		model.put("pizza", pizza);
		log.info("Iniciando creacion de pizza");
		return "pizzas/createOrUpdatePizzaForm";
	}
	@PostMapping(value = "/cartas/{cartaId}/pizza/new")
	public String processCreationForm(@Valid Pizza Pizza, BindingResult result,ModelMap model) {
		if (result.hasErrors()) {
			model.put("pizza", Pizza);
			log.warn("Fallo al crear la pizza");
			return "pizzas/createOrUpdatePizzaForm";
		} else {
			this.PizzaService.savePizza(Pizza);
			log.info("Pizza guardada");
			return "redirect:/cartas/{cartaId}/pizzas";
		}
	}
	
	@GetMapping(value = "/cartas/{cartaId}/bebida/new" )
	public String initBebidaCreationForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
		model.put("cartaId", cartaId);
		Bebida bebida = new Bebida();
		model.put("bebida", bebida);
		log.info("Iniciar creacion de bebida");
		return "bebidas/createOrUpdateBebidaForm";
	}
	@PostMapping(value = "/cartas/{cartaId}/bebida/new")
	public String processBebidaCreationForm(@Valid Bebida bebida, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("bebida", bebida);
			log.error("Fallo al crear una bebida");
			return "bebidas/createOrUpdateBebidaForm";
		} else {
			this.BebidaService.saveBebida(bebida);
			log.info("Bebida guardada");
			return "redirect:/cartas/{cartaId}/bebidas";
		}
	}
	
	//crear nuevo Otros
		@GetMapping(value = "/cartas/{cartaId}/otro/new")
		public String initCreationOtrosForm(@PathVariable("cartaId") Integer cartaId, Map<String, Object> model) {
			Otro otro = new Otro();
			model.put("otro", otro);
			model.put("cartaId", cartaId);
			log.info("Iniciar creacion de otro");
			return "Otros/createOrUpdateOtrosForm";
		}

		//mandar nuevo Otros
		@PostMapping(value = "/cartas/{cartaId}/otro/new")
		public String processCreationOtrosForm(@Valid Otro otro, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
					model.put("otro", otro);
					log.error("Fallo en la creacion de otro");
				return "Otros/createOrUpdateOtrosForm";
			}
			else {
				this.OtrosService.saveOtros(otro);
				log.info("Otro guardado");
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
			log.info("Iniciar actualizacion de una pizza");
			return "pizzas/createOrUpdatePizzaForm";
		}

		// mandar actualizacion de pizza
		@PostMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/edit")
		public String processUpdatePizzaForm(@Valid Pizza Pizza, BindingResult result, @PathVariable("cartaId") Integer cartaId,
				@PathVariable("pizzaId") int pizzaId, @RequestParam(value = "version", required=false) Integer version, ModelMap model) {
			Pizza pizzaToUpdate = this.PizzaService.findPizzaById(pizzaId);
			if(pizzaToUpdate.getVersion()!=version) {
			model.put("message","Problema de concurrencia a la hora de editar la pizza. "
					+ "Inténtelo de nuevo más tarde.");
			log.info("Problema de concurrencia a la hora de editar la pizza.");
			return initUpdatePizzaForm(cartaId,pizzaId,model);
			}
			if (result.hasErrors()) {
				Pizza.setId(pizzaId);
				log.error("Fallo en la actualizacion de una pizza");
				return "pizzas/createOrUpdatePizzaForm";
			} else {
				Pizza.setId(pizzaId);
				this.PizzaService.savePizza(Pizza);
				log.info("Pizza guardada");
				return "redirect:/cartas/{cartaId}/pizzas";
			}
		}
		

		
		// iniciar actualizacion de bebida
		@GetMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/edit")
		public String initUpdateBebidaForm(@PathVariable("cartaId") Integer cartaId, @PathVariable("bebidaId") int bebidaId,ModelMap model) {
			Bebida bebida = this.BebidaService.findById(bebidaId);
			model.put("bebida", bebida);
			model.put("cartaId", cartaId);
			log.info("Iniciar actualizacion de una bebida");
			return "bebidas/createOrUpdateBebidaForm";
		}

		// mandar actualizacion de bebida
		@PostMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/edit")
		public String processUpdateBebidaForm(@Valid Bebida bebida, BindingResult result, @PathVariable("cartaId") Integer cartaId,
				@PathVariable("bebidaId") int bebidaId, @RequestParam(value = "version", required=false) Integer version, ModelMap model) {
			Bebida bebidaToUpdate = this.BebidaService.findById(bebidaId);
			if(bebidaToUpdate.getVersion()!=version) {
			model.put("message","Problema de concurrencia a la hora de editar la bebida. "
					+ "Inténtelo de nuevo más tarde.");
			log.info("Problema de concurrencia a la hora de editar la bebida.");
			return initUpdateBebidaForm(cartaId,bebidaId,model);
		}
			if (result.hasErrors()) {
				bebida.setId(bebidaId);
				log.error("Fallo en la actualizacion de una bebida");
				return "bebidas/createOrUpdateBebidaForm";
			} else {
				bebida.setId(bebidaId);
				this.BebidaService.saveBebida(bebida);
				log.info("Bebida guardada");
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
			log.info("Iniciar actualizacion de un otro");
			return "Otros/createOrUpdateOtrosForm";
		}
		
		//mandar actualizacion de otro
		@PostMapping(value = "/cartas/{cartaId}/otro/{OtrosId}/edit")
		public String processUpdateOtrosForm(@Valid Otro otro, BindingResult result, @PathVariable("cartaId") Integer cartaId,
				@PathVariable("OtrosId") int OtrosId, ModelMap model, @RequestParam(value = "version", required=false) Integer version) {
			Otro otroToUpdate = this.OtrosService.findOtrosById(OtrosId);
			if(otroToUpdate.getVersion()!=version) {
				model.put("message","Problema de concurrencia a la hora de editar otro producto. "
						+ "Inténtelo de nuevo más tarde.");
				log.info("Problema de concurrencia a la hora de editar otro producto.");
				return initUpdateOtrosForm(cartaId,OtrosId,model);
			}
			if (result.hasErrors()) {
				otro.setId(OtrosId);
				log.error("Fallo en la actualizacion de un otro");
				model.put("otro", otro);
				return "Otros/createOrUpdateOtrosForm";
			}
			else {
				otro.setId(OtrosId);
				this.OtrosService.saveOtros(otro);
				log.info("Otro guardado");
				return "redirect:/cartas/{cartaId}/otros";
			}
		}

		
		//Quitar de la carta
		@GetMapping(value = "/cartas/{cartaId}/pizza/{pizzaId}/deleteFromCarta")
		public String deletePizzaFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("pizzaId") int pizzaId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.PizzaService.deletePizzaFromComposicionCarta(pizzaId);
			log.info("Borrar la pizza de la carta");
			return "redirect:/cartas/{cartaId}/VerCarta";
		}
		
		@GetMapping(value = "/cartas/{cartaId}/otros/{otrosId}/deleteFromCarta")
		public String deleteOtroFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("otrosId") int otrosId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.OtrosService.deleteOtroFromComposicionCarta(otrosId);
			log.info("Borrar la bebida de la carta");
			return "redirect:/cartas/{cartaId}/VerCarta";
		}
		
		@GetMapping(value = "/cartas/{cartaId}/bebida/{bebidaId}/deleteFromCarta")
		public String deleteBebidaFromCarta(@PathVariable("cartaId") Integer cartaId, 
				@PathVariable("bebidaId") int bebidaId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			this.BebidaService.deleteBebidaFromComposicionCarta(bebidaId);
			log.info("Borrar el otro de la carta");
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
