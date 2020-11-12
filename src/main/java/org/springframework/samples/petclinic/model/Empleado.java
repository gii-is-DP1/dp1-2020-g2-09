package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class Empleado extends Cuenta{

	@Column(name = "fecha_inicio_contrato")
	private LocalDate fechaInicioContrato = LocalDate.now();
	
	@Column(name = "fecha_fin_contrato")
	private LocalDate fechaFinContrato;
}
