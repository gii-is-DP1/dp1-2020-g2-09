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
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.service.BebidaService;


@ExtendWith(MockitoExtension.class)
class BebidaFormatterTests {

	@Mock
	private BebidaService bebidaService;

	private BebidaFormatter bebidaFormatter;

	@BeforeEach
	void setup() {
		bebidaFormatter = new BebidaFormatter(bebidaService);
	}

	@Test
	void testPrint() {
		Bebida b = new Bebida();
		b.setNombre("Hidromiel");
		String NombreBebida = bebidaFormatter.print(b, Locale.ENGLISH);
		assertEquals("Hidromiel", NombreBebida);
	}

	@Test
	void shouldParse() throws ParseException {
		Mockito.when(bebidaService.findBebidas()).thenReturn((List<Bebida>) makeBebida());
		Bebida b = bebidaFormatter.parse("Hidromiel", Locale.ENGLISH);
		assertEquals("Hidromiel", b.getNombre());
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		Mockito.when(bebidaService.findBebidas()).thenReturn((List<Bebida>) makeBebida());
		Assertions.assertThrows(ParseException.class, () -> {
			bebidaFormatter.parse("Mojito", Locale.ENGLISH);
		});
	}

	private Collection<Bebida> makeBebida() {
		Collection<Bebida> b = new ArrayList<>();
		b.add(new Bebida() {
			{
				setNombre("Leche");
			}
		});
		b.add(new Bebida() {
			{
				setNombre("Hidromiel");
			}
		});
		return b;
	}

}