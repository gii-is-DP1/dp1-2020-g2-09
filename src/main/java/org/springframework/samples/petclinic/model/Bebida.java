package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name = "Bebidas")
public class Bebida extends Producto {

	@ManyToOne
	@JoinColumn(name = "tamaño")
	private tamaño tamaño;

	@Column(name = "es_carbonatada")
	@NotNull
	private Boolean esCarbonatada;
	
	@ManyToMany
	@JoinTable(name = "composicionCarta", joinColumns =@JoinColumn(name= "bebidasEnCarta"))
	private Collection<Carta> cartaDeBebidas;
}