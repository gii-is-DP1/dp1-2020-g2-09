package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public class Cuenta extends BaseEntity {
	
	@Column(name = "nombre")
	@NotNull
	@Size(min = 2, max = 10)
	private String nombre;
	
	@Column(name = "apellidos")
	@NotNull
	private String apellidos;
	
	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaNacimiento;
	
	@Column(name = "telefono")
	@NotNull
	private Integer telefono;
	
	@Column(name = "usuario")
	@NotNull
	private String usuario;
	
	@Column(name = "contraseña")
	@NotNull
	private String contraseña;
	
	@Column(name = "email")
	@NotNull
	private String email;
	

}
