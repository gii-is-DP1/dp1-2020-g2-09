package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Reclamaciones")
public class Reclamacion extends BaseEntity {
	
	
//	@Column(name = "fechaReclamacion")
//	@DateTimeFormat(pattern = "yyyy/MM/dd")
//	@NotNull
//	private LocalDate fechaReclamacion;
	
	@Column(name = "observacion")
	@NotNull
	private String observacion;
	
	@Column(name = "respuesta")
	//@Value("Lo sentimos, perdone las molestias")
	private String respuesta;
	

}
