package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Reservas {
	private List<Reserva> Reserva;

	@XmlElement
	public List<Reserva> getReservasList() {
		if (Reserva == null) {
			Reserva = new ArrayList<>();
		}
		return Reserva;
	}
}
