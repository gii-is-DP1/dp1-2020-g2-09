package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaPedido;
	
	@ManyToMany(cascade = CascadeType.ALL)
	//JoinTable el nombre de la tabla que va a relacionar pedido con oferta
	//JoinColumn el nombre del atributo
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
	private Collection<Otros> otrosEnPedido;
	
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "estado_pedido")
	private EstadoPedido estadoPedido;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipo_pago")
	private TipoPago tipoPago;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tipo_envio")
	private TipoEnvio tipoEnvio;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pedidocliente")
	private Cliente cliente;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Reclamacion> reclamacion;
	
}
