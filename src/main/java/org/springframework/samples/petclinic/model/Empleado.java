package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Empleado extends Cuenta{

	@Column(name = "fecha_inicio_contrato")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicioContrato;
	
	@Column(name = "fecha_fin_contrato")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFinContrato;
}
