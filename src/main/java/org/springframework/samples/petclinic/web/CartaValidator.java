package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Carta;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CartaValidator implements Validator {
	private static final String REQUIRED = "required";

	@Override
	public boolean supports(Class<?> clazz) {
		return Carta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Carta carta = (Carta) target;
		String name = carta.getNombre();
		// name validation
		if (!StringUtils.hasLength(name) || name.length()>50 || name.length()<0) {
			errors.rejectValue("Nombre", REQUIRED+"debe estar entre 3 y 50 caracteres", REQUIRED+" debe estar entre 3 y 50 caracteres");
		}

		// NOMBRE validation
		if (carta.getNombre() == null) {
			errors.rejectValue("Nombre", REQUIRED,REQUIRED);
		}

		// FECHA validation
		if (carta.getFechaCreacion() == null || carta.getFechaCreacion().isAfter(LocalDate.now())) {
			errors.rejectValue("fecha", REQUIRED+"debe ser antes de la fecha actual",REQUIRED);
		}		
		
	}

}
