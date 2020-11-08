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
@Table(name = "Cuentas")
public class Cuentas extends BaseEntity {
	
	@Column(name = "nombre")
	@NotNull
	private String nombre;
	
	@Column(name = "apellidos")
	@NotNull
	private String apellidos;
	
	@Column(name = "fecha_nacimiento")
	@NotNull
	private LocalDate fechaNacimiento;
	
	@Column(name = "telefono")
	@NotNull
	private Integer telefono;
	
	@Column(name = "nombre_usuario")
	@NotNull
	private String nombreUsuario;
	
	@Column(name = "contraseña")
	@NotNull
	private String contraseña;
	
	@Column(name = "email")
	@NotNull
	private String email;
	

}
