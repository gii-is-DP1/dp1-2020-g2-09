package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CocineroServiceTestsSinMockito {

	
	@Autowired
	protected CocineroService cocineroService;
	
	
	@Test
	@Transactional
	public void shouldInsertCocinero() {

		Cocina cocinero = new Cocina();
		cocinero.setNombre("Paco");
		cocinero.setApellidos("Florentino");
		cocinero.setTelefono(683020234);
		cocinero.setEmail("paquito@gmail.com");
		cocinero.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		cocinero.setUser(usuario);                
                
		this.cocineroService.saveCocinero(cocinero);
		Cocina cocineroEncontrado = this.cocineroService.findCocineroById(cocinero.getId());
		assertThat(cocinero).isEqualTo(cocineroEncontrado);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertCocineroWithoutNombre() {

		Cocina cocinero = new Cocina();
		//cocinero.setNombre("Paco");
		cocinero.setApellidos("Florentino");
		cocinero.setTelefono(683020234);
		cocinero.setEmail("paquito@gmail.com");
		cocinero.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		cocinero.setId(1000);
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		cocinero.setUser(usuario);                      
		this.cocineroService.saveCocinero(cocinero);
		Cocina cocineroEncontrado = this.cocineroService.findCocineroById(1000);
		assertNull(cocineroEncontrado);
	}
	
	
	
	@Test
	@Transactional
	void shouldUpdateCocinero() {
		Cocina cocinero = this.cocineroService.findCocineroById(1);
		String oldNombre = cocinero.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		cocinero.setNombre(newNombre);
		this.cocineroService.saveCocinero(cocinero);
		
		cocinero = this.cocineroService.findCocineroById(1);
		assertThat(cocinero.getNombre()).isEqualTo(newNombre);
		
	}
	
//	@Test
//	@Transactional
//	void shouldNotUpdateCocineroTelefonoIncorrect() {
//		Cocina oldCocinero = this.cocineroService.findCocineroById(1);
//		Integer telefono = 1;
//		oldCocinero.setTelefono(telefono);
//		this.cocineroService.saveCocinero(oldCocinero);
//		Cocina cocineroEncontrado = this.cocineroService.findCocineroById(oldCocinero.getId());
//		assertFalse(cocineroEncontrado.getTelefono()==(telefono));
//		
//	}
	
	@Test
	@Transactional
	void shouldDeleteCocinero() {
		Cocina cocinero = new Cocina();
		cocinero.setApellidos("Apellido1 Apellido2");
		cocinero.setEmail("correo@alum.us.es");
		cocinero.setNombre("Nombre1");
		cocinero.setTelefono(123456789);
		cocinero.setId(1000);
		User user = new User();
		user.setUsername("jex1234");
		user.setPassword("jex1234");
		cocinero.setUser(user);
		this.cocineroService.saveCocinero(cocinero);
		
		this.cocineroService.deleteCocinero(cocinero);
		
		Cocina cocineroEncontrado = this.cocineroService.findCocineroById(1000);
		
		assertNull(cocineroEncontrado);
	}
	
	
	
}
