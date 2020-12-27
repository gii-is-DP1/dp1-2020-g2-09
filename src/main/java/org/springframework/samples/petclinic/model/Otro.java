package org.springframework.samples.petclinic.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Otros")
public class Otro extends Producto {

	@ManyToMany(cascade = CascadeType.REFRESH)
	private Collection<Ingrediente> ingredientes;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "composicionCartaOtros", joinColumns =@JoinColumn(name= "otrosEnCarta"))
//	private Collection<Carta> cartaDeOtros;
	
}