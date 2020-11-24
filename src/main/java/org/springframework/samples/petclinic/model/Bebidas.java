package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Bebidas {
private List<Bebida> bebidasList;
	
	@XmlElement
	public List<Bebida> getBebidasList() {
		if (bebidasList == null) {
			bebidasList = new ArrayList<>();
		}
		return bebidasList;
	}
}
