package org.springframework.samples.petclinic.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class Producto extends BaseEntity {
	
	@Column(name = "nombre")
	@NotNull
	@Size(min = 2, max = 255)
	private String nombre;

	@Column(name = "coste")
	@NotNull
	@Min(0)
	@Max(100)
	private Integer coste;
	
	@Column(name = "contador")
	@NotNull
	private Integer contador=1;

	@ManyToMany (cascade = CascadeType.MERGE)
	@JoinTable(name = "OfertaProducto", joinColumns =@JoinColumn(name= "ofertaId" ))
	private Collection<Oferta> productoEnOferta;
}