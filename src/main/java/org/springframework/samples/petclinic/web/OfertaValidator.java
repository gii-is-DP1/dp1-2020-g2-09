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
		TamanoOferta tamaño = oferta.getTamanoOferta();
		Double coste= oferta.getCoste();
		LocalDate fechaFinal = oferta.getFechaFinal();
		LocalDate fechaInicial = oferta.getFechaInicial();
		NivelSocio nivelSocio = oferta.getNivelSocio();
		

		if (tamaño.equals(null)) {
			errors.rejectValue("El tamaño", REQUIRED+" no puede ser nulo", REQUIRED+" no puede ser nulo");
		}

		if (fechaFinal.isBefore(LocalDate.now())) {
			errors.rejectValue("La fecha debe de ser futura", REQUIRED, REQUIRED + "La fecha debe de ser futura");
		}
		

		
		if (fechaInicial.isAfter(LocalDate.now())) {
			errors.rejectValue("La fecha debe de ser pasada", REQUIRED, REQUIRED + "La fecha debe de ser pasada");
		}
		
		if (coste == 0 || coste<=0 || coste.equals(null)) {
			errors.rejectValue("El coste no puede ser negativo o menor que cero", REQUIRED, REQUIRED);
		}
		
		if (nivelSocio.equals(null)) {
			errors.rejectValue("El nivel de socio no puede ser nulo", REQUIRED, REQUIRED + "El nivel de socio no puede ser nulo");
		}
		
	}
}
