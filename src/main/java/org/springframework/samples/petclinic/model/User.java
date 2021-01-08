package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	String username;
	
	@NotNull
	String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private Set<Authorities> authorities;
}
