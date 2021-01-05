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
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.ReclamacionService;

@ExtendWith(MockitoExtension.class)
public class ReclamacionFormatterTests {
	@Mock
	private ReclamacionService reclamacionService;

	private ReclamacionFormatter reclamacionFormatter;

	@BeforeEach
	void setup() {
		reclamacionFormatter = new ReclamacionFormatter(reclamacionService);
	}

	@Test
	void testPrint() {
		Reclamacion reclamacion = new Reclamacion();
		//reclamacion.setFechaReclamacion(LocalDate.of(2020, 11, 24));
		reclamacion.setObservacion("aaaa");
		String reclamacionObservacion = reclamacionFormatter.print(reclamacion, Locale.ENGLISH);
		assertEquals("aaaa", reclamacionObservacion);
	}

	 @Test
	    void shouldParse() throws ParseException {
	        Mockito.when(reclamacionService.findReclamaciones()).thenReturn((List<Reclamacion>) makeReclamaciones());
	        Reclamacion reclamaciones = reclamacionFormatter.parse("rec", Locale.ENGLISH);
	        assertEquals("rec", reclamaciones.getObservacion());
	    }

	    @Test
	    void shouldThrowParseException() throws ParseException {
	        Mockito.when(reclamacionService.findReclamaciones()).thenReturn((List<Reclamacion>) makeReclamaciones());
	        Assertions.assertThrows(ParseException.class, () -> {
	            reclamacionFormatter.parse("a1", Locale.ENGLISH);
	        });
	    }
	
	private Collection<Reclamacion> makeReclamaciones() {
		Collection<Reclamacion> reclamaciones = new ArrayList<>();
		reclamaciones.add(new Reclamacion() {
			{
				setObservacion("rec2");
			}
		});
		reclamaciones.add(new Reclamacion() {
			{
				setObservacion("rec");
			}
		});
		return reclamaciones;
	}
	

}
