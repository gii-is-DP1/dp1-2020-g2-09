package org.springframework.samples.petclinic.web;

import java.time.LocalDate;

import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class OfertaValidator implements Validator {

	private static final String REQUIRED = "requerido";
	
	public boolean supports(Class<?> clazz) {
		return Oferta.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Oferta oferta = (Oferta) obj;
		String name=oferta.getName();
		TamanoOferta tamaño = oferta.getTamanoOferta();
		Double coste= oferta.getCoste();
		LocalDate fechaFinal = oferta.getFechaFinal();
		LocalDate fechaInicial = oferta.getFechaInicial();
		NivelSocio nivelSocio = oferta.getNivelSocio();
		
		if(name==null) {
			errors.rejectValue("name", REQUIRED, "Por favor, introduzca un nombre");
		} 
		else if(name.length() <= 2|| name.length()>=30 ) {
			errors.rejectValue("name", "El nombre debe tener entre 2 y 30 caracteres",
					"El nombre debe tener entre 2 y 30 caracteres");
		}
		if(coste==null) {
			errors.rejectValue("coste", REQUIRED, "Por favor, introduzca un precio");
		} 
		else if(coste == 0 || coste<=0 ) {
			errors.rejectValue("coste", "El precio debe ser un número positivo mayor que cero",
					"El precio debe ser un número positivo mayor que cero");
		}
		
		if(fechaInicial==null) {
			errors.rejectValue("fechaInicial", "Por favor, introduzca una fecha de comienzo de la oferta",
					"Por favor, introduzca una fecha de comienzo de la oferta");
		} else if(fechaInicial.isBefore(LocalDate.now())) {
			errors.rejectValue("fechaInicial", 
					"La fecha de comienzo de la oferta no puede ser anterior al día de hoy",
					"La fecha de comienzo de la oferta no puede ser anterior al día de hoy");
		}
	
		
		if(fechaFinal==null) {
			errors.rejectValue("fechaFinal", "Por favor, introduzca una fecha de fin de la oferta",
					"Por favor, introduzca una fecha de fin de la oferta");
			
		} else if(fechaFinal.isBefore(fechaInicial)) {
			errors.rejectValue("fechaFinal",
					"La fecha de fin de la oferta no puede ser anterior a la fecha de comienzo",
					"La fecha de fin de la oferta no puede ser anterior a la fecha de comienzo");
		}
		
		if (tamaño==null) {
			errors.rejectValue("tamanoOferta", "Por favor, seleccione un tamaño","Por favor, seleccione un tamaño");
		}
		
		if (nivelSocio==null) {
			errors.rejectValue("nivelSocio", "Por favor, seleccione un nivel de socio", "Por favor, seleccione un nivel de socio");
		}
		
	}
}
