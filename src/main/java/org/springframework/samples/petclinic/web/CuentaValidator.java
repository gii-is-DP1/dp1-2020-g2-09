package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CuentaValidator implements Validator{

	private static final String REQUIRED = "requirido";
	
	@Autowired
	private ClienteService clienteService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Cuenta cuenta = (Cuenta) obj;
		String nombre = cuenta.getNombre();
		String apellidos = cuenta.getApellidos();
		LocalDate fechaNac = cuenta.getFechaNacimiento();
		Integer telefono = cuenta.getTelefono();
		String email = cuenta.getEmail();
		User usuario = cuenta.getUser();
		String nombreUsuario = usuario.getUsername();
		String contraseña = usuario.getPassword();
		
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
		if(fechaNac.equals(null)) {
			errors.rejectValue("fechaNacimiento", "La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}
		//telefono
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
		//contraseña
		if(contraseña.equals(null)) {
			errors.rejectValue("nombreUsuario", "La contraseña no puede estar vacía",
					"La contraseña no puede estar vacía");
		}
	}

}
