package org.springframework.samples.petclinic.web;


import java.util.Collection;

import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OtrosValidator implements Validator{

	private static final String REQUIRED = "requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Otros otros = (Otros) obj;
		String nombre = otros.getNombre();
		//Collection<Ingrediente> ingredientes = otros.getIngredientes();
		Integer coste = otros.getCoste();
		
		if (!StringUtils.hasLength(nombre) || nombre.length()>20 || nombre.length()<2) {
			errors.rejectValue("nombre", REQUIRED+" debe tener entre 2 y 20 carácteres", REQUIRED+" debe tener entre 2 y 20 carácteres");
		}

		/*if (ingredientes==null) {
			errors.rejectValue("ingredientes","Los ingredientes no pueden ser nulos","Los ingredientes no pueden ser nulos");
		}*/
		
		if (coste==null) {
			errors.rejectValue("coste","El coste no puede ser negativo o menor que cero","El coste no puede ser negativo o menor que cero");
		}else if (coste == 0 || coste<=0) {
			errors.rejectValue("coste","El coste no puede ser negativo o menor que cero","El coste no puede ser negativo o menor que cero");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Otros.class.isAssignableFrom(clazz);
	}
}
