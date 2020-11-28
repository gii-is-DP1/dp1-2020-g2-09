package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class IngredienteValidator  implements Validator{

	private static final String REQUIRED = "requerido";
	
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Ingrediente ingrediente = (Ingrediente) obj;
		String nombre= ingrediente.getNombre();
		String tipo= ingrediente.getTipo();
		LocalDate fechaCaducidad = ingrediente.getFechaCaducidad();
		

		if (tipo.equals(null)) {
			errors.rejectValue("El tipo", REQUIRED+" no puede ser nulo", REQUIRED+" no puede ser nulo");
		}

		if (!StringUtils.hasLength(nombre) || nombre.length()>50 || nombre.length()<3 || nombre.equals(null)) {
			errors.rejectValue("El nombre", REQUIRED+" debe tener entre 3 y 50 carácteres", REQUIRED+" debe tener entre 3 y 50 carácteres");
		}
		
		if (fechaCaducidad.isEqual(LocalDate.now())) {
			errors.rejectValue("La fecha de caducidad debe de ser anterior que la actual", REQUIRED,
					REQUIRED + "La fecha de caducidad debe de ser anterior que la actual");
		}
		
		
	}
}
