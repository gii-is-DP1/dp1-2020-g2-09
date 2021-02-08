package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.stereotype.Component;

@Component
public class ReclamacionFormatter implements Formatter<Reclamacion>{
private final ReclamacionService rs;
	
	@Autowired
	public ReclamacionFormatter(ReclamacionService reclamacionService) {
		this.rs = reclamacionService;
	}
	@Override
	public String print(Reclamacion object, Locale locale) {
		return object.getObservacion();

	}

	@Override
	public Reclamacion parse(String text, Locale locale) throws ParseException {
		Collection<Reclamacion> findReclamacion = this.rs.findReclamaciones();
		for(Reclamacion r:findReclamacion) {
			if(r.getObservacion().equals(text)) {
				return r;
			}
		}
		throw new ParseException("Reclamacion not found: " + text, 0);
	}
}
