package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	/*@Column(name = "pizzas")
	@NotNull
	private List<Pizza> pizzas;
	
	@Column(name = "entrantes")
	@NotNull
	private List<Entrantes> entrantes;
	
	@Column(name = "otros")
	@NotNull
	private List<Otros> otros;*/
}
