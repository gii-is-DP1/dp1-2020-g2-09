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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Cartas")
public class Carta extends BaseEntity{
	
	
	@Column(name = "nombre")
	@NotNull
	private String nombre;
	
	@Column(name = "fecha_creacion")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaCreacion = LocalDate.now();
	
	@Column(name = "fecha_final")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFinal;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "composicionCartaPizza", joinColumns =@JoinColumn(name= "cartaId" ))
	private Collection<Pizza> pizzasEnCarta;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "composicionCartaBebida", joinColumns =@JoinColumn(name= "cartaId" ))
	private Collection<Bebida> bebidasEnCarta;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "composicionCartaOtros", joinColumns =@JoinColumn(name= "cartaId" ))
	private Collection<Otros> otrosEnCarta;
}
