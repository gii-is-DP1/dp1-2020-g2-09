package org.springframework.samples.petclinic.model;


import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Mesas")
public class Mesa extends BaseEntity{
	
	@Column(name = "capacidad")
	@NotNull
	@Min(0)
	@Max(6)
	private Integer capacidad;
	
	@ManyToMany
	@JoinTable(name = "reservaAsociada", joinColumns =@JoinColumn(name= "mesasReservadas"))
	private Collection<Reserva> reserva;


}
