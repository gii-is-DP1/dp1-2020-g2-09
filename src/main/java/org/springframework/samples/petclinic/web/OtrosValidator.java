package org.springframework.samples.petclinic.web;


import java.util.Collection;

import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class OtrosValidator implements Validator{

	private static final String REQUIRED = "Requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Otro otros = (Otro) obj;
		String nombre = otros.getNombre();
		//Collection<Ingrediente> ingredientes = otros.getIngredientes();
		Double coste = otros.getCoste();
		Collection<Ingrediente> ing = otros.getIngredientes();
		
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<2) {
			errors.rejectValue("nombre", REQUIRED+" debe tener entre 2 y 50 carácteres", REQUIRED+" debe tener entre 2 y 50 carácteres");
		}

		/*if (ingredientes==null) {
			errors.rejectValue("ingredientes","Los ingredientes no pueden ser nulos","Los ingredientes no pueden ser nulos");
		}*/
		
		if (coste==null) {
			errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero");
		}else if (coste == 0 || coste<=0) {
			errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero");
		}
		
		if(ing.equals(null) || ing.isEmpty()) {
			errors.rejectValue("ingredientes","Debe escoger ingredientes","Debe escoger ingredientes" );
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Otro.class.isAssignableFrom(clazz);
	}
}
