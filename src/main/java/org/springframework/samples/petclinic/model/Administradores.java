package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Administradores {

	
private List<Administrador> administradoresList;
	
	@XmlElement
	public List<Administrador> getAdministradoresList() {
		if (administradoresList == null) {
			administradoresList = new ArrayList<>();
		}
		return administradoresList;
	}
	
}
