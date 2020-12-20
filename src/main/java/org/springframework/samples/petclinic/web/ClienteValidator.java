package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

@Component
public class ClienteValidator implements Validator{

	private static final String REQUIRED = "Requerido";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.isAssignableFrom(clazz);
	}	

	@Override
	public void validate(Object obj, Errors errors) {
		Cliente cliente = (Cliente) obj;
		String apellidos = cliente.getApellidos();
		String email= cliente.getEmail();
		LocalDate fechaAlta = cliente.getFechaAlta();
		LocalDate fechaNacimiento =cliente.getFechaNacimiento();
		String nombre = cliente.getNombre();
		Integer telefono = cliente.getTelefono();
		User nombreUsuario = cliente.getUser();
		
		//nombre
		if(nombre==null) {
			errors.rejectValue("nombre","El nombre debe tener entre 2 y 20 caracteres","El nombre debe tener y entre 2 y 20 caracteres");
		}else if(nombre.length()<2 || nombre.length()>20) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 20 caracteres.", "El nombre debe tener de 2 a 20 caracteres.");
		}
		
		//apellidos
		if(apellidos==null) {
			errors.rejectValue("apellidos", "El apellido debe tener y entre 2 y 20 caracteres","El apellido debe tener y entre 2 y 20 caracteres");
		}else if(apellidos.length()<2 || apellidos.length()>20) {
			errors.rejectValue("apellidos","El apellido debe tener de 2 a 20 caracteres.", "El apellido debe tener de 2 a 20 caracteres.");
		}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía","La fecha no puede estar vacía");
		}
		//fecha de alta
		if (fechaAlta.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaAlta", "La fecha debe de ser pasada", "La fecha debe de ser pasada");
		}
		
		//telefono
		
		if(telefono==null) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}else {
			String telefonoString = telefono.toString();
			if(telefonoString.matches("[0-9]*")) {
				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",REQUIRED+" escriba un número válido");
			}else if(telefonoString.length()!=9 || telefonoString.length()<1) {
				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",REQUIRED+" escriba un número válido");
			}
		}
		
		//email
		if(email==null) {
			errors.rejectValue("email", "El email no puede estar vacío","El email no puede estar vacío");
		}
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("nombreUsuario", "El nombre de usuario no puede estar vacío","El nombre de usuario no puede estar vacío");
		}

		
	}
}
