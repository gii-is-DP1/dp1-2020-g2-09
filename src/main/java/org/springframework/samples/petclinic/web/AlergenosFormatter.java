package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.stereotype.Component;


@Component
public class AlergenosFormatter implements Formatter<Alergenos> {

	private final IngredienteService ingredienteService;

	@Autowired
	public AlergenosFormatter(IngredienteService ingredienteService) {
		this.ingredienteService = ingredienteService;
	}

	@Override
	public String print(Alergenos alergenos, Locale locale) {
		return alergenos.getName();
	}

	@Override
	public Alergenos parse(String text, Locale locale) throws ParseException {
		Collection<Alergenos> findAlergenos = this.ingredienteService.findAlergenos();
		for (Alergenos alergenos : findAlergenos) {
			if (alergenos.getName().equals(text)) {
				return alergenos;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}

