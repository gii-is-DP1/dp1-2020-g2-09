package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class Empleado extends Cuenta{

	@Column(name = "fecha_inicio_contrato")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaInicioContrato = LocalDate.now();
	
	@Column(name = "fecha_fin_contrato")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate fechaFinContrato;
}
