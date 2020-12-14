package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.stereotype.Component;
@Component
public class IngredienteFormatter implements Formatter<Ingrediente>{

	private final IngredienteService Is;
	
	@Autowired
	public IngredienteFormatter(IngredienteService IngredienteService) {
		this.Is = IngredienteService;
	}
	@Override
	public String print(Ingrediente object, Locale locale) {
		return object.getNombre();
	}

	@Override
	public Ingrediente parse(String text, Locale locale) throws ParseException {
		Collection<Ingrediente> findIngrediente = this.Is.findIngredientes();
		for(Ingrediente i:findIngrediente) {
			if(i.getNombre().equals(text)) {
				return i;
			}
		}
		throw new ParseException("Reserva not found: " + text, 0);
	}

}