package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

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
	//hay que poner algo de los productos?
}
