package org.springframework.samples.petclinic.model;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "Ofertas")
public class Oferta extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	private TamanoProducto tamano;
	
	@Column(name = "coste")
	@NotNull
	private Double coste;
	
	@Column(name = "fechaInicial")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicial;
	
	@ManyToOne
	@JoinColumn(name = "tamano_id")
	private NivelSocio nivelSocio;
	
	@Column(name = "fechaFinal")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFinal;

}
