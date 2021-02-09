package org.springframework.samples.petclinic.web;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReservaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Reserva.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Reserva reserva1 = (Reserva) obj;
		Integer numeroPersonas = reserva1.getNumeroPersonas();
		LocalDate fechaReserva= reserva1.getFechaReserva();
		LocalDate fechaActual = LocalDate.now();
		LocalTime hora= reserva1.getHora();
		tipoReserva tipoReserva = reserva1.getTipoReserva();

		
		if (numeroPersonas==null) {
			errors.rejectValue("numeroPersonas", "Campo obligatorio", "Por favor, introduzca un número entre 1 y 6");
		}else if(numeroPersonas<=0 || numeroPersonas >6){
						errors.rejectValue("numeroPersonas", "El número de personas debe ser un dígito entre 1 y 6",
								"El número de personas debe ser un dígito entre 1 y 6");			
			}
		
		if (fechaReserva==null) {
			errors.rejectValue("fechaReserva", "Campo obligatorio", "Por favor, introduzca una fecha");
		} else if(fechaReserva.isBefore(fechaActual)) {
			errors.rejectValue("fechaReserva", "La fecha de la reserva no puede ser anterior al día de hoy", 
					"La fecha de la reserva no puede ser anterior al día de hoy");
			
		} else if(DAYS.between(fechaActual, fechaReserva) < 1) {
			errors.rejectValue("fechaReserva", "Debe reservar con al menos un día de antelación", "Debe reservar con al menos un día de antelación");
		}
		
		
		if (tipoReserva==null) {
			errors.rejectValue("tipoReserva",
					"Campo obligatorio", 
					"Por favor, seleccione si su reserva se corresponde con un almuerzo o cena");
			
		} else if(!(tipoReserva.getName().equals("ALMUERZO") || tipoReserva.getName().equals("CENA"))) {
			errors.rejectValue("tipoReserva", "Solo se puede reservar para realizar un almuerzo o cena",
					"Solo se puede reservar para realizar un almuerzo o cena");
		}
			
		if (hora == null) {
			errors.rejectValue("hora",
					"Campo obligatorio", 
					"Por favor, introduzca una hora para reservar");
			
		} else if(tipoReserva.getName()=="ALMUERZO") {
			if((hora.getHour()<12 || hora.getHour()>15)) {
				errors.rejectValue("hora", "Por favor, seleccione una hora entre las 12:00 y las 15:00",
						"Por favor, seleccione una hora entre las 12:00 y las 15:00");
			} else if(hora.getHour()<20 || hora.getHour()>23) {
				errors.rejectValue("hora", "Por favor, seleccione una hora entre las 20:00 y las 23:00",
						"Por favor, seleccione una hora entre las 20:00 y las 23:00");
			}
			
		}
		
		
		}
		
	}


