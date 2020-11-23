package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;



@XmlRootElement
public class Clientes {

	private List<Cliente> clientesList;
	
	@XmlElement
	public List<Cliente> getClientesList() {
		if (clientesList == null) {
			clientesList = new ArrayList<>();
		}
		return clientesList;
	}
}
