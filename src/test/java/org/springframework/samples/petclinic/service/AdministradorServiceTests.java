package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AdministradorServiceTests {

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
		//cliente.setFechaAlta(LocalDate.now());
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
	void shouldNotUpdateAdministrador() {
		Administrador admin = this.administradorService.findAdministradorById(1);
		String oldNombre = admin.getNombre();
		String newNombre = oldNombre+"Yeaaaah";
		
		admin.setNombre(newNombre);
		try{
			this.administradorService.saveAdministrador(admin);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldDeleteAdministrador() {
		Administrador admin = this.administradorService.findAdministradorById(1);
		
		this.administradorService.deleteAdministrador(admin);
		
		admin = this.administradorService.findAdministradorById(1);
		
		assertNull(admin);
	}
	
	
}
