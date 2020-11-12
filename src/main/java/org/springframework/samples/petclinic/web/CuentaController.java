package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Cuentas;
import org.springframework.samples.petclinic.service.CuentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


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
	
	@GetMapping(value = { "/allCuentas" })
	public String showCuentaList(Map<String, Object> model) {
		Cuentas cuentas = new Cuentas();
		cuentas.getCuentasList().addAll(this.cuentaService.findCuentas());
		model.put("cuentas", cuentas);
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
	public String processCreationForm(@Valid Cuenta cuenta, BindingResult result) {
		if (result.hasErrors()) {
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
			this.cuentaService.saveCuenta(cuenta);
			return "redirect:/allCuentas";
		}
	}

	//iniciar actualizacion
	@GetMapping(value = "/cuentas/{cuentaId}/edit")
	public String initUpdateForm(@PathVariable("cuentaId") int cuentaId, ModelMap model) {
		Cuenta cuenta = this.cuentaService.findCuentaById(cuentaId);
		model.put("cuenta", cuenta);
		return "clientes/createOrUpdateCuentaForm";
	}
	
	//mandar actualizacion
	@PostMapping(value = "/cuentas/{cuentaId}/edit")
	public String processUpdateCuentaForm(@Valid Cuenta cuenta, BindingResult result,
			@PathVariable("cuentaId") int cuentaId) {
		if (result.hasErrors()) {
			return "clientes/createOrUpdateCuentaForm";
		}
		else {
			cuenta.setId(cuentaId);
			this.cuentaService.saveCuenta(cuenta);
			return "redirect:/allCuentas";
		}
	}
	


}
