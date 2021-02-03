package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Cuenta extends BaseEntity {
	
	
	@Column(name = "nombre")
	@NotNull
	@Size(min = 2, max = 20)
	private String nombre;
	
	@Column(name = "apellidos")
	@NotNull
	@Size(min = 2, max = 20)
	private String apellidos;
	
	@Column(name = "fecha_nacimiento")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull
	private LocalDate fechaNacimiento;
	
	@Column(name = "telefono")
	@NotNull
	@Min(value = 100000000)
	@Max(value = 999999999)
	//@Length(min = 9, max = 9)
	private Integer telefono;
	
	@OneToOne(cascade = CascadeType.ALL)//crea un usuario para una sola cuenta
    @JoinColumn(name = "usuario", referencedColumnName = "username")
	@NotNull
	private User user;
	
	@Column(name = "email")
	@NotNull
	@Email
	private String email;
	

}
