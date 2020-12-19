package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class RepartidorValidator implements Validator{

	private static final String REQUIRED = "requerido";
	

	@Override
	public void validate(Object obj, Errors errors) {
		Repartidor repartidor = (Repartidor) obj;
		String apellidos = repartidor.getApellidos();
		String email= repartidor.getEmail();
		LocalDate fechaFinContrato = repartidor.getFechaFinContrato();
		LocalDate fechaInicioContrato = repartidor.getFechaInicioContrato();
		LocalDate fechaNacimiento =repartidor.getFechaNacimiento();
		String nombre = repartidor.getNombre();
		Integer telefono = repartidor.getTelefono();
		User nombreUsuario = repartidor.getUser();
		
		//nombre
		if(nombre==null) {
			errors.rejectValue("nombre", REQUIRED+" y entre 2 y 10 caracteres",
					REQUIRED+" y entre 2 y 10 caracteres");
		}else if(nombre.length()<2 || nombre.length()>10) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 10 caracteres.", "El nombre debe tener de 2 a 10 caracteres.");
		}
		
		//apellidos
		if(apellidos==null) {
			errors.rejectValue("apellidos", REQUIRED+" y entre 2 y 20 caracteres (no nulo)",
					REQUIRED+" y entre 2 y 20 caracteres");
		}else if(apellidos.length()<2 || apellidos.length()>10) {
			errors.rejectValue("apellidos","El nombre debe tener de 2 a 20 caracteres.", "El nombre debe tener de 2 a 20 caracteres.");
		}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}
		//fecha de inicio de contrato
		/*if (fechaInicioContrato.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaInicioContrato", REQUIRED, REQUIRED + "La fecha debe de ser pasada");
		}
		
		//fecha de fin de contrato
		if (fechaFinContrato.isBefore(LocalDate.now())) {
			errors.rejectValue("fechaFinContrato", REQUIRED, 
					REQUIRED + "La fecha debe de ser futura o actual");
		}
		
		 if (fechaFinContrato.isBefore(fechaInicioContrato)) {
			errors.rejectValue("fechaFinContrato", REQUIRED, 
					REQUIRED + "La fecha de fin de contrato debe ser posterior a la de inicio");
		}
		*/
		//teléfono
		if(telefono==null ) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}else  {
			String telefonoString = telefono.toString();
			if(telefonoString.length()!=9|| telefonoString.length()<1) {
				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
						REQUIRED+" escriba un número válido");
			}
		}
		
		//email
		if(email==null) {
			errors.rejectValue("email", "El email no puede estar vacío",
					"El email no puede estar vacío");
		}
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("nombreUsuario", "El nombre de usuario no puede estar vacío",
					"El nombre de usuario no puede estar vacío");
		}
	}
	
	
	public boolean supports(Class<?> clazz) {
		return Repartidor.class.isAssignableFrom(clazz);
	}
}
