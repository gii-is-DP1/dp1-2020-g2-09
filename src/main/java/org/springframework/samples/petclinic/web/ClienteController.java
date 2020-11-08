package org.springframework.samples.petclinic.web;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Clientes;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClienteController {
	
		private final ClienteService clienteService;

		@Autowired
		public ClienteController(ClienteService clienteService) {
			this.clienteService = clienteService;
		}

		@InitBinder
		public void setAllowedFields(WebDataBinder dataBinder) {
			dataBinder.setDisallowedFields("id");
		}


		@GetMapping(value = "/clientes/find")
		public String initFindForm(Map<String, Object> model) {
			model.put("cliente", new Clientes());
			return "clientes/findClientes";
		}


		/**
		 * Custom handler for displaying an owner.
		 * @param ownerId the ID of the owner to display
		 * @return a ModelMap with the model attributes for the view
		 */
		@GetMapping("/clientes/{clienteNombreUsuario}")
		public ModelAndView showCliente(@PathVariable("clienteNombreUsuario") String clienteNombreUsuario) {
			ModelAndView mav = new ModelAndView("owners/ownerDetails");
			mav.addObject(this.clienteService.findClienteByNombreUsuario(clienteNombreUsuario));
			return mav;
		}
}
