package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Reclamaciones {

private List<Reclamacion> reclamacionesList;
	
	@XmlElement
	public List<Reclamacion> getReclamacionesList() {
		if (reclamacionesList == null) {
			reclamacionesList = new ArrayList<>();
		}
		return reclamacionesList;
	}
}

