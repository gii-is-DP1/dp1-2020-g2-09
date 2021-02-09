package org.springframework.samples.petclinic.web;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Ofertas;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.service.BebidaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller

public class OfertaController {
	
	private final OfertaService ofertaService;
	private final BebidaService bebidaService;
	private final PizzaService pizzaService;
	private final OtrosService otrosService;

	
	@Autowired
	public OfertaController(OfertaService ofertaService,OtrosService otrosService,PizzaService pizzaService,BebidaService bebidaService) {
		this.ofertaService = ofertaService;
		this.bebidaService = bebidaService;
		this.pizzaService = pizzaService;
		this.otrosService = otrosService;
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
	public String showOfertaList(Map<String, Object> model) {
		Ofertas ofertas = new Ofertas();
		ofertas.getOfertasList().addAll(this.ofertaService.findOfertas());
		model.put("ofertas", ofertas);
		model.put("hoy",LocalDate.now());
		log.info("Mostrando lista de ofertas");
		return "ofertas/ofertasList";
	}

	//crear nueva oferta
	@GetMapping(value = "/ofertas/new")
	public String initCreationForm(Map<String, Object> model) {
		Oferta oferta = new Oferta();
		List<Pizza> pizzas=pizzaService.findPizzas();
		List<Bebida> bebidas=bebidaService.findBebidas();
		List<Otro> otros=otrosService.findOtros();
		model.put("pizzas", pizzas);
		model.put("bebidas", bebidas);
		model.put("otros", otros);
		model.put("oferta", oferta);
		return "ofertas/createOrUpdateOfertaForm";
	}

	//mandar nueva oferta
	@PostMapping(value = "/ofertas/new")
	public String processCreationForm(@Valid Oferta oferta,BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("oferta", oferta);
			List<Pizza> pizzas=pizzaService.findPizzas();
			List<Bebida> bebidas=bebidaService.findBebidas();
			List<Otro> otros=otrosService.findOtros();
			model.put("pizzas", pizzas);
			model.put("bebidas", bebidas);
			model.put("otros", otros);
			log.warn("Errores en la creación de la oferta");
			return "ofertas/createOrUpdateOfertaForm";
		}
		else {
			this.ofertaService.saveOferta(oferta);
			log.info("Nueva oferta creada con éxito");
			return "redirect:/ofertas/"+oferta.getId()+"/anadirProductos";
		}
		
	}
	//añadir productos a ala oferta
		@GetMapping(value = "/ofertas/{ofertaId}/anadirProductos")
		public String anadirProductos(@PathVariable("ofertaId") int ofertaId, ModelMap model) {
			Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
			List<Pizza> pizzas=pizzaService.findPizzas();
			List<Bebida> bebidas=bebidaService.findBebidas();
			List<Otro> otros=otrosService.findOtros();
			model.put("pizzas", pizzas);
			model.put("bebidas", bebidas);
			model.put("otros", otros);
			model.put("oferta", oferta);
			log.info("Mostrando lista de productos para añadir en la oferta");
			return "ofertas/anadirProductos";
		}
		
		@PostMapping(value = "/ofertas/{ofertaId}/anadirProductos")
		public String anadirProductos(@Valid Oferta oferta, BindingResult result,@PathVariable("ofertaId") int ofertaId, ModelMap model) {
			log.info("Productos añadidos con exito");
				return "redirect:/allOfertas";
		
		}
	//iniciar actualizacion oferta
	@GetMapping(value = "/ofertas/{ofertaId}/edit")
	public String initUpdateForm(@PathVariable("ofertaId") int ofertaId, ModelMap model) {
		Oferta oferta = this.ofertaService.findOfertaById(ofertaId);
		List<Pizza> pizzas=pizzaService.findPizzas();
		List<Bebida> bebidas=bebidaService.findBebidas();
		List<Otro> otros=otrosService.findOtros();
		model.put("pizzas", pizzas);
		model.put("bebidas", bebidas);
		model.put("otros", otros);
		model.put("oferta", oferta);
		log.info("Mostrando lista de productos para actualizar en la oferta");
		return "ofertas/createOrUpdateOfertaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/ofertas/{ofertaId}/edit")
	public String processUpdatePedidoForm(@Valid Oferta oferta, BindingResult result,@PathVariable("ofertaId") int ofertaId, ModelMap model) {
		if (result.hasErrors()) {
			oferta.setId(ofertaId);
			List<Pizza> pizzas=pizzaService.findPizzas();
			List<Bebida> bebidas=bebidaService.findBebidas();
			List<Otro> otros=otrosService.findOtros();
			List<Pizza> pizzasEnOferta=ofertaService.findPizzasEnOfertaByOfertaId(ofertaId);
			List<Bebida> bebidasEnOferta=ofertaService.findBebidasEnOfertaByOfertaId(ofertaId);
			List<Otro> otrosEnOferta=ofertaService.findOtrosEnOfertaByOfertaId(ofertaId);
			oferta.setPizzasEnOferta(pizzasEnOferta);
			oferta.setBebidasEnOferta(bebidasEnOferta);
			oferta.setOtrosEnOferta(otrosEnOferta);
			//model.put(key, value);
			model.put("pizzas", pizzas);
			model.put("bebidas", bebidas);
			model.put("otros", otros);
			model.put("oferta", oferta);
			log.warn("Los productos no se pudieron añadir a la oferta");
			return "ofertas/createOrUpdateOfertaForm";
		}
		else {
			oferta.setId(ofertaId);
			this.ofertaService.saveOferta(oferta);
			log.info("Productos añadidos a la oferta");
			return "redirect:/allOfertas";
		}
	}
	
	@RequestMapping(value = "/ofertas/{ofertaId}/changeState",method = RequestMethod.GET)
	public String changeOfertaState(@PathVariable("ofertaId") int ofertaId, ModelMap model){
	
			Oferta oferta= ofertaService.findOfertaById(ofertaId);
			LocalDate hoy= LocalDate.now();
			if(oferta.getEstadoOferta().equals(true) && oferta.getFechaFinal().isBefore(hoy)) {
				oferta.setFechaInicial(hoy);
				oferta.setFechaFinal(hoy.plusDays(30));		
			} else if(oferta.getEstadoOferta().equals(true)) {
				oferta.setEstadoOferta(false);
			}else {
				oferta.setEstadoOferta(true);
				oferta.setFechaInicial(hoy);
				oferta.setFechaFinal(hoy.plusDays(30));
			}
			this.ofertaService.saveOferta(oferta);
			log.info("Estado de la oferta cambiado");
			return "redirect:/allOfertas";
		}

			@GetMapping("/ofertas/{ofertaId}/anadirPizza/{pizzaId}")
			public String anadirPizza(ModelMap model, @PathVariable("ofertaId") int ofertaId,@PathVariable("pizzaId") int pizzaId) {
				Pizza p=pizzaService.findPizzaById(pizzaId);
				Oferta oferta = ofertaService.findOfertaById(ofertaId);
				model.put("oferta", oferta);
				model.put("pizzaEnOferta",p);
				//model.put("pizzasEnOferta", pizzasEnOferta);
				this.ofertaService.asociarOfertaAPizza(ofertaId, pizzaId);
				log.info("Añadir una pizza");
				return "redirect:/ofertas/{ofertaId}/anadirProductos";
			}
			@GetMapping("/ofertas/{ofertaId}/anadirBebida/{bebidaId}")
			public String anadirBebida(ModelMap model, @PathVariable("ofertaId") int ofertaId,@PathVariable("bebidaId") int bebidaId) {
				Bebida b=bebidaService.findById(bebidaId);
				Oferta oferta = ofertaService.findOfertaById(ofertaId);
				model.put("oferta", oferta);
				model.put("bebidaEnOferta",b);
				this.ofertaService.asociarOfertaABebida(ofertaId, bebidaId);
				log.info("Añadir una bebida");
				return "redirect:/ofertas/{ofertaId}/anadirProductos";
			}
			@GetMapping("/ofertas/{ofertaId}/anadirOtro/{otroId}")
			public String anadirOtro(ModelMap model, @PathVariable("ofertaId") int ofertaId,@PathVariable("otroId") int otroId) {
				Otro o=otrosService.findOtrosById(otroId);
				Oferta oferta = ofertaService.findOfertaById(ofertaId);
				model.put("oferta", oferta);
				model.put("otroEnOferta",o);
				this.ofertaService.asociarOfertaAOtro(ofertaId, otroId);
				log.info("Añadir una otro");
				return "redirect:/ofertas/{ofertaId}/anadirProductos";
			}
			
}
