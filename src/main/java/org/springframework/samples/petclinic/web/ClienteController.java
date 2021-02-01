package org.springframework.samples.petclinic.web;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ClienteController {
	
	private final ClienteService clienteService;
	private final UserService userService;
	private final PedidoService pedidoService;

	@Autowired
	public ClienteController(ClienteService clienteService, UserService userService,
			PedidoService pedidoService) {
		this.clienteService = clienteService;
		this.userService =  userService;
		this.pedidoService = pedidoService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder("cliente")
	public void initClienteBinder(WebDataBinder dataBinder) {
		//dataBinder.setValidator(new UserValidator());
		dataBinder.setValidator(new CuentaValidator());
	}
	
	@GetMapping(value = { "/allCuentas" })
	public String showCuentaList(Map<String, Object> model) {
		Clientes clientes = new Clientes();
		clientes.getClientesList().addAll(this.clienteService.findCuentas());
		model.put("clientes", clientes);
		log.info("Mostrando los clientes");
		return "clientes/clientesList";
	}

	//crear nuevo cliente
	@GetMapping(value = "/clientes/new")
	public String initCreationForm(Map<String, Object> model) {
		Cliente cuenta = new Cliente();
		model.put("cliente", cuenta);
		log.info("Iniciando creación del cliente");
		return "clientes/createOrUpdateCuentaForm";
	}

	//mandar nuevo cliente
	@PostMapping(value = "/clientes/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("cliente", cliente);
			log.error("Se ha rellenado mal el formulario de creacion");
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
//			CuentaValidator cuentaValidator = new CuentaValidator();
//			ValidationUtils.invokeValidator(cuentaValidator, cliente, result);
			cliente.setFechaAlta(LocalDate.now());
			NivelSocio nivelSocio = new NivelSocio();
			nivelSocio.setName("No tiene nivel de socio");
			cliente.setNivelSocio(nivelSocio);
			
			this.clienteService.saveCliente(cliente);
			log.info("Creado del cliente finalizado");
			return "redirect:/";
		}
	}
	
	//para ver los datos de mi perfil de cliente que ha iniciado sesión
	@GetMapping("/clientes/DetallesPerfil")
	public ModelAndView showCliente() {
		ModelAndView mav = new ModelAndView("clientes/clienteDetails");
		Authentication auth = SecurityContextHolder
	            .getContext()
	            .getAuthentication();
	    UserDetails userDetail = (UserDetails) auth.getPrincipal();
	    User usuario = this.userService.findUser(userDetail.getUsername()).get();
	    Cliente cliente = this.clienteService.findCuentaByUser(usuario);
	  //Nivel socio a determinar
	    NivelSocio nivelSocio = new NivelSocio();
	    List<Pedido> pedidosCliente = this.pedidoService
				.findPedidosByCliente(cliente.getId());
		Double acum = 0.;
		for(int i=0; i<pedidosCliente.size(); i++) {
			acum += pedidosCliente.get(i).getPrecio();
		}
		if(acum<100) {//mirar si eso las fechas
			nivelSocio.setName("No tiene nivel de socio");
			cliente.setNivelSocio(nivelSocio);
		}else if(acum<200) {
			nivelSocio.setName("BRONCE");
			cliente.setNivelSocio(nivelSocio);
		}else if(acum<300) {
			nivelSocio.setName("PLATA");
			cliente.setNivelSocio(nivelSocio);
		}else {
			nivelSocio.setName("ORO");
			cliente.setNivelSocio(nivelSocio);
		}
	    mav.addObject(cliente);
	    log.info("Devolviendo detalles del cliente");
		return mav;
	}

	//iniciar actualizacion
	@GetMapping(value = "/clientes/{cuentaId}/edit")
	public String initUpdateForm(@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		Cliente cuenta = this.clienteService.findCuentaById(cuentaId);
		model.put("cliente", cuenta);
		log.info("Iniciando actualizacion");
		return "clientes/createOrUpdateCuentaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/clientes/{cuentaId}/edit")
	public String processUpdateCuentaForm(@Valid Cliente cliente, BindingResult result,
			@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("cliente", cliente);
			log.error("Fallos en el formulario de actualizacion");
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
			cliente.setId(cuentaId);
			this.clienteService.saveCliente(cliente);
			log.info("Cliente actualizado");
			return "clientes/clienteDetails";
		}
	}
	
	//borrar cliente
	@GetMapping(value = "/clientes/{cuentaId}/delete")
	public String initDeleteCuenta(@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		Cliente cliente = this.clienteService.findCuentaById(cuentaId);
		this.clienteService.deleteCliente(cliente);
		log.info("Cliente borrado");
		return "redirect:/allCuentas";
	}

//	@DeleteMapping(value = "/cuentas/{cuentaId}/delete")
//	public String deleteCuenta(@PathVariable("cuentaId") int cuentaId) {
//		Cliente cliente = this.clienteService.findCuentaById(cuentaId);
//		this.clienteService.deleteCliente(cliente);
//		return "redirect:/allCuentas";
//	}
}
