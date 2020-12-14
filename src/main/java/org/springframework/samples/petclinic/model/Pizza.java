package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Pizzas")
public class Pizza extends Producto {

	@ManyToOne
	@JoinColumn(name = "tamano_producto" )
	private TamanoProducto tamano;

	@ManyToOne
	@JoinColumn(name = "tipoMasa")
	private tipoMasa tipoMasa;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "composicionCartaPizza", joinColumns =@JoinColumn(name= "pizzasEnCarta"))
	private Collection<Carta> cartaDePizzas;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	private Collection<Ingrediente> ingredientes;

}	