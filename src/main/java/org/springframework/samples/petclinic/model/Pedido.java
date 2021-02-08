package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pedido")

public class Pedido extends BaseEntity{
	
	@Column(name = "precio")
	@NotNull
	@Min(0)
	private Double precio;
	
	@Column(name = "gastos_envio")
	private Double gastosEnvio;
	
	@Column(name = "direccion")
	@NotNull
	private String direccion;
	
	@Column(name = "fecha_pedido")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaPedido;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "ofertaPedido", joinColumns =@JoinColumn(name= "pedidoId" ))
	private Collection<Oferta> ofertasEnPedido;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "productoPizzaPedido", joinColumns =@JoinColumn(name= "pedidoId" ))
	private Collection<Pizza> pizzasEnPedido;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "productoBebidaPedido", joinColumns =@JoinColumn(name= "pedidoId" ))
	private Collection<Bebida> bebidasEnPedido;
	
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(name = "productoOtrosPedido", joinColumns =@JoinColumn(name= "pedidoId" ))
	private Collection<Otro> otrosEnPedido;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "estado_pedido")
	private EstadoPedido estadoPedido;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipo_pago")
	private TipoPago tipoPago;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipo_envio")
	private TipoEnvio tipoEnvio;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "pedidocliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Reclamacion> reclamacion;
	
}
