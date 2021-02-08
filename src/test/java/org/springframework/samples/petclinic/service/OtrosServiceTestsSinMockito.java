package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OtrosServiceTestsSinMockito {
	
	@Autowired 
	OtrosService otrosService;
	
	@Autowired
	CartaService cartaService;
	
	@Test
	@Transactional
	public void shouldInsertOtros() {

		Otro otro = new Otro();
		otro.setCoste(10.0);
		otro.setNombre("Pollo con queso");
		
        
		this.otrosService.saveOtros(otro);
		
		Otro otroEncontrado = this.otrosService.findOtrosById(otro.getId());
		
		assertThat(otro).isEqualTo(otroEncontrado);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertOtrosNullNombre() {
		Otro otro = new Otro();
		otro.setCoste(10.0);
		otro.setId(1000);
		this.otrosService.saveOtros(otro);
		
		Otro otroEncontrado = this.otrosService.findOtrosById(1000);
		
		assertNull(otroEncontrado);
	}
	
	
	@Test
	@Transactional
	void shouldAnadirOtroACarta() {
		Otro otro = this.otrosService.findOtrosById(1);
		Carta carta = this.cartaService.findCartaById(1);
		
		this.otrosService.añadirOtroACarta(otro.getId(), carta.getId());
		
		assertThat(carta.getOtrosEnCarta().contains(otro));
		
	}
	
	@Test
	@Transactional 
	void shouldDeleteOtrosFromComposicionCarta() {
		Otro otro = this.otrosService.findOtrosById(1);
		Carta carta = this.cartaService.findCartaById(1);
		
		this.otrosService.deleteOtroFromComposicionCarta(otro.getId());
		
		assertThat(!carta.getOtrosEnCarta().contains(otro));
	}
	
	

}
