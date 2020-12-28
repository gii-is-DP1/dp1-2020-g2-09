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

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reservas")
public class Reserva extends BaseEntity {

	@Column(name = "numeroPersonas")
	@NotNull
	private Integer numeroPersonas;

	@Column(name = "fecha_reserva") 
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaReserva;

	@Column(name = "hora_reserva")
	@NotNull
	private LocalTime hora;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tipo_reserva")
	private tipoReserva tipoReserva;
	
	@ManyToMany (cascade = CascadeType.MERGE)
	@JoinTable(name = "reservaMesa", joinColumns =@JoinColumn(name= "reservaId" ))
	private Collection<Mesa> mesasEnReserva;
	
//	@ManyToOne
//	@JoinColumn(name = "usuario",referencedColumnName="usuario")
//	private Cliente cliente;
}
