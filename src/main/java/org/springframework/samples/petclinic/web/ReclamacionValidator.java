package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ReclamacionValidator implements Validator {
	
private static final String REQUIRED = "requirido";
	
	@Autowired
	private ReclamacionService reclamacionService;
	
	public boolean supports(Class<?> clazz) {
		return Reclamacion.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		Reclamacion reclamacion = (Reclamacion) obj;
		LocalDate fechaReclamacion = reclamacion.getFechaReclamacion();
		String observacion = reclamacion.getObservacion();
		
		//fechaReclamacion
		if(fechaReclamacion.isAfter(LocalDate.now())) {
		errors.rejectValue("fechaReclamacion",
				"La fecha de reclamación no debe ser posterior a la fecha actual");
				}
		if(fechaReclamacion.equals(null)) {
			errors.rejectValue("fechaReclamacion", REQUIRED);
				}
		
		//Observacion
		if(observacion.length()<10 || observacion.length()>1000) {
			errors.rejectValue("observacion", "Por favor, escriba una observación entre 10 y "
					+ "1000 caracteres");
		}
		
		if(observacion=="" || observacion.equals(null)) {
			errors.rejectValue("observacion", REQUIRED);
		}
		
	}

}
