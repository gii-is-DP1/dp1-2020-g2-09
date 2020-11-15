package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Mesas {

private List<Mesa> mesasList;
	
	@XmlElement
	public List<Mesa> getMesasList() {
		if (mesasList == null) {
			mesasList = new ArrayList<>();
		}
		return mesasList;
	}
}
