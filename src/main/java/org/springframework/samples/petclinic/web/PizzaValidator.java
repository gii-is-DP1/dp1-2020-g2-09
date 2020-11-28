package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PizzaValidator implements Validator{

	private static final String REQUIRED = "requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Pizza pizza = (Pizza) obj;
		String nombre = pizza.getNombre();
		tipoMasa tipoMasa = pizza.getTipoMasa();
		TamanoProducto tamaño = pizza.getTamano();
		Integer coste = pizza.getCoste();
		
		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3 || nombre.equals(null)) {
			errors.rejectValue("El nombre", REQUIRED+" debe tener entre 3 y 50 carácteres", REQUIRED+" debe tener entre 3 y 50 carácteres");
		}

		if (tipoMasa.equals(null)) {
			errors.rejectValue("Debe indicar el tipo de masa que desea", REQUIRED, REQUIRED);
		}
		
		if (tamaño.equals(null)) {
			errors.rejectValue("El tamaño no puede ser nulo", REQUIRED, REQUIRED);
		}
		
		if (coste == 0 || coste<=0 || coste.equals(null)) {
			errors.rejectValue("El coste no puede ser negativo o menor que cero", REQUIRED, REQUIRED);
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Pizza.class.isAssignableFrom(clazz);
	}
}
