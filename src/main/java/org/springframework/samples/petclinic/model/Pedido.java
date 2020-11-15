package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Pedido")

public class Pedido extends BaseEntity{
	
	@Column(name = "precio")
	@NotNull
	private Integer precio;
	
	@Column(name = "gastos_envio")
	private Integer gastosEnvio;
	
	@Column(name = "direccion")
	@NotNull
	private String direccion;
	
	@Column(name = "fecha_pedido")
	@NotNull
	private LocalDate fechaPedido;
	


}
