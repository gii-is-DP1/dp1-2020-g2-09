package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReservaValidator implements Validator {

	private static final String REQUIRED = "Requerido";
	@Override
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Reserva reserva1 = (Reserva) obj;
		Integer numeroPersonas = reserva1.getNumeroPersonas();
		LocalDate fechaReserva= reserva1.getFechaReserva();
		LocalTime hora= reserva1.getHora();
		tipoReserva tipoReserva = reserva1.getTipoReserva();

		
		if (numeroPersonas==null) {
			errors.rejectValue("numeroPersonas", REQUIRED+" debe ser un número real y mayor que cero", REQUIRED+" debe ser un número real y mayor que cero");
		}else {
			String numString = String.valueOf(numeroPersonas);
			if(numString.length()<1) {
				errors.rejectValue("numeroPersonas", REQUIRED+" debe ser un número real y mayor que cero", REQUIRED+" debe ser un número real y mayor que cero");
			}else {
				 if (numeroPersonas==0 || numeroPersonas<=0) {
						errors.rejectValue("numeroPersonas", REQUIRED+" debe ser un número real", REQUIRED+" y mayor que cero");
					}else if (numeroPersonas>6) {
						errors.rejectValue("numeroPersonas", "El numero de personas debe ser inferior a 6 debido a la pandemia del COVID","El numero de personas debe ser inferior a 6 debido a la pandemia del COVID");
					}	
			}
		}
		
		if (fechaReserva==null) {
			errors.rejectValue("fechaReserva",
					"No puede introducir una reserva con valores nulos", 
					"No puede introducir una reserva con valores nulos");
		} 
		if (hora== null) {
			errors.rejectValue("hora",
					"No puede introducir una reserva con valores nulos", 
					"No puede introducir una reserva con valores nulos");
		} 
		if (tipoReserva==null) {
			errors.rejectValue("tipoReserva",
					"No puede introducir una reserva con valores nulos", 
					"No puede introducir una reserva con valores nulos");
		}
		
	}
}

