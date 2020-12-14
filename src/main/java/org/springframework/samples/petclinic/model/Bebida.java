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

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "tamano_producto")
	private TamanoProducto tamano;

	@Column(name = "es_carbonatada")
	@NotNull
	private Boolean esCarbonatada;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "composicionCartaBebida", joinColumns =@JoinColumn(name= "bebidasEnCarta"))
//	private Collection<Carta> cartaDeBebidas;
}