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
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Ofertas")
public class Oferta extends BaseEntity{
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "tamano_oferta")
	private TamanoOferta tamanoOferta;
	
	@Column(name = "coste")
	@NotNull
	private Double coste;
	
	@Column(name = "fechaInicial")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicial;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	//JoinTable el nombre de la tabla que va a relacionar pedido con oferta
//	@JoinTable(name = "ofertaPedido")
//	private Collection<Pedido> PedidosConOferta;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "nivel_socio")
	private NivelSocio nivelSocio;
	
	@Column(name = "fechaFinal")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFinal;
	
//	public TamanoProducto getTamanoProducto() {
//		return this.tamanoProducto;
//	}
//
//	public void setTamanoProducto(TamanoProducto tamanoProducto) {
//		this.tamanoProducto = tamanoProducto;
//	}
//	
//	public NivelSocio getNivelSocio() {
//		return this.nivelSocio;
//	}
//
//	public void setNivelSocio(NivelSocio nivelSocio) {
//		this.nivelSocio = nivelSocio;
//	}
//	


}
