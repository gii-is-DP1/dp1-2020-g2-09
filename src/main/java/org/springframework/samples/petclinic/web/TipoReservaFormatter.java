package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.stereotype.Component;
@Component
public class TipoReservaFormatter implements Formatter<tipoReserva>{

	private final ReservaService rs;
	
	@Autowired
	public TipoReservaFormatter(ReservaService reservaService) {
		this.rs = reservaService;
	}
	@Override
	public String print(tipoReserva object, Locale locale) {
		return object.getName();
	}

	@Override
	public tipoReserva parse(String text, Locale locale) throws ParseException {
		Collection<tipoReserva> findTipoReserva = this.rs.findTipoReserva();
		for(tipoReserva r:findTipoReserva) {
			if(r.getName().equals(text)) {
				return r;
			}
		}
		throw new ParseException("Reserva not found: " + text, 0);
	}


}
