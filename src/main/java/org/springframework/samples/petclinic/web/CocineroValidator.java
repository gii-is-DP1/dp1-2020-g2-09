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
	
	public boolean supports(Class<?> clazz) {
		return Cocina.class.isAssignableFrom(clazz);
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		Cocina cocinero = (Cocina) obj;
		String apellidos = cocinero.getApellidos();
		String email= cocinero.getEmail();
		LocalDate fechaFinContrato = cocinero.getFechaFinContrato();
		LocalDate fechaInicioContrato = cocinero.getFechaInicioContrato();
		LocalDate fechaNacimiento =cocinero.getFechaNacimiento();
		String nombre = cocinero.getNombre();
		Integer telefono = cocinero.getTelefono();
		User nombreUsuario = cocinero.getUser();
		
		//nombre
		if(nombre.length()<2 || nombre.length()>10) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 10 caracteres.", "El nombre debe tener de 2 a 10 caracteres.");
		}
		if(nombre.length()<1 || nombre.equals(null)) {
			errors.rejectValue("nombre", REQUIRED+" y entre 2 y 10 caracteres",
					REQUIRED+" y entre 2 y 10 caracteres");
		}
		//apellidos
		if(apellidos.length()<2 || apellidos.length()>10) {
			errors.rejectValue("apellidos","El nombre debe tener de 2 a 20 caracteres.", "El nombre debe tener de 2 a 20 caracteres.");
		}
		if(apellidos.length()<1 || apellidos.equals(null)) {
			errors.rejectValue("apellidos", REQUIRED+" y entre 2 y 20 caracteres",
					REQUIRED+" y entre 2 y 20 caracteres");
		}
		//fechaNacimiento
		if(fechaNacimiento.equals(null)) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}
		//fecha de inicio de contrato
		if (fechaInicioContrato.isAfter(LocalDate.now())) {
			errors.rejectValue("La fecha debe de ser pasada", REQUIRED, REQUIRED + "La fecha debe de ser pasada");
		}
		
		//fecha de fin de contrato
		if (fechaFinContrato.isBefore(LocalDate.now())) {
			errors.rejectValue("La fecha debe de ser futura o actual", REQUIRED, 
					REQUIRED + "La fecha debe de ser futura o actual");
		}
		
		if (fechaFinContrato.isBefore(fechaInicioContrato)) {
			errors.rejectValue("La fecha de fin de contrato debe ser posterior a la de inicio", REQUIRED, 
					REQUIRED + "La fecha de fin de contrato debe ser posterior a la de inicio");
		}
		
		//teléfono
		String telefonoString = telefono.toString();
		if(telefono.equals(null) || telefonoString.length()<1) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}
		if(telefonoString.length()!=9) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}
		//email
		if(email.equals(null)) {
			errors.rejectValue("email", "El email no puede estar vacío",
					"El email no puede estar vacío");
		}
		//nombreUsuario
		if(nombreUsuario.equals(null)) {
			errors.rejectValue("nombreUsuario", "El nombre de usuario no puede estar vacío",
					"El nombre de usuario no puede estar vacío");
		}
	}
}
