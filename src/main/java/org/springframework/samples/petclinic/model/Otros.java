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

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Otros")
public class Otros extends Producto {

	@Column(name = "ingredientes")
	private Ingrediente [] ingredientes;
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCartaOtros", joinColumns =@JoinColumn(name= "otrosEnCarta"))
	private Collection<Carta> cartaDeOtros;
}