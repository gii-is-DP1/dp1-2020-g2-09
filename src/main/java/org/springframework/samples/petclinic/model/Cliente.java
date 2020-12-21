package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Clientes")
public class Cliente extends Cuenta {

	@Column(name = "fechaAlta")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaAlta = LocalDate.now();
	
//	@OneToMany
//	@JoinColumn(name = "pedidocliente")
//	private Collection<Pedido> pedidos;
	
	
}
