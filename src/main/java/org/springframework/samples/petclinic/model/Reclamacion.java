package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Reclamaciones")
public class Reclamacion extends BaseEntity {
	
	
	@Column(name = "fechaReclamacion")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaReclamacion;
	
	@Column(name = "observacion")
	@NotNull
	private String observacion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	

}
