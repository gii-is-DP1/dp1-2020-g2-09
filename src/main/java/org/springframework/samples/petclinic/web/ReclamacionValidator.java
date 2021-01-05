package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ReclamacionValidator implements Validator {
	
private static final String REQUIRED = "requerido";
	
	@Autowired
	private ReclamacionService reclamacionService;
	
	public boolean supports(Class<?> clazz) {
		return Reclamacion.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		Reclamacion reclamacion = (Reclamacion) obj;
		//LocalDate fechaReclamacion = reclamacion.getFechaReclamacion();
		String observacion = reclamacion.getObservacion();
		String respuesta = reclamacion.getRespuesta();
		
//		//fechaReclamacion
//		if(fechaReclamacion==null) {
//			errors.rejectValue("fechaReclamacion", REQUIRED, "Por favor, inserte una fecha");
//				}
//		if(fechaReclamacion.isAfter(LocalDate.now())) {
//		errors.rejectValue("fechaReclamacion", "La fecha de reclamación no puede ser posterior a la fecha actual",
//				"La fecha de reclamación no puede ser posterior a la fecha actual");
//				} 
		
		//observacion
		if(observacion.length()<10 || observacion.length()>1000 || observacion.equals("")) {
			errors.rejectValue("observacion","Por favor, escriba una observación entre 10 y \"\r\n" + 
					"					+ \"1000 caracteres", "Por favor, escriba una observación entre 10 y "
					+ "1000 caracteres");
		}
		
		//respuesta
		
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
