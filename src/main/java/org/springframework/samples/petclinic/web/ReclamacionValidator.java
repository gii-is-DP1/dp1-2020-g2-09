package org.springframework.samples.petclinic.web;


import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ReclamacionValidator implements Validator {

	
	public boolean supports(Class<?> clazz) {
		return Reclamacion.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		Reclamacion reclamacion = (Reclamacion) obj;
		String observacion = reclamacion.getObservacion();
		String respuesta = reclamacion.getRespuesta();
		
		if(observacion.length()<10 || observacion.length()>1000 || observacion.equals("")) {
			errors.rejectValue("observacion","Por favor, escriba una observación entre 10 y \"\r\n" + 
					"					+ \"1000 caracteres", "Por favor, escriba una observación entre 10 y "
					+ "1000 caracteres");
		}
				
		if(respuesta.equals("") || respuesta==null){
			errors.rejectValue("respuesta", "Complete el campo respuesta", 
					"Complete el campo respuesta");
			
		}
		if(respuesta.length()>=1 && respuesta.length()<10) {
			errors.rejectValue("respuesta","Por favor, escriba una respuesta un poco más larga",
					"Por favor, escriba una respuesta un poco más larga");
			
		} 
		if(respuesta.length()>1000){
			errors.rejectValue("", "Su respuesta excede los 1000 caracteres. Por favor, escriba una respuesta un poco más corta",
					"Su respuesta excede los 1000 caracteres. Por favor, escriba una respuesta un poco más corta");
		}
		
	}
 
}
