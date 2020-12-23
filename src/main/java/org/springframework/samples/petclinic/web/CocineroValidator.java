package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CocineroValidator implements Validator {
	
	private static final String REQUIRED = "requerido";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Cocina.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		Cocina cocinero = (Cocina) obj;
		String apellidos = cocinero.getApellidos();
		String email= cocinero.getEmail();
		//LocalDate fechaFinContrato = cocinero.getFechaFinContrato();
		//LocalDate fechaInicioContrato = cocinero.getFechaInicioContrato();
		LocalDate fechaNacimiento =cocinero.getFechaNacimiento();
		String nombre = cocinero.getNombre();
		
		Integer telefono = cocinero.getTelefono();
		User nombreUsuario = cocinero.getUser();
		
		
		//nombre
		 if(nombre==null) {
			errors.rejectValue("nombre", REQUIRED+"no debe ser nulo", REQUIRED+" nombre no puede ser nulo");
		}else if(nombre.length()<2 || nombre.length()>20) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 20 caracteres.", "El nombre debe tener de 2 a 20 caracteres.");
		}
		
		//apellidos
		if(apellidos==null) {
			errors.rejectValue("apellidos", REQUIRED+" no debe ser nulo", REQUIRED+" apellido no debe ser nulo");
		}else if(apellidos.length()<2 || apellidos.length()>30) {
			errors.rejectValue("apellidos","El apellido debe tener de 2 a 30 caracteres.", "El apellido debe tener de 2 a 30 caracteres.");
		}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía","La fecha no puede estar vacía");
		}
		//fecha de inicio de contrato
		/* if (fechaInicioContrato.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaInicioContrato", REQUIRED, REQUIRED + "La fecha debe de ser pasada");
		}
		
		//fecha de fin de contrato
		 if (fechaFinContrato.isBefore(LocalDate.now())) {
			errors.rejectValue("fechaFinContrato", REQUIRED, REQUIRED + "La fecha debe de ser futura o actual");
		}else if (fechaFinContrato.isBefore(fechaInicioContrato)) {
			errors.rejectValue("fechaFinContrato", REQUIRED, REQUIRED + "La fecha de fin de contrato debe ser posterior a la de inicio");
		}*/
		
		//teléfono
		
		
		if(telefono==null) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido", REQUIRED+" escriba un número válido");
		}else {
			String telefonoString = String.valueOf(telefono);
			if(telefonoString.length()<1) {
				errors.rejectValue("telefono", REQUIRED+" escriba un número válido", REQUIRED+" escriba un número válido");
			}else if(telefonoString.length()!=9) {
				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",REQUIRED+" escriba un número válido");
			}
		}
		
		//email
		if(email==null) {
			errors.rejectValue("email", "El email no puede estar vacío","El email no puede estar vacío");
		}
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("nombreUsuario", "El nombre de usuario no puede estar vacío", "El nombre de usuario no puede estar vacío");
		}
	}
	
}
