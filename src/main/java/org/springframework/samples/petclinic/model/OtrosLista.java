package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class OtrosLista {

	
private List<Otros> otrosList;
	
	@XmlElement
	public List<Otros> getOtrosList() {
		if (otrosList == null) {
			otrosList = new ArrayList<>();
		}
		return otrosList;
	}
}
