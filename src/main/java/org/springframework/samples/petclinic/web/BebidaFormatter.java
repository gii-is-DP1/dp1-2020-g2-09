package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.stereotype.Component;


@Component
public class BebidaFormatter implements Formatter<Bebida> {

	private final BebidaService bebidaService;

	@Autowired
	public BebidaFormatter(BebidaService bebidaService) {
		this.bebidaService = bebidaService;
	}

	@Override
	public String print(Bebida b, Locale locale) {
		return b.getNombre();
	}

	@Override
	public Bebida parse(String text, Locale locale) throws ParseException {
		Collection<Bebida> findBebidas = this.bebidaService.findBebidas();
		for (Bebida b : findBebidas) {
			if (b.getNombre().equals(text)) {
				return b;
			}
		}
		throw new ParseException("bebida not found: " + text, 0);
	}

}