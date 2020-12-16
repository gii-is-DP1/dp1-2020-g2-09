package org.springframework.samples.petclinic.web;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Component;

@Component
public class PizzaFormatter implements Formatter<Pizza> {

		private final PizzaService piService;

		@Autowired
		public PizzaFormatter(PizzaService pizzaService) {
			this.piService = pizzaService;
		}

		@Override
		public String print(Pizza piz, Locale locale) {
			return piz.getNombre();
		}

		@Override
		public Pizza parse(String text, Locale locale) throws ParseException {
			Collection<Pizza> findPizza = this.piService.findPizzas();
			for (Pizza type : findPizza) {
				if (type.getNombre().equals(text)) {
					return type;
				}
			}
			throw new ParseException("pizza not found: " + text, 0);
		}
}
