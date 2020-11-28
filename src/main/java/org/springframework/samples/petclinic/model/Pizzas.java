package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.txw2.annotation.XmlElement;


@XmlRootElement
public class Pizzas {

	private List<Pizza> pizzasList;
	
	@XmlElement
	public List<Pizza> getPizzasList() {
		if (pizzasList == null) {
			pizzasList = new ArrayList<>();
		}
		return pizzasList;
	}

}
