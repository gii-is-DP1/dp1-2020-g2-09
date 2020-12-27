package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.stereotype.Component;

@Component
public class OtrosFormatter implements Formatter<Otro> {

	private final OtrosService otroService;

	@Autowired
	public OtrosFormatter(OtrosService otrosService) {
		this.otroService = otrosService;
	}

	@Override
	public String print(Otro otros, Locale locale) {
		return otros.getNombre();
	}

	 @Override
	    public Otro parse(String text, Locale locale) throws ParseException {
	        Collection<Otro> findOtros = this.otroService.findOtros();
	        for (Otro b : findOtros) {
	            if (b.getNombre().equals(text)) {
	                return b;
	            }
	        }
	        throw new ParseException("otros not found: " + text, 0);
	    }
	 
}
