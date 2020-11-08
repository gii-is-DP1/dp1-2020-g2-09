package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Cuentas {

private List<Cuenta> cuentasList;
	
	@XmlElement
	public List<Cuenta> getCuentasList() {
		if (cuentasList == null) {
			cuentasList = new ArrayList<>();
		}
		return cuentasList;
	}
}
