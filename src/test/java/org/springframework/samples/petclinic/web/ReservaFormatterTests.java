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
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.service.ReservaService;

/**
 * Test class for {@link PetTypeFormatter}
 *
 * @author Colin But
 */
@ExtendWith(MockitoExtension.class)
class ReservaFormatterTests {

	@Mock
	private ReservaService reservaService;

	private ReservaFormatter reservaFormatter;

	@BeforeEach
	void setup() {
		reservaFormatter = new ReservaFormatter(reservaService);
	}

	@Test
	void testPrint() {
		Reserva r = new Reserva();
		r.setId(1);
		String reservaId = reservaFormatter.print(r, Locale.ENGLISH);
		assertEquals("1", reservaId);
	}

	@Test
	void shouldParse() throws ParseException {
		Mockito.when(reservaService.findReservas()).thenReturn((List<Reserva>) makeReserva());
		Reserva r = reservaFormatter.parse("1", Locale.ENGLISH);
		assertEquals(1, r.getId());
	}

	@Test
	void shouldThrowParseException() throws ParseException {
		Mockito.when(reservaService.findReservas()).thenReturn((List<Reserva>) makeReserva());
		Assertions.assertThrows(ParseException.class, () -> {
			reservaFormatter.parse("2", Locale.ENGLISH);
		});
	}

	/**
	 * Helper method to produce some sample pet types just for test purpose
	 * @return {@link Collection} of {@link PetType}
	 */
	private Collection<Reserva> makeReserva() {
		Collection<Reserva> r = new ArrayList<>();
		r.add(new Reserva() {
			{
				setId(1);
			}
		});
		r.add(new Reserva() {
			{
				setId(1);
			}
		});
		return r;
	}

}
