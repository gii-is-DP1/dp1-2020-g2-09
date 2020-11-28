package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@ManyToMany(cascade = CascadeType.ALL)
	//JoinTable el nombre de la tabla que va a relacionar pedido con oferta
	//JoinColumn el nombre del atributo
	@JoinTable(name = "ofertaPedido", joinColumns =@JoinColumn(name= "PedidosConOferta" ))
	private Collection<Pedido> pedidosConOferta;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "estado_pedido")
	private EstadoPedido estadoPedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_pago")
	private TipoPago tipoPago;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_envio")
	private TipoEnvio tipoEnvio;
	
	@ManyToOne
	@JoinColumn(name = "pedidocliente")
	private Cliente cliente;
	
	
}
