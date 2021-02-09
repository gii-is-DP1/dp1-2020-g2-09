package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MesaServiceTestsSinMockito {
	@Autowired
	protected MesaService mesaService;
	
	
	
	@Test
	@Transactional
	public void shouldInsertMesa() {

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6); 
                
		this.mesaService.saveMesa(mesa);
		Mesa mesaEncontrada = this.mesaService.findById(mesa.getId());
		assertThat(mesa).isEqualTo(mesaEncontrada);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {-5,0, 7, 10})
	@Transactional
	public void shouldNotInsertMesaIncorrectCapacidad(int argument) {
		Mesa mesa = new Mesa();
		mesa.setCapacidad(argument);
		
		try {
			this.mesaService.saveMesa(mesa);
			
		} catch(Exception e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	@Transactional
	void shouldUpdateMesa() {
		Mesa mesa = this.mesaService.findById(1);
		Integer oldCapacidad = mesa.getCapacidad();
		Integer newCapacidad = oldCapacidad+2;
		
		mesa.setCapacidad(newCapacidad);
		this.mesaService.saveMesa(mesa);

		mesa = this.mesaService.findById(1);
		assertThat(mesa.getCapacidad()).isEqualTo(newCapacidad);
	}
	
	
}

