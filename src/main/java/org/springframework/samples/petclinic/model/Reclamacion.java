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
	
	
	@Column(name = "observacion")
	@NotNull
	private String observacion;
	
	@Column(name = "respuesta")
	private String respuesta;
	

}
