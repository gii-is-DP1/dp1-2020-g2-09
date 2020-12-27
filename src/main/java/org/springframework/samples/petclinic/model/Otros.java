package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Otros {

	
private List<Otro> otrosLista;
	
	@XmlElement
	public List<Otro> getOtrosLista() {
		if (otrosLista == null) {
			otrosLista = new ArrayList<>();
		}
		return otrosLista;
	}
}
