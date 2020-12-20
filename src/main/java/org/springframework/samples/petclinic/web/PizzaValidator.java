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

	private static final String REQUIRED = "Requerido";

	@Override
	public void validate(Object obj, Errors errors) {
		Pizza pizza = (Pizza) obj;
		String nombre = pizza.getNombre();
		tipoMasa tipoMasa = pizza.getTipoMasa();
		TamanoProducto tamaño = pizza.getTamano();
		Integer coste = pizza.getCoste();
		
		if (nombre == null) {
			errors.rejectValue("nombre", "El nombre debe tener entre 3 y 50 carácteres", "El nombre debe tener entre 3 y 50 carácteres");
		}else if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3) {
			errors.rejectValue("nombre", "El nombre debe tener entre 3 y 50 carácteres", "El nombre debe tener entre 3 y 50 carácteres");
		}

		if (tipoMasa==null) {
			errors.rejectValue("tipoMasa","Debe indicar el tipo de masa que desea", "Debe indicar el tipo de masa que desea");
		}
		
		if (tamaño==null) {
			errors.rejectValue("tamano","El tamaño no puede ser nulo", "El tamaño no puede ser nulo");
		}
		
		if (coste==null) {
			errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero" );
		}else {
			String costestring= String.valueOf(coste);
			//if(!costestring.matches("[0-9]*")) {
			/*if(costestring.matches("\\d*"))  {
				errors.rejectValue("coste","El precio debe ser numérico","El precio debe ser numérico" );

			}else*/ if (coste<=0) {
				errors.rejectValue("coste","El precio no puede ser negativo o menor que cero","El precio no puede ser negativo o menor que cero" );

		}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Pizza.class.isAssignableFrom(clazz);
	}
}
