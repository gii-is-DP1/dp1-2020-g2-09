package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Cartas {

	private List<Carta> cartasList;
	
	@XmlElement
	public List<Carta> getCartasList() {
		if (cartasList == null) {
			cartasList = new ArrayList<>();
		}
		return cartasList;
	}
}
