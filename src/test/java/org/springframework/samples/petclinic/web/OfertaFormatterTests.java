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
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.service.OfertaService;

@ExtendWith(MockitoExtension.class)
public class OfertaFormatterTests {

	@Mock
	private OfertaService ofertaService;

	private OfertaFormatter ofertaFormatter;

	@BeforeEach
	void setup() {
		ofertaFormatter = new OfertaFormatter(ofertaService);
	}

	@Test
	void testPrint() {
		Oferta oferta = new Oferta();
		oferta.setId(1);
		String id = String.valueOf(oferta.getId());
		String ofertaId = ofertaFormatter.print(oferta, Locale.ENGLISH);
		assertEquals("1", ofertaId);
	}

	 @Test
	    void shouldParse() throws ParseException {
	        Mockito.when(ofertaService.findOfertas()).thenReturn((List<Oferta>) makeOfertas());
	        Oferta ofertas = ofertaFormatter.parse2(2, Locale.ENGLISH);
	        assertEquals(2, ofertas.getId());
	    }

	    @Test
	    void shouldThrowParseException() throws ParseException {
	        Mockito.when(ofertaService.findOfertas()).thenReturn((List<Oferta>) makeOfertas());
	        Assertions.assertThrows(ParseException.class, () -> {
	            ofertaFormatter.parse2(34, Locale.ENGLISH);
	        });
	    }
	
	private Collection<Oferta> makeOfertas() {
		Collection<Oferta> ofertas = new ArrayList<>();
		ofertas.add(new Oferta() {
			{
				setId(1);
			}
		});
		ofertas.add(new Oferta() {
			{
				setId(2);
			}
		});
		return ofertas;
	}
}
