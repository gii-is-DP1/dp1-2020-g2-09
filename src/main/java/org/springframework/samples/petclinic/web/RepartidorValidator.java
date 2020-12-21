package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class RepartidorValidator implements Validator{

	private static final String REQUIRED = "Requerido";
	

	@Override
	public void validate(Object obj, Errors errors) {
		Repartidor repartidor = (Repartidor) obj;
		String apellidos = repartidor.getApellidos();
		String email= repartidor.getEmail();
//		LocalDate fechaFinContrato = repartidor.getFechaFinContrato();
//		LocalDate fechaInicioContrato = repartidor.getFechaInicioContrato();
		LocalDate fechaNacimiento =repartidor.getFechaNacimiento();
		String nombre = repartidor.getNombre();
		Integer telefono = repartidor.getTelefono();
		User usuario = repartidor.getUser();
		String nombreUsuario = usuario.getUsername();
		String contraseña = usuario.getPassword();
		
		//nombre
		if(nombre==null) {
			errors.rejectValue("nombre", "El nombre debe tener de 2 a 20 caracteres","El nombre debe tener de 2 a 20 caracteres");
		}else if(nombre.length()<2 || nombre.length()>20) {
			errors.rejectValue("nombre","El nombre debe tener de 2 a 20 caracteres", "El nombre debe tener de 2 a 20 caracteres");
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
		}*/
		
		//telefono
		if(telefono==null) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}else if(telefono<100000000 || telefono>999999999) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
			REQUIRED+" escriba un número válido");
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
			errors.rejectValue("email", "El email no es correcto",
					"El email no es correcto");
		}
		//nombreUsuario
		if(nombreUsuario==null) {
			errors.rejectValue("user.username", "El nombre de usuario no puede estar vacío",
					"El nombre de usuario no puede estar vacío");
		}else if(nombreUsuario.length()<2 || nombreUsuario.length()>20) {
			errors.rejectValue("user.username", "El nombre tiene que estar entre 2 y 20 caracteres",
					"El nombre tiene que estar entre 2 y 20 caracteres");
		} 
		
		if(contraseña==null) {
			errors.rejectValue("user.password", "La contraseña de usuario no puede estar vacía",
					"La contraseña de usuario no puede estar vacía");
		}else if(contraseña.length()<2 || contraseña.length()>20) {
			errors.rejectValue("user.password", "La contraseña de usuario tiene que estar entre 2 y 20 caracteres",
					"La contraseña de usuario tiene que estar entre 2 y 20 caracteres");
		}
	}
	
	
	public boolean supports(Class<?> clazz) {
		return Repartidor.class.isAssignableFrom(clazz);
	}
}
