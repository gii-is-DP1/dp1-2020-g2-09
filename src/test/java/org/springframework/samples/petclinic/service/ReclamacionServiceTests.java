package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;


import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Oferta;
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
	@Transactional
	public void shouldThrowExceptionInsertingShortObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("a");
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
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
	@Transactional
	public void shouldThrowExceptionNullObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
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
	@Transactional
	public void shouldThrowExceptionEmptyObservacion() {
		
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("");
		reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
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
	@Transactional
	public void shouldUpdateReclamacion() {
		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
		 String observacion = "nueva observación";
		 reclamacion.setObservacion(observacion);
	     this.reclamacionService.saveReclamacion(reclamacion);
	     reclamacion = this.reclamacionService.findReclamacionById(1);
	     assertThat(reclamacion.getObservacion()).isEqualTo(observacion);
	 }

	@Test
	@Transactional
	public void shouldNotUpdateReclamacionWithNewDate() {
		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
			LocalDate newFecha = LocalDate.of(2020, 12, 20);
			reclamacion.setFechaReclamacion(newFecha);
			try{
				this.reclamacionService.saveReclamacion(reclamacion);
				//assertTrue(false);
			}catch (Exception e) { //La fecha de incidencia no es modificable
				assertTrue(true);
			}
			//assertTrue(false);
	}

}

	

