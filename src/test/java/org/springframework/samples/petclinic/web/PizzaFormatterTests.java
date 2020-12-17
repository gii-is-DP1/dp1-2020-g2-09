package org.springframework.samples.petclinic.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.service.PizzaService;


@ExtendWith(MockitoExtension.class)

public class PizzaFormatterTests {

	@Mock
	private PizzaService pizzaService;

	private PizzaFormatter pizzaFormatter;

	@BeforeEach
	void setup() {
		pizzaFormatter = new PizzaFormatter(pizzaService);
	}

	@Test
	void testPrint() {
		Pizza pi = new Pizza();
		pi.setNombre("Carbonara");
		String PizzaName = pizzaFormatter.print(pi, Locale.ENGLISH);
		assertEquals("Carbonara", PizzaName);
	}

	@Test
	void shouldParse() throws ParseException {
		Mockito.when(pizzaService.findPizzas()).thenReturn((List<Pizza>) makePizza());
		Pizza pi = pizzaFormatter.parse("Barbacoa", Locale.ENGLISH);
		assertEquals("Barbacoa", pi.getNombre());
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		Mockito.when(pizzaService.findPizzas()).thenReturn((List<Pizza>) makePizza());
		Assertions.assertThrows(ParseException.class, () -> {
			pizzaFormatter.parse("Vegetal", Locale.ENGLISH);
		});
	}

	private Collection<Pizza> makePizza() {
		Collection<Pizza> pi = new ArrayList<>();
		pi.add(new Pizza() {
			{
				setNombre("Pizza Rara");
			}
		});
		pi.add(new Pizza() {
			{
				setNombre("Barbacoa");
			}
		});
		return pi;
	}
}
