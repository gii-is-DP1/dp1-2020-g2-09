package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Component;


@Component
public class TamanoProductoFormatter2 implements Formatter<TamanoProducto> {

	private final PizzaService pizzaService;

	@Autowired
	public TamanoProductoFormatter2(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}

	@Override
	public String print(TamanoProducto tamanoProducto, Locale locale) {
		return tamanoProducto.getName();
	}

	@Override
	public TamanoProducto parse(String text, Locale locale) throws ParseException {
		Collection<TamanoProducto> findTamanoProducto = this.pizzaService.findTamaño();
		for (TamanoProducto tamanoProducto : findTamanoProducto) {
			if (tamanoProducto.getName().equals(text)) {
				return tamanoProducto;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}

