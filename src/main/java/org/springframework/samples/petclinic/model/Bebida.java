package org.springframework.samples.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Bebidas")
public class Bebida extends Producto {

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tamano_producto")
	private TamanoProducto tamano;

	@Column(name = "es_carbonatada")
	@NotNull
	private Boolean esCarbonatada;

}