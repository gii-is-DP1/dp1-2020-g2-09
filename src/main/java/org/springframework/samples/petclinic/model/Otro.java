package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Otros")
public class Otro extends Producto {

	@ManyToMany(cascade = CascadeType.REFRESH)
	private Collection<Ingrediente> ingredientes;
	
}