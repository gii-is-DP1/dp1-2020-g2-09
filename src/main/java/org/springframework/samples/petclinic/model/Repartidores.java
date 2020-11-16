package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Repartidores {

private List<Repartidor> repartidoresList;
	
	@XmlElement
	public List<Repartidor> getRepartidoresList() {
		if (repartidoresList == null) {
			repartidoresList = new ArrayList<>();
		}
		return repartidoresList;
	}
	
}
