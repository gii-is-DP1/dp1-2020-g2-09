package org.springframework.samples.petclinic.model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "Reservas")
public class Reserva extends BaseEntity {

	@Column(name = "numeroPersonas")
	@NotNull
	@Max(6)
	@Min(0)
	private Integer numeroPersonas;

	@Column(name = "fecha_reserva")
	@NotNull
	private LocalDate fechaReserva;

	@Column(name = "hora_reserva")
	@NotNull
	private LocalTime hora;

	@ManyToOne
	@JoinColumn(name = "tipo_reserva")
	private tipoReserva tipoReserva;
	
	@ManyToMany
	@JoinTable(name = "reservaAsociada", joinColumns =@JoinColumn(name= "mesasReservadas"))
	private Collection<Reserva> reserva;	
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "usuario",referencedColumnName="usuario")
//	private Cliente cliente;
}
