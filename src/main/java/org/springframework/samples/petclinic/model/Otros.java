package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	
}