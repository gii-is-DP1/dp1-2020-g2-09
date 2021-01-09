package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pizzas")
public class Pizza extends Producto {
    
	
//	public Pizza(Integer id,Integer contador,Integer coste,String nombre,TamanoProducto tamano, tipoMasa tipoMasa, Cliente cliente, Boolean personalizada) {
//		super();
//		this.tamano = tamano;
//		this.tipoMasa = tipoMasa;
//		this.cliente = cliente;
//		Personalizada = personalizada;
//	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tamano_producto" )
	private TamanoProducto tamano;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipoMasa")
	private tipoMasa tipoMasa;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "composicionCartaPizza", joinColumns =@JoinColumn(name= "pizzasEnCarta"))
//	private Collection<Carta> cartaDePizzas;

	
	@ManyToMany(cascade = CascadeType.REFRESH)
	private Collection<Ingrediente> ingredientes;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Cliente cliente;
	
	@Column(name = "Personalizada")
	@NotNull
	private Boolean Personalizada;
}	