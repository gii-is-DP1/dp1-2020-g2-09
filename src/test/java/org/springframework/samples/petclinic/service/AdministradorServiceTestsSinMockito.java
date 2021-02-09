package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdministradorServiceTestsSinMockito {

	@Autowired
	protected AdministradorService administradorService;
	
	
	@Test
	@Transactional
	public void shouldInsertAdministrador() {

		Administrador admin = new Administrador();
		admin.setNombre("Paco");
		admin.setApellidos("Florentino");
		admin.setTelefono(683020234);
		admin.setEmail("paquito@gmail.com");
		admin.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		admin.setUser(usuario);                
                
		this.administradorService.saveAdministrador(admin);
		Administrador adminEncontrado = this.administradorService
				.findAdministradorById(admin.getId());
		assertThat(admin).isEqualTo(adminEncontrado);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertAdministradorNullNombre() {

		Administrador admin = new Administrador();
		admin.setApellidos("Florentino");
		admin.setTelefono(683020234);
		admin.setEmail("paquito@gmail.com");
		admin.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		admin.setUser(usuario);  
		admin.setId(1000);
                
		this.administradorService.saveAdministrador(admin);
		Administrador adminEncontrado = this.administradorService
				.findAdministradorById(1000);
		assertNull(adminEncontrado);
	}
	
	@Test
	@Transactional
	void shouldUpdateAdministrador() {
		Administrador admin = this.administradorService.findAdministradorById(1);
		String oldNombre = admin.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		admin.setNombre(newNombre);
		this.administradorService.saveAdministrador(admin);
		
		admin = this.administradorService.findAdministradorById(1);
		assertThat(admin.getNombre()).isEqualTo(newNombre);
		
	}
	

	
	@Test
	@Transactional
	void shouldDeleteAdministrador() {
		Administrador admin = new Administrador();
		admin.setApellidos("Apellido1 Apellido2");
		admin.setEmail("correo@alum.us.es");
		admin.setNombre("Nombre1");
		admin.setTelefono(123456789);
		admin.setId(1000);
		User user = new User();
		user.setUsername("jex1234");
		user.setPassword("jex1234");
		admin.setUser(user);
		this.administradorService.saveAdministrador(admin);
		
		this.administradorService.saveAdministrador(admin);
		
		Administrador adminEncontrado = this.administradorService.findAdministradorById(1000);
		
		assertNull(adminEncontrado);
	}
}
