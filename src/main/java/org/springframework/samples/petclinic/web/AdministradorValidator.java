package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdministradorValidator implements Validator{

	private static final String REQUIRED = "Requerido";
	
	@Override	
	public boolean supports(Class<?> clazz) {
		return Administrador.class.isAssignableFrom(clazz);
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		Administrador administrador = (Administrador) obj;
		String apellidos = administrador.getApellidos();
		String email= administrador.getEmail();
		LocalDate fechaNacimiento =administrador.getFechaNacimiento();
		String nombre = administrador.getNombre();
		Integer telefono = administrador.getTelefono();
		User nombreUsuario = administrador.getUser();
		
		//nombre
		if(nombre==null) {
			errors.rejectValue("nombre","El nombre debe estar entre 2 y 20 caracteres","El nombre  debe estar entre 2 y 20 caracteres");
		}else if(nombre.length()<2 || nombre.length()>20) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 20 caracteres.", "El nombre debe tener de 2 a 20 caracteres.");
		}
		
		//apellidos
		if(apellidos==null) {
			errors.rejectValue("apellidos", "El apellido debe estar entre 2 y 20 caracteres","El apellido debe estar entre 2 y 20 caracteres");
		}else if(apellidos.length()<2 || apellidos.length()>20) {
			errors.rejectValue("apellidos","El apellido debe estar entre 2 y 20 caracteres", "El apellido debe tener de 2 a 20 caracteres.");
		}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}
		
		//telefono
		
		if(telefono==null) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}else {
			String telefonoString = String.valueOf(telefono);
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
			errors.rejectValue("user.username", "El nombre de usuario no puede estar vacío",
					"El nombre de usuario no puede estar vacío");
		}

		
	}
}
