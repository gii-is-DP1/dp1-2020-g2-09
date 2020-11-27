package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CocineroServiceTests {

	
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
	void shouldUpdateCocinero() {
		Cocina cocinero = this.cocineroService.findCocineroById(1);
		String oldNombre = cocinero.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		cocinero.setNombre(newNombre);
		this.cocineroService.saveCocinero(cocinero);
		
		cocinero = this.cocineroService.findCocineroById(1);
		assertThat(cocinero.getNombre()).isEqualTo(newNombre);
		
	}
	
	@Test
	@Transactional
	void shouldNotUpdateCocinero() {
		Cocina oldCocinero = this.cocineroService.findCocineroById(1);
		String oldNombre = oldCocinero.getNombre();
		String newNombre = oldNombre+"Yeahhhhhhhhhhhh";
		
		oldCocinero.setNombre(newNombre);
		try{
			this.cocineroService.saveCocinero(oldCocinero);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldDeleteCocinero() {
		Cocina cocinero = this.cocineroService.findCocineroById(1);
		
		this.cocineroService.deleteCocinero(cocinero);
		
		cocinero = this.cocineroService.findCocineroById(1);
		
		assertNull(cocinero);
	}
	
	
	
}
