package org.springframework.samples.petclinic.web;


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
		Ingrediente[] ingredientes = otros.getIngredientes();
		String tamaño = otros.getNombre();
		Integer coste = otros.getCoste();
		
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3) {
			errors.rejectValue("El nombre", REQUIRED+" debe tener entre 3 y 50 carácteres", REQUIRED+" debe tener entre 3 y 50 carácteres");
		}

		if (ingredientes.equals(null)) {
			errors.rejectValue("Los ingredientes no pueden ser nulos", REQUIRED, REQUIRED);
		}
		
		if (tamaño.equals(null)) {
			errors.rejectValue("El tamaño no puede ser nulo", REQUIRED, REQUIRED);
		}
		
		if (coste == 0 || coste<=0 ||coste.equals(null)) {
			errors.rejectValue("El coste no puede ser negativo o menor que cero", REQUIRED, REQUIRED);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Otros.class.isAssignableFrom(clazz);
	}
}
