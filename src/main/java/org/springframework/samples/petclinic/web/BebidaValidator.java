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
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3|| nombre==null) {
			errors.rejectValue("nombre", REQUIRED+" debe tener entre 3 y 50 carácteres", REQUIRED+" debe tener entre 3 y 50 carácteres");
		}

		if (carbonatada==null) {
			errors.rejectValue("carbonatada","Debe indicar si la bebida es carbonatada o no", "Debe indicar si la bebida es carbonatada o no");
		}
		
		if (tamaño==null) {
			errors.rejectValue("tamaño","El tamaño no puede ser nulo","El tamaño no puede ser nulo");
		}
		
		if (coste == 0 || coste<=0 ||coste==null) {
			errors.rejectValue("coste", "El coste no puede ser negativo o menor que cero", "El coste no puede ser negativo o menor que cero");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Bebida.class.isAssignableFrom(clazz);
	}
}
