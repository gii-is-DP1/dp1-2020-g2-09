package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Mesas")
public class Mesa extends BaseEntity{
	
	@Column(name = "capacidad")
	@NotNull
	@Min(1)
	@Max(6)
	private Integer capacidad;
	
	@Version
	private Integer version;
	
}
