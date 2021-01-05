package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.stereotype.Component;


@Component
public class NivelSocioFormatter implements Formatter<NivelSocio> {

	private final OfertaService ofertaService;

	@Autowired
	public NivelSocioFormatter(OfertaService ofertaService) {
		this.ofertaService = ofertaService;
	}

	@Override
	public String print(NivelSocio nivelSocio, Locale locale) {
		return nivelSocio.getName();
	}

	@Override
	public NivelSocio parse(String text, Locale locale) throws ParseException {
		Collection<NivelSocio> findNivelSocio = this.ofertaService.findNivelSocio();
		for (NivelSocio nivelSocio : findNivelSocio) {
			if (nivelSocio.getName().equals(text)) {
				return nivelSocio;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
