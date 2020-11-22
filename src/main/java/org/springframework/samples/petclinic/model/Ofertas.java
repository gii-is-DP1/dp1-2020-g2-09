package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Ofertas {

	private List<Oferta> ofertasList;
		
		@XmlElement
		public List<Oferta> getOfertasList() {
			if (ofertasList == null) {
				ofertasList = new ArrayList<>();
			}
			return ofertasList;
		}
}

