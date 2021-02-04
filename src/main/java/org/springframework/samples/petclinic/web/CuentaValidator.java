package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CuentaValidator implements Validator{

	
	/*@Autowired
	private ClienteService clienteService;*/
	@Override
	public boolean supports(Class<?> clazz) {
		return Cuenta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Cuenta cuenta = (Cuenta) obj;
		String nombre = cuenta.getNombre();
		String apellidos = cuenta.getApellidos();
		LocalDate fechaNacimiento = cuenta.getFechaNacimiento();
		Integer telefono = cuenta.getTelefono();
		String email = cuenta.getEmail();
		User usuario = cuenta.getUser();
		String nombreUsuario = usuario.getUsername();
		String contraseña = usuario.getPassword();
		
		Pattern patternNombre = Pattern
                .compile("^[a-zA-ZñÑ\\s]+");
		Matcher matcherNombre = patternNombre.matcher(nombre);
		//nombre
				if(nombre.equals(null)) {
					errors.rejectValue("nombre",
							"El nombre debe tener entre 2 y 20 caracteres",
							"El nombre debe tener y entre 2 y 20 caracteres");
				}else if(nombre.length()<2 || nombre.length()>20) {
					errors.rejectValue("nombre",
							"El nombre debe tener de 2 a 20 caracteres",
							"El nombre debe tener de 2 a 20 caracteres");
				}else if(!matcherNombre.find()) {
					errors.rejectValue("nombre", 
							"El nombre no posee el formato correcto",
							"El nombre no posee el formato correcto");
				}
				Matcher matcherApellidos = patternNombre.matcher(apellidos);
				//apellidos
				if(apellidos==null) {
					errors.rejectValue("apellidos",
							"El apellido debe tener y entre 2 y 20 caracteres",
							"El apellido debe tener y entre 2 y 20 caracteres");
				}else if(apellidos.length()<2 || apellidos.length()>20) {
					errors.rejectValue("apellidos",
							"El apellido debe tener de 2 a 20 caracteres", 
							"El apellido debe tener de 2 a 20 caracteres");
				}else if(!matcherApellidos.find()) {
					errors.rejectValue("apellidos", 
							"Los apellidos no poseen el formato correcto",
							"Los apellidos no poseen el formato correcto");
				}
		
		//fechaNacimiento
		if(fechaNacimiento==null) {
			errors.rejectValue("fechaNacimiento", 
					"La fecha no puede estar vacía",
					"La fecha no puede estar vacía");
		}else if(fechaNacimiento.isAfter(LocalDate.now())) {
			errors.rejectValue("fechaNacimiento", 
					"La fecha de nacimiento no puede ser superior a la de hoy",
					"La fecha de nacimiento no puede ser superior a la de hoy");
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
				
		//telefono
		if(telefono==null) {
			errors.rejectValue("telefono", "Escriba un número válido",
					 "Escriba un número válido");
		}else if(telefono<100000000 || telefono>999999999) {
			errors.rejectValue("telefono",  "Escriba un número válido",
					"Escriba un número válido");
		}
		
	}

}
