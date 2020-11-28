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
	
	@Column(name = "fecha")
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fecha = LocalDate.now();
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCartaPizza", joinColumns =@JoinColumn(name= "pizzasEnCarta" ))
	private Collection<Pizza> cartaDePizzas;
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCartaBebida", joinColumns =@JoinColumn(name= "bebidasEnCarta" ))
	private Collection<Bebida> cartaDeBebidas;
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCartaOtros", joinColumns =@JoinColumn(name= "otrosEnCarta" ))
	private Collection<Otros> cartaDeOtros;
}
