package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pedidos;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.Authentication;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PedidoController {
	
	private final PedidoService pedidoService;
	private final UserService userService;
	private final ClienteService clienteService;

	@Autowired
	public PedidoController(PedidoService pedidoService, UserService userService,ClienteService clienteService,
			AuthoritiesService authoritiesService) {
		this.pedidoService = pedidoService;
		this.userService =  userService;
		this.clienteService= clienteService;
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
	
	@GetMapping(value = { "/allPedidos" })
	public String showPedidoList(Map<String, Object> model) {
		Pedidos pedidos = new Pedidos();
		pedidos.getPedidosList().addAll(this.pedidoService.findPedidos());
		model.put("pedidos", pedidos);
		return "pedidos/pedidosList";
	}
	
	//para ver los pedidos de cliente que ha iniciado sesión
	@GetMapping("/pedidos/user")
	public String showMisPedidos(Map<String, Object> model) {
		Pedidos pedidos = new Pedidos();
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    User usuario = this.userService.findUser(userDetail.getUsername()).get();
	    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
	    pedidos.getPedidosList().addAll(this.pedidoService.findPedidosByCliente(cliente.getId()));
		model.put("pedidos", pedidos);
		return "pedidos/pedidoUser";
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
			this.pedidoService.savePedido(pedido);
			return "redirect:/allPedidos";
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
	
	//borrar pedido
	@GetMapping(value = "/pedidos/{pedidoId}/delete")
	public String initDeleteCuenta(@PathVariable("pedidoId") int pedidoId, ModelMap model) {
		Pedido pedido = this.pedidoService.findPedidoById(pedidoId);
		this.pedidoService.deletePedido(pedido);
		return "redirect:/allPedidos";
	}

}
