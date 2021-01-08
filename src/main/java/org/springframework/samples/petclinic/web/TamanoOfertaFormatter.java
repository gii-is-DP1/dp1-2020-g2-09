package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.stereotype.Component;


@Component
public class TamanoOfertaFormatter implements Formatter<TamanoOferta> {

	private final OfertaService ofertaService;

	@Autowired
	public TamanoOfertaFormatter(OfertaService ofertaService) {
		this.ofertaService = ofertaService;
	}

	@Override
	public String print(TamanoOferta tamanoOferta, Locale locale) {
		return tamanoOferta.getName();
	}

	@Override
	public TamanoOferta parse(String text, Locale locale) throws ParseException {
		Collection<TamanoOferta> findTamanoOferta = this.ofertaService.findTamanoOferta();
		for (TamanoOferta tamanoOferta : findTamanoOferta) {
			if (tamanoOferta.getName().equals(text)) {
				return tamanoOferta;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}

