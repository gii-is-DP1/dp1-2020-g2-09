package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuentas;
import org.springframework.samples.petclinic.service.CuentaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;


@Controller
public class CuentaController {

	private final CuentaService cuentaService;

	@Autowired
	public CuentaController(CuentaService cuentaService) {
		this.cuentaService = cuentaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/cuentas" })
	public String showCuentaList(Map<String, Object> model) {
		Cuentas cuentas = new Cuentas();
		cuentas.getCuentasList().addAll(this.cuentaService.findCuentas());
		model.put("cuentas", cuentas);
		return "clientes/clientesList";
	}


	@GetMapping(value = "/cuentas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cliente", new Cliente());
		return "cliente/clientesList/findClientePorUsuario";
	}

}
