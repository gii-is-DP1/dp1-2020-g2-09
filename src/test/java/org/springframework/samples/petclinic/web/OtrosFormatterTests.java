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
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.service.OtrosService;


@ExtendWith(MockitoExtension.class)
class OtrosFormatterTests {

	@Mock
	private OtrosService otrosService;

	private OtrosFormatter otrosFormatter;

	@BeforeEach
	void setup() {
		otrosFormatter = new OtrosFormatter(otrosService);
	}

	@Test
	void testPrint() {
		Otro otros = new Otro();
		otros.setNombre("Patatitas");
		String OtrosName = otrosFormatter.print(otros, Locale.ENGLISH);
		assertEquals("Patatitas", OtrosName);
	}

	@Test
	void shouldParse() throws ParseException {
		Mockito.when(otrosService.findOtros()).thenReturn((List<Otro>) makeOtros());
		Otro otros = otrosFormatter.parse("Patatuelas", Locale.ENGLISH);
		assertEquals("Patatuelas", otros.getNombre());
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		Mockito.when(otrosService.findOtros()).thenReturn((List<Otro>) makeOtros());
		Assertions.assertThrows(ParseException.class, () -> {
			otrosFormatter.parse("Patatillas", Locale.ENGLISH);
		});
	}

	private Collection<Otro> makeOtros() {
		Collection<Otro> otros = new ArrayList<>();
		otros.add(new Otro() {
			{
				setNombre("Patata");
			}
		});
		otros.add(new Otro() {
			{
				setNombre("Patatuelas");
			}
		});
		return otros;
	}

}
