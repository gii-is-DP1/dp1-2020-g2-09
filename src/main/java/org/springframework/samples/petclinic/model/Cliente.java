package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Clientes")
public class Cliente extends Cuenta {

	@Column(name = "fechaAlta")
	@NotNull
	private LocalDate fechaAlta;
	
	
}
