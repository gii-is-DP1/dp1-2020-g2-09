package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Pizzas")
public class Pizza extends Producto {

	@ManyToOne
	@JoinColumn(name = "tamaño")
	private TamanoProducto tamanyo;

	@ManyToOne
	@JoinColumn(name = "tipoMasa")
	private tipoMasa tipoMasa;
	
	@ManyToMany
	@JoinTable(name = "composicionCarta", joinColumns =@JoinColumn(name= "pizzasEnCarta"))
	private Collection<Carta> cartaDePizzas;
}	