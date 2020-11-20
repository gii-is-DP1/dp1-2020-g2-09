package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Double precio;
	
	@Column(name = "gastos_envio")
	private Double gastosEnvio;
	
	@Column(name = "direccion")
	@NotNull
	private String direccion;
	
	@Column(name = "fecha_pedido")
	@NotNull
	private LocalDate fechaPedido;
	
	@ManyToOne
	@JoinColumn(name = "estado_pedido")
	private EstadoPedido estadoPedido;
	
	@ManyToOne
	@JoinColumn(name = "tipo_pago")
	private TipoPago tipoPago;
	
	@ManyToOne
	@JoinColumn(name = "tipo_envio")
	private TipoEnvio tipoEnvio;
	
}