package org.springframework.samples.petclinic.web;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.service.AuthoritiesService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
	
	private final ClienteService clienteService;
	//private final UserService userService;

	@Autowired
	public ClienteController(ClienteService clienteService, UserService userService,
			AuthoritiesService authoritiesService) {
		this.clienteService = clienteService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allCuentas" })
	public String showCuentaList(Map<String, Object> model) {
		Clientes clientes = new Clientes();
		clientes.getClientesList().addAll(this.clienteService.findCuentas());
		model.put("clientes", clientes);
		return "clientes/clientesList";
	}

	//crear nuevo cliente
	@GetMapping(value = "/cuentas/new")
	public String initCreationForm(Map<String, Object> model) {
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		return "clientes/createOrUpdateCuentaForm";
	}

	//mandar nuevo cliente
	@PostMapping(value = "/cuentas/new")
	public String processCreationForm(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
			this.clienteService.saveCliente(cliente);
			return "redirect:/";
		}
	}
	
	//para ver los datos de mi perfil de cliente
	@GetMapping("/cuentas/{cuentaId}")
	public ModelAndView showCliente(@PathVariable("cuentaId") int cuentaId) {
		ModelAndView mav = new ModelAndView("clientes/clienteDetails");
		mav.addObject(this.clienteService.findCuentaById(cuentaId));
		return mav;
	}
	
//	@ModelAttribute("cuenta")
//	public Cliente findCliente(@PathVariable("cuentaId") int cuentaId) {
//		return this.clienteService.findCuentaById(cuentaId);
//	}

	//iniciar actualizacion
	@GetMapping(value = "/cuentas/{cuentaId}/edit")
	public String initUpdateForm(@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		Cliente cuenta = this.clienteService.findCuentaById(cuentaId);
		model.put("cuenta", cuenta);
		return "clientes/createOrUpdateCuentaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/cuentas/{cuentaId}/edit")
	public String processUpdateCuentaForm(@Valid Cliente cliente, BindingResult result,
			@PathVariable("cuentaId") int cuentaId) {
		if (result.hasErrors()) {
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
			cliente.setId(cuentaId);
			this.clienteService.saveCliente(cliente);
			return "redirect:/allCuentas";
		}
	}
	
	//borrar cliente
	@GetMapping(value = "/cuentas/{cuentaId}/delete")
	public String initDeleteCuenta(@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		Cliente cliente = this.clienteService.findCuentaById(cuentaId);
		this.clienteService.deleteCliente(cliente);
		return "redirect:/allCuentas";
	}

//	@DeleteMapping(value = "/cuentas/{cuentaId}/delete")
//	public String deleteCuenta(@PathVariable("cuentaId") int cuentaId) {
//		Cliente cliente = this.clienteService.findCuentaById(cuentaId);
//		this.clienteService.deleteCliente(cliente);
//		return "redirect:/allCuentas";
//	}
}
