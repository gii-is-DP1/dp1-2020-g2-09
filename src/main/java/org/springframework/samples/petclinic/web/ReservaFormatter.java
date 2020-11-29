package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.stereotype.Component;
@Component
public class ReservaFormatter implements Formatter<Reserva>{

	private final ReservaService rs;
	
	@Autowired
	public ReservaFormatter(ReservaService reservaService) {
		this.rs = reservaService;
	}
	@Override
	public String print(Reserva object, Locale locale) {
		return object.getId().toString();
//				"La reserva con id "+object.getId()+ " del dia "+ object.getFechaReserva() + 
//				" a las "+ object.getHora()+ " es de " + object.getNumeroPersonas() + "personas.";
	}

	@Override
	public Reserva parse(String text, Locale locale) throws ParseException {
		Collection<Reserva> findReserva = this.rs.findReservas();
		for(Reserva r:findReserva) {
			if(r.getId().toString().equals(text)) {
				return r;
			}
		}
		throw new ParseException("Reserva not found: " + text, 0);
	}

}