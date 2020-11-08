package org.springframework.samples.petclinic.web;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.service.CuentaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
	
		private final ClienteService clienteService;
		private final CuentaService cuentaService;

		@Autowired
		public ClienteController(ClienteService clienteService, CuentaService cuentaService) {
			this.clienteService = clienteService;
			this.cuentaService = cuentaService;
		}

		@InitBinder
		public void setAllowedFields(WebDataBinder dataBinder) {
			dataBinder.setDisallowedFields("id");
		}
		
		@GetMapping(value = { "/clientes" })
		public String showClientList(Map<String, Object> model) {
			Clientes clientes = new Clientes();
			clientes.getClientesList().addAll(this.clienteService.findClientes());
			model.put("clientes", clientes);
			return "clientes/clientesList";
		}


		@GetMapping(value = "/clientes/find")
		public String initFindForm(Map<String, Object> model) {
			model.put("cliente", new Cliente());
			return "cliente/clientesList/findClientePorUsuario";
		}


		/**
		 * Custom handler for displaying an owner.
		 * @param ownerId the ID of the owner to display
		 * @return a ModelMap with the model attributes for the view
		 */
		@GetMapping("/clientes/{clienteNombreUsuario}")
		public ModelAndView showCliente(@PathVariable("clienteNombreUsuario") String clienteNombreUsuario) {
			ModelAndView mav = new ModelAndView("cliente/findClientePorUsuario");
			mav.addObject(this.clienteService.findClienteByNombreUsuario(clienteNombreUsuario));
			return mav;
		}
		
		@ModelAttribute("cuentaPorId")
		public Cuenta findCuentaById(@PathVariable("cuentaId") int cuentaId) {
			return this.cuentaService.findCuentaById(cuentaId);
		}

}
