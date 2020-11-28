package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Bebidas")
public class Bebida extends Producto {

	@ManyToOne
	@JoinColumn(name = "tamaño")
	private TamanoProducto tamaño;

	@Column(name = "es_carbonatada")
	@NotNull
	private Boolean esCarbonatada;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCartaBebida", joinColumns =@JoinColumn(name= "bebidasEnCarta"))
	private Collection<Carta> cartaDeBebidas;
}