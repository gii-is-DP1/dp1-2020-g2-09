package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Pizzas")
public class Pizza extends Producto {

	@ManyToOne
	@JoinColumn(name = "tamaño")
	private tamaño tamaño;

	@ManyToOne
	@JoinColumn(name = "tipoMasa")
	private tipoMasa tipoMasa;
}