package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BebidaValidator implements Validator {

//	private static final String REQUIRED = "requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Bebida bebida = (Bebida) obj;
		String nombre = bebida.getNombre();
		Boolean carbonatada = bebida.getEsCarbonatada();
		TamanoProducto tamaño = bebida.getTamano();
		Double coste = bebida.getCoste();
		
		// nombre validation
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<2) {
			errors.rejectValue("nombre", "El nombre debe tener entre 2 y 50 carácteres", "El nombre  debe tener entre 2 y 50 carácteres");
		}

		if (carbonatada==null) {
			errors.rejectValue("carbonatada","Debe indicar si la bebida es carbonatada o no", "Debe indicar si la bebida es carbonatada o no");
		}
		
		if (tamaño==null) {
			errors.rejectValue("tamano","El tamaño no puede ser nulo","El tamaño no puede ser nulo");
		}
		
		if (coste==null) {
			errors.rejectValue("coste", "El precio no puede ser negativo o menor que cero", "El precio no puede ser negativo o menor que cero");
		}else if (coste == 0 || coste<=0) {
			errors.rejectValue("coste", "El precio no puede ser negativo o menor que cero", "El precio no puede ser negativo o menor que cero");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Bebida.class.isAssignableFrom(clazz);
	}
}
