/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CrashController {
	
//	@RequestMapping(value = "/error")
//	public String triggerErrorException(ModelMap model) {
//		model.put("mensaje", "Intentó meterse donde está restringido el paso u ocurrió un error extraño.");
//		return "exception";
//	}

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
