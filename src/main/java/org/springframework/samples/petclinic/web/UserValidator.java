package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Cuenta cuenta = (Cuenta) obj;
		User usuario = cuenta.getUser();
		String nombreUsuario = usuario.getUsername();
		String contraseña = usuario.getPassword();
		
		
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("username", "El nombre de usuario no puede estar vacío",
					"El nombre de usuario no puede estar vacío");
		} 
		
		if(contraseña==null) {
			errors.rejectValue("password", "La contraseña de usuario no puede estar vacía",
					"La contraseña de usuario no puede estar vacía");
		}
				
	}


}
