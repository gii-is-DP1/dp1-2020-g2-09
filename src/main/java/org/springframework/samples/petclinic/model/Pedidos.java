package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;

@XmlRootElement
public class Pedidos {
	
private List<Pedido> pedidosList;
	
	@XmlElement
	public List<Pedido> getPedidosList() {
		if (pedidosList == null) {
			pedidosList = new ArrayList<>();
		}
		return pedidosList;
	}

}
