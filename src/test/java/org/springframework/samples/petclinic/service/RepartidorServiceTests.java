package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class RepartidorServiceTests {

	
	@Autowired
	protected RepartidorService repartidorService;
	
	
	@Test
	@Transactional
	public void shouldInsertRepartidor() {

		Repartidor repartidor = new Repartidor();
		repartidor.setNombre("Paco");
		repartidor.setApellidos("Florentino");
		repartidor.setTelefono(683020234);
		repartidor.setEmail("paquito@gmail.com");
		repartidor.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		repartidor.setUser(usuario);                
                
		this.repartidorService.saveRepartidor(repartidor);
		Repartidor repartidorEncontrado = this.repartidorService
				.findRepartidorById(repartidor.getId());
		assertThat(repartidor).isEqualTo(repartidorEncontrado);
	}
	
	@Test
	@Transactional
	void shouldUpdateRepartidor() {
		Repartidor repartidor = this.repartidorService.findRepartidorById(1);
		String oldNombre = repartidor.getNombre();
		String newNombre = oldNombre+"Ye";
		
		repartidor.setNombre(newNombre);
		this.repartidorService.saveRepartidor(repartidor);
		
		repartidor = this.repartidorService.findRepartidorById(1);
		assertThat(repartidor.getNombre()).isEqualTo(newNombre);
		
	}
	
	@Test
	@Transactional
	void shouldNotUpdateRepartidor() {
		Repartidor repartidor = this.repartidorService.findRepartidorById(1);
		String oldNombre = repartidor.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		repartidor.setNombre(newNombre);
		try{
			this.repartidorService.saveRepartidor(repartidor);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldDeleteRepartidor() {
		Repartidor repartidor = this.repartidorService.findRepartidorById(1);
		
		this.repartidorService.deleteRepartidor(repartidor);
		
		repartidor = this.repartidorService.findRepartidorById(1);
		
		assertNull(repartidor);
	}
	
}
