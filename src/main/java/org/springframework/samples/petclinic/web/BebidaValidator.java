package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BebidaValidator implements Validator {

	private static final String REQUIRED = "requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Bebida bebida = (Bebida) obj;
		String nombre = bebida.getNombre();
		Boolean carbonatada = bebida.getEsCarbonatada();
		TamanoProducto tamaño = bebida.getTamano();
		Integer coste = bebida.getCoste();
		
		// nombre validation
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3|| nombre.equals(null)) {
			errors.rejectValue("El nombre", REQUIRED+" debe tener entre 3 y 50 carácteres", REQUIRED+" debe tener entre 3 y 50 carácteres");
		}

		if (carbonatada.equals(null)) {
			errors.rejectValue("Debe indicar si la bebida es carbonatada o no", REQUIRED, REQUIRED);
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
		return Bebida.class.isAssignableFrom(clazz);
	}
}
