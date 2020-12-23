package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		LocalDate fechaNacimiento =cliente.getFechaNacimiento();
		String nombre = cliente.getNombre();
		Integer telefono = cliente.getTelefono();
		User Usuario = cliente.getUser();
		String nombreUsuario = Usuario.getUsername();
		
		//nombre
		if(nombre==null) {
			errors.rejectValue("nombre",
					"El nombre debe tener entre 2 y 20 caracteres",
					"El nombre debe tener y entre 2 y 20 caracteres");
		}else if(nombre.length()<2 || nombre.length()>20) {
			errors.rejectValue("nombre",
					"El nombre debe tener de 2 a 20 caracteres", 
					"El nombre debe tener de 2 a 20 caracteres");
		}
		
		//apellidos
		if(apellidos==null) {
			errors.rejectValue("apellidos", 
					"El apellido debe tener y entre 2 y 20 caracteres",
					"El apellido debe tener y entre 2 y 20 caracteres");
		}else if(apellidos.length()<2 || apellidos.length()>20) {
			errors.rejectValue("apellidos",
					"El apellido debe tener de 2 a 20 caracteres",
					"El apellido debe tener de 2 a 20 caracteres");
		}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", 
					"La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}
		//fecha de alta
//		if (fechaAlta!=LocalDate.now()) {
//			errors.rejectValue("fechaAlta", "La fecha debe de ser pasada", "La fecha debe de ser pasada");
//		}
		
		//telefono
		
		if(telefono==null) {
			errors.rejectValue("telefono",
					"Escriba un número válido",
					"Escriba un número válido");
		}
//		else {
//			String telefonoString = telefono.toString();
//			if(telefonoString.matches("[0-9]*")) {
//				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",REQUIRED+" escriba un número válido");
//			}else if(telefonoString.length()!=9 || telefonoString.length()<1) {
//				errors.rejectValue("telefono", REQUIRED+" escriba un número válido",REQUIRED+" escriba un número válido");
//			}
//		}
		else if(telefono<100000000 || telefono>999999999) {
			errors.rejectValue("telefono",
					"Escriba un número válido",
					"Escriba un número válido");
		}
		
		
		// Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);

		
		//email
		if(email==null) {
			errors.rejectValue("email", "El email no puede estar vacío",
					"El email no puede estar vacío");
		}else if(email.length()<12 || email.length()>30) {
			errors.rejectValue("email", "El email es demasiado largo o corto",
					"El email es demasiado largo o corto");
		}else if(!matcher.find()) {
			errors.rejectValue("email", "El email no posee el formato correcto",
					"El email no posee el formato correcto");
		}
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("user.username", "El nombre de usuario no puede estar vacío","El nombre de usuario no puede estar vacío");
		}else if(nombreUsuario.length()<2 || nombreUsuario.length()>20) {
			errors.rejectValue("user.username", "El nombre tiene que estar entre 2 y 20 caracteres",
					"El nombre tiene que estar entre 2 y 20 caracteres");
		}

		
	}
}
