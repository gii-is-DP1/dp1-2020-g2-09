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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pedidos;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Pizzas;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PedidoController {
	
	private PedidoService pedidoService;
	private UserService userService;
	private ClienteService clienteService;
	
	private CartaService CartaService;
	
	private PizzaService PizzaService;
	
	private OtrosService OtrosService;
	
	private BebidaService BebidaService;

	//private IngredienteService ingredienteService;
	
	@Autowired
	public PedidoController(PedidoService pedidoService, UserService userService,ClienteService clienteService,
			AuthoritiesService authoritiesService,PizzaService PizzaService,
			OtrosService OtrosService, BebidaService BebidaService, CartaService CartaService) {
		this.pedidoService = pedidoService;
		this.userService =  userService;
		this.clienteService= clienteService;
		this.PizzaService = PizzaService;
		this.OtrosService = OtrosService;
		this.BebidaService = BebidaService;
		//this.IngredienteService = IngredienteService;
		this.CartaService = CartaService;
	}
	
	
	@ModelAttribute("estadoPedido")
	public Collection<EstadoPedido> populateEstadoPedido() {
		return this.pedidoService.findEstadoPedido();
	}
	
	@ModelAttribute("tipoPago")
	public Collection<TipoPago> populateTipoPago() {
		return this.pedidoService.findTipoPago();
	}
	
	@ModelAttribute("tipoEnvio")
	public Collection<TipoEnvio> populateTipoEnvio() {
		return this.pedidoService.findTipoEnvio();
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	//muestra todos los pedidos
	@GetMapping(value = { "/allPedidos" })
	public String showPedidoList(Map<String, Object> model) {
		Pedidos pedidos = new Pedidos();
		pedidos.getPedidosList().addAll(this.pedidoService.findPedidos());
		model.put("pedidos", pedidos);
		return "pedidos/pedidosList";
	}
	
	//para ver los pedidos del cliente que ha iniciado sesión
	@GetMapping("/pedidos/user")
	public String showMisPedidos(Map<String, Object> model) {
		Pedidos pedidos = new Pedidos();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
	    User usuario = this.userService.findUser(userName).get();
	    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
	    pedidos.getPedidosList().addAll(this.pedidoService.findPedidosByCliente(cliente.getId()));
		model.put("pedidos", pedidos);
		return "pedidos/pedidoUser";
	}
	
	//ver pedidos cocinero
	@GetMapping(value = { "/pedidos/cocinero" })
	public String showPedidoCocinero(Map<String, Object> model) {
		Pedidos pedidos = new Pedidos();
		pedidos.getPedidosList().addAll(this.pedidoService.findPedidoForCocinero());
		model.put("pedidos", pedidos);
		return "pedidos/pedidosList";
	}
	
	//ver pedidos repartidor
		@GetMapping(value = { "/pedidos/repartidor" })
		public String showPedidoReparto(Map<String, Object> model) {
			Pedidos pedidos = new Pedidos();
			pedidos.getPedidosList().addAll(this.pedidoService.findPedidoForRepartidor());
			model.put("pedidos", pedidos);
			return "pedidos/pedidosList";
		}
	
	
	
	//añadir un pedido nuevo
	@GetMapping(value = "/pedidos/new")
	public String initCreationForm(Map<String, Object> model) {
		Pedido pedido = new Pedido();
		model.put("pedido", pedido);
		return "pedidos/createOrUpdatePedidoForm";
	}
			
	//mandar nueva pedido
	@PostMapping(value = "/pedidos/new")
	public String processCreationForm(@Valid Pedido pedido, BindingResult result,  ModelMap model) {
		if (result.hasErrors()) {
			model.put("pedido", pedido);
			return "pedidos/createOrUpdatePedidoForm";
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
			  userDetails = (UserDetails) principal;
			}
			String userName = userDetails.getUsername();
		    User usuario = this.userService.findUser(userName).get();
		    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
			//hay que asociar el pedido creado al usuario que lo ha creado
			pedido.setCliente((Cliente) cliente);
			//por defecto
			pedido.setPrecio(0.0);
			pedido.setFechaPedido(LocalDate.now());
			this.pedidoService.savePedido(pedido);
			return "redirect:/pedidos/user";
		} 
	}

	//iniciar actualizacion pedido
	@GetMapping(value = "/pedidos/{pedidoId}/edit")
	public String initUpdateForm(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
		Pedido pedido = this.pedidoService.findPedidoById(pedidoId);
		model.put("pedido", pedido);
		return "pedidos/createOrUpdatePedidoForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/pedidos/{pedidoId}/edit")
	public String processUpdatePedidoForm(@Valid Pedido pedido, BindingResult result,
			@PathVariable("pedidoId") int pedidoId) {
		if (result.hasErrors()) {
			return "pedidos/createOrUpdatePedidoForm";
		}
		else {
			pedido.setId(pedidoId);
			this.pedidoService.savePedido(pedido);
			return "redirect:/allPedidos";
		}
	}
	
	//BORRAR PEDIDO
	@GetMapping(value = "/pedidos/{pedidoId}/delete")
	public String initDeleteCuenta(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
		Pedido pedido = this.pedidoService.findPedidoById(pedidoId);
		this.pedidoService.deletePedido(pedido);
		return "redirect:/pedidos/user";
	}
	
	//Mostrar cartas de las que coger productos
	@GetMapping(value = { "/pedidos/{pedidoId}/allCartas" })//hacer que lleve a ver carta del tiron
	public String showCartaParaPedidosList(@PathVariable("pedidoId") int pedidoId,
			ModelMap model2,  Map<String, Object> model) {
		LocalDate hoy = LocalDate.now();
		Carta carta = CartaService.findCartaByFechaCreacionYFechaFinal(hoy);
		//Pedido pedido= pedidoService.findPedidoById(pedidoId);
		Integer cartaId = carta.getId();
		//model2.put("cartas", carta);
		//model2.put("pedido", pedido);
		return "redirect:/pedidos/"+pedidoId+"/cartas/"+cartaId+"/verCarta"; 
	}
	
	//Acceso a la carta desde un pedido
		@GetMapping(value = "/pedidos/{pedidoId}/cartas/{cartaId}/verCarta") 
		public String verCartaPedido(@PathVariable("pedidoId") Integer pedidoId,
				@PathVariable("cartaId") Integer cartaId, ModelMap model) {
			model.put("cartaId", cartaId);
			
			Pedido pedido= pedidoService.findPedidoById(pedidoId);
			model.put("pedido",pedido);
			
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
		
		//Aqui tenemos que añadir la pizza seleccionado a un nuevo pedido
		@GetMapping("/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirPizza/{pizzaId}")
		public String anadirPizza(Map<String, Object> model, @PathVariable("pedidoId") int pedidoId,
				@PathVariable("pizzaId") int pizzaId, @PathVariable("cartaId") Integer cartaId) {
			model.put("cartaId", cartaId);
			Pedido pedido = pedidoService.findPedidoById(pedidoId);
			//hacer funcion que incremente el precio del pedido
			Double precioDeMiPedidoAntesDeAnadirPizza = pedido.getPrecio();
			Double cantidadASumar = (double) this.pedidoService.cogerPrecioPizza(pizzaId);
			Double precioDeMiPedidoNuevo = precioDeMiPedidoAntesDeAnadirPizza + cantidadASumar;
			pedido.setPrecio(precioDeMiPedidoNuevo);
			//ya está incrementado el coste del pedido
			model.put("pedido", pedido);
			this.pedidoService.añadirPizzaAPedido(pedidoId, pizzaId);
			return "redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen";
		}
		
		//Aqui tenemos que añadir la bebida seleccionado a un nuevo pedido
		@GetMapping("/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirBebida/{bebidaId}")
		public String anadirBebida(Map<String, Object> model, @PathVariable("pedidoId") int pedidoId,
				@PathVariable("bebidaId") int bebidaId, @PathVariable("cartaId") Integer cartaId) {
			model.put("cartaId", cartaId);
			Pedido pedido = pedidoService.findPedidoById(pedidoId);
			//hacer funcion que incremente el precio del pedido
			Double precioDeMiPedidoAntesDeAnadirPizza = pedido.getPrecio();
			Double cantidadASumar = (double) this.pedidoService.cogerPrecioBebida(bebidaId);
			Double precioDeMiPedidoNuevo = precioDeMiPedidoAntesDeAnadirPizza + cantidadASumar;
			pedido.setPrecio(precioDeMiPedidoNuevo);
			//ya está incrementado el coste del pedido
			model.put("pedido", pedido);
			this.pedidoService.añadirBebidaAPedido(pedidoId, bebidaId);
			//this.pedidoService.savePedido(pedido);
			return "redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen";
		}
		
		//Aqui tenemos que añadir los otros seleccionado a un nuevo pedido
		@GetMapping("/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirOtros/{otrosId}")
		public String anadirOtros(Map<String, Object> model, @PathVariable("pedidoId") int pedidoId,
				@PathVariable("otrosId") int otrosId, @PathVariable("cartaId") Integer cartaId) {
			model.put("cartaId", cartaId);
			Pedido pedido = pedidoService.findPedidoById(pedidoId);
			//hacer funcion que incremente el precio del pedido
			Double precioDeMiPedidoAntesDeAnadirPizza = pedido.getPrecio();
			Double cantidadASumar = (double) this.pedidoService.cogerPrecioOtros(otrosId);
			Double precioDeMiPedidoNuevo = precioDeMiPedidoAntesDeAnadirPizza + cantidadASumar;
			pedido.setPrecio(precioDeMiPedidoNuevo);
			//ya está incrementado el coste del pedido
			model.put("pedido", pedido);
			this.pedidoService.añadirOtrosAPedido(pedidoId, otrosId);
			//this.pedidoService.savePedido(pedido);
			return "redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen";
		}
		
		//Resumen de un pedido
		@GetMapping(value = "/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen") 
		public String verResumenPedido(@PathVariable("pedidoId") Integer pedidoId,
				@PathVariable("cartaId") Integer cartaId, ModelMap model) {
			
			model.put("cartaId", cartaId);
			Pedido pedido= pedidoService.findPedidoById(pedidoId);
			model.put("pedido",pedido);
			
			//Recogemos las pizzas de la tabla y la guardamos en el modelo
			List<Integer> listaIdPizzas = PizzaService.findPizzaPedidoById(pedidoId);
			Pizzas listaPizzas = new Pizzas();
			for(int i=0; i<listaIdPizzas.size(); i++) {
				Integer pizzaId = listaIdPizzas.get(i);
				Pizza pizza = this.PizzaService.findPizzaById(pizzaId);
				listaPizzas.getPizzasList().add(pizza);
			}
			model.put("pizzas", listaPizzas);
			
			List<Integer> listaIdBebidas = BebidaService.findBebidaPedidoById(pedidoId);
			Bebidas listaBebidas = new Bebidas();
			for(int i=0; i<listaIdBebidas.size(); i++) {
				Integer bebidaId = listaIdBebidas.get(i);
				Bebida bebida = this.BebidaService.findById(bebidaId);
				listaBebidas.getBebidasList().add(bebida);
			}
			model.put("bebidas", listaBebidas);
			
			List<Integer> listaIdOtros = OtrosService.findOtrosPedidoById(pedidoId);
			Otros listaOtros = new Otros();
			for(int i=0; i<listaIdOtros.size(); i++) {
				Integer otroId = listaIdOtros.get(i);
				Otro otro = this.OtrosService.findOtrosById(otroId);
				listaOtros.getOtrosLista().add(otro);
			}
			model.put("otros", listaOtros);

			return "pedidos/resumenPedido";
		}
		
		//Poner En cocina al finalizar un pedido
		@GetMapping(value = "/pedidos/{pedidoId}/finalizarPedido")
		public String enCocina(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
			pedidoService.putEnCocina(pedidoId);
			return "redirect:/pedidos/user";
		}
				
				
		//Cambiar En cocina a Preparado
		@GetMapping(value = "/cocinero/{pedidoId}/estadoPedido")
		public String enCocinaPreparado(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
				pedidoService.putPreparado(pedidoId);
			return "redirect:/pedidos/cocinero";
		}
		
		//Cambiar Preparado a En Reparto Y En Reparto a Entregado
		@GetMapping(value = "/repartidor/{pedidoId}/estadoPedido")
			public String PreparadoEnReparto(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
				Pedido pedido=this.pedidoService.findPedidoById(pedidoId);
				if(pedido.getEstadoPedido().getId()==2) {
					pedidoService.putEnReparto(pedidoId);
				}else
					pedidoService.putEntregado(pedidoId);
			return "redirect:/pedidos/repartidor";
		}
		
		
		
		
	

}
