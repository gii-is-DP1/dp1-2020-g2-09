package org.springframework.samples.petclinic.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tamaño")
public class tamaño extends NamedEntity{

	@ManyToOne
	@JoinColumn(name = "tamaño")
	private tamaño tamaño;
	
	@Column(name = "esCarbonatada")
	private Boolean esCarbonatada;
}
