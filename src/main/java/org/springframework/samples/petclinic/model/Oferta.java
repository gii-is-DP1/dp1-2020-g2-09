package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "Ofertas")
public class Oferta extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "tamano_producto")
	private TamanoProducto tamanoProducto;
	
	@Column(name = "coste")
	@NotNull
	private Double coste;
	
	@Column(name = "fechaInicial")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaInicial;
	
	@ManyToOne
	@JoinColumn(name = "nivel_socio")
	private NivelSocio nivelSocio;
	
	@Column(name = "fechaFinal")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaFinal;
	
	public TamanoProducto getTamanoProducto() {
		return this.tamanoProducto;
	}

	public void setType(TamanoProducto type) {
		this.tamanoProducto = tamanoProducto;
	}

}
