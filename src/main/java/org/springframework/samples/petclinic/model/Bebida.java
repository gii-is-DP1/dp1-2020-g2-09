package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
}