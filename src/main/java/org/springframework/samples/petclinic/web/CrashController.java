package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CrashController {
	

	@GetMapping(value = "/NoEsPosibleDarDeBaja")
	public String triggerExceptionRN5(ModelMap model) {
		model.put("mensaje", "No puede dar de baja a alguien que no ha cumplido aun 1 mes de contrato, reglas de negocio.");
		return "exception";
	}
	
	@GetMapping(value = "/NombreDePizzaPersonalizadaDuplicado")
	public String triggerExceptionRN4(ModelMap model) {
		model.put("mensaje", "Ha intentado guardar una pizza personalizada con el nombre que ya existía de una pizza que ya había guardado y que le pertenece.");
		return "exception";
	}
	
	@GetMapping(value = "/PizzaDuplicadaEnCarta")
	public String triggerExceptionRN7Pizza(ModelMap model) {
		model.put("mensaje", "Ha intentado añadir una pizza que ya estaba en esa carta.");
		return "exception";
	}
	
	@GetMapping(value = "/BebidaDuplicadaEnCarta")
	public String triggerExceptionRN7Bebida(ModelMap model) {
		model.put("mensaje", "Ha intentado añadir una bebida que ya estaba en esa carta.");
		return "exception";
	}
	
	@GetMapping(value = "/OtroDuplicadaEnCarta")
	public String triggerExceptionRN7Otro(ModelMap model) {
		model.put("mensaje", "Ha intentado añadir un otro que ya estaba en esa carta.");
		return "exception";
	}

}
