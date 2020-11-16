package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;


@XmlRootElement
public class Productos {

private List<Producto> productosList;
	
	@XmlElement
	public List<Producto> getProductosList() {
		if (productosList == null) {
			productosList = new ArrayList<>();
		}
		return productosList;
	}
}
