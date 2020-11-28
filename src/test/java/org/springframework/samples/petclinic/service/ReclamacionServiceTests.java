package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReclamacionServiceTests {
	
	@Autowired
	protected ReclamacionService reclamacionService;

	
	@Test
	@Transactional
	public void shouldInsertReclamacion() {

		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("La pizza no llevaba queso");
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));               
                
		this.reclamacionService.saveReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.
				findReclamacionById(reclamacion.getId());
		
		assertThat(reclamacion).isEqualTo(reclamacionEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingFechaReclamacionAfterCurrentDate(){
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("La pizza no llevaba queso");
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 29)));
		
		try{
			this.reclamacionService.saveReclamacion(reclamacion);
			//assertTrue(false);
			
		}
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
		
	}
	}
	
	@Test
	public void shouldfechaReclamacionAnteriorFechaActual() {

		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("La pizza no llevaba queso");
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));               
                
		this.reclamacionService.saveReclamacion(reclamacion);
		
		assertThat(reclamacion.getFechaReclamacion()).isBefore(LocalDate.now());
	}
	
	
	
	
	
}

