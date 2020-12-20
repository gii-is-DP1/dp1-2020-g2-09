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

	private static final String REQUIRED = "Requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Otros otros = (Otros) obj;
		String nombre = otros.getNombre();
		Collection<Ingrediente> ingredientes = otros.getIngredientes();
		String tamaño = otros.getNombre();
		Integer coste = otros.getCoste();
		
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3) {
			errors.rejectValue("nombre","El nombre debe tener entre 3 y 50 carácteres", "El nombre debe tener entre 3 y 50 carácteres");
		}

		if (ingredientes==null) {
			errors.rejectValue("ingredientes","Los ingredientes no pueden ser nulos","Los ingredientes no pueden ser nulos");
		}
		
		if (tamaño==null) {
			errors.rejectValue("tamano","El tamaño no puede ser nulo", "El tamaño no puede ser nulo");
		}
		
		if (coste==null) {
			errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero");
		}else if (coste<=0) {
			errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Otros.class.isAssignableFrom(clazz);
	}
}
