package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ofertas")
public class Oferta extends BaseEntity{
	
	@Size(min = 3, max = 50)
	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tamano_oferta")
	private TamanoOferta tamanoOferta;
	
	@Column(name = "coste")
	@NotNull
	private Double coste;
	
	@Column(name = "fechaInicial")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicial;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "nivel_socio")
	private NivelSocio nivelSocio;
	
	@Column(name = "fechaFinal")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFinal;
	
	@Column(name = "estadoOferta")
	@NotNull
	private Boolean estadoOferta=true;
	
	@ManyToMany (cascade = CascadeType.MERGE)
	@JoinTable(name = "OfertaPizza", joinColumns =@JoinColumn(name= "ofertaId" ))
	private List<Pizza> pizzasEnOferta;
	
	@ManyToMany (cascade = CascadeType.MERGE)
	@JoinTable(name = "OfertaBebida", joinColumns =@JoinColumn(name= "ofertaId" ))
	private Collection<Bebida> bebidasEnOferta;
	
	@ManyToMany (cascade = CascadeType.MERGE)
	@JoinTable(name = "OfertaOtro", joinColumns =@JoinColumn(name= "ofertaId" ))
	private Collection<Otro> otrosEnOferta;
	
}
