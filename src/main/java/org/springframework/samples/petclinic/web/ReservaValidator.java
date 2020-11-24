package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.validation.Errors;

public class ReservaValidator {

	private static final String REQUIRED = "required";

	public void reservaValidate(Object obj, Errors errors) {
		Reserva reserva1 = (Reserva) obj;
		Integer numeroPersonas = reserva1.getNumeroPersonas();
		LocalDate fechaReserva= reserva1.getFechaReserva();
		LocalTime hora= reserva1.getHora();
		tipoReserva tipoReserva = reserva1.getTipoReserva();

		
		if (numeroPersonas==0 || numeroPersonas<=0) {
			errors.rejectValue("El número de personas", REQUIRED+" debe ser un número real", REQUIRED+" y mayor que cero.");
		}

		if (numeroPersonas == null || fechaReserva==null || hora== null || tipoReserva==null) {
			errors.rejectValue("No puede introducir una reserva con valores nulos.", REQUIRED, REQUIRED);
		}
	}
	
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}
}

