package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CuentaValidator implements Validator{

	private static final String REQUIRED = "Requirido";
	
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
		
		if(contraseña==null) {
			errors.rejectValue("user.password", "La contraseña de usuario no puede estar vacía",
					"La contraseña de usuario no puede estar vacía");
		}
				
		//telefono
		if(telefono==null) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
					REQUIRED+" escriba un número válido");
		}else if(telefono<100000000 || telefono>999999999) {
			errors.rejectValue("telefono", REQUIRED+" escriba un número válido",
			REQUIRED+" escriba un número válido");
		}
		
	}

}
