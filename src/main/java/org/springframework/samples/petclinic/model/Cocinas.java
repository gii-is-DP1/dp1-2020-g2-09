package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Cocinas {

private List<Cocina> cocinerosList;
	
	@XmlElement
	public List<Cocina> getCocinerosList() {
		if (cocinerosList == null) {
			cocinerosList = new ArrayList<>();
		}
		return cocinerosList;
	}
}
