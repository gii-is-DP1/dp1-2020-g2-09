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

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Cartas")
public class Carta extends BaseEntity{
	
	@Column(name = "fecha")
	@NotNull
	private LocalDate fecha;
	
	@Column(name = "Pizza")
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCarta", joinColumns =@JoinColumn(name= "pizzasEnCarta" ))
	private Collection<Pizza> cartaDePizzas;
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCarta", joinColumns =@JoinColumn(name= "bebidasEnCarta" ))
	private Collection<Bebida> cartaDeBebidas;
	
	@ManyToMany (cascade = CascadeType.REMOVE)
	@JoinTable(name = "composicionCarta", joinColumns =@JoinColumn(name= "otrosEnCarta" ))
	private Collection<Otros> cartaDeOtros;
}
