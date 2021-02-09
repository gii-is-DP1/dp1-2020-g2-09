package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Carta;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Carta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Carta carta = (Carta) target;
		String name = carta.getNombre();
		if (carta.getNombre() == null) {
			errors.rejectValue("nombre", 
					"El nombre debe estar entre 3 y 50 caracteres",
					"El nombre debe estar entre 3 y 50 caracteres");
		}else if (name.length()>50 || name.length()<3) {
			errors.rejectValue("nombre",
					"El nombre debe estar entre 3 y 50 caracteres",
					"El nombre debe estar entre 3 y 50 caracteres");
		}	
		
	}

}
