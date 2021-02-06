package org.springframework.samples.petclinic.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CollectionType;

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
	private Double coste;
	
	
//	@ManyToMany (cascade = CascadeType.MERGE)
//	@JoinTable(name = "OfertaProducto", joinColumns =@JoinColumn(name= "ofertaId" ))
//	private Collection<Oferta> productoEnOferta;
}