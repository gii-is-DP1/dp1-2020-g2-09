package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Ingrediente extends BaseEntity {
	
	@Column(name = "nombre")
	@NotNull
	@Size(min = 2, max = 20)
	private String nombre;
	
	@Column(name = "tipo")
	@NotNull
	private String tipo;
	
	@Column(name = "fechaCaducidad")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaCaducidad;
	

	@ManyToOne
	@JoinColumn(name = "Alergenos")
	private Alergenos alergenos;

	

}