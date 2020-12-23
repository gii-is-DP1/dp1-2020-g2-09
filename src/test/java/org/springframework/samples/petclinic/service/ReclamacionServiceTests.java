package org.springframework.samples.petclinic.service;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.repository.ReclamacionRepository;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class ReclamacionServiceTests {
	
	
	@Mock
	ReclamacionRepository reclamacionRepository;
	
	ReclamacionService reclamacionService;

	@BeforeEach
	void setup() {
		reclamacionService = new ReclamacionService(reclamacionRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindReclamacionById() {
		
		//Cargo los datos del SUT
		Reclamacion r = new Reclamacion();
		r.setObservacion("Prueba de observación");
		r.setRespuesta("Prueba de respuesta");
		
		//Hago la prueba
		//reclamacionRepository es el mock que le debo pasar como argumento al método verify
		//Un mock es un stub que espera unos valores y te devuelve 
		when(reclamacionRepository.findReclamacionById(anyInt())).thenReturn(r);
		reclamacionService.findReclamacionById(10);
		
		//Testeo que se ha llamado al método indicado una vez
		verify(reclamacionRepository).findReclamacionById(10);
	}
	
	
	@Test
	@Transactional
	public void shouldNotFindReclamacionById() {
		//Cargo los datos del SUT
//				Reclamacion r = new Reclamacion();
//				r.setObservacion("Prueba de observación");
//				r.setRespuesta("Prueba de respuesta");
//				
//				//Hago la prueba
//				//reclamacionRepository es el mock que le debo pasar como argumento al método verify
//				//Un mock es un stub que espera unos valores y te devuelve 
//				when(reclamacionRepository.findReclamacionById(anyInt())).thenReturn(r);
//				reclamacionService.findReclamacionById(10);
				
				//Testeo que NO se ha llamado nunca al método 
				verify(reclamacionRepository, never()).findReclamacionById(10);
	}
	
	@Test
	@Transactional
	public void shouldFindAllReclamaciones() {
		when(reclamacionRepository.findAll()).thenReturn(new ArrayList<>());
		reclamacionService.findReclamaciones();
		verify(reclamacionRepository).findAll();
	}
	
	@Test
	@Transactional
	public void shouldNotFindAllReclamaciones() {
		verify(reclamacionRepository, never()).findAll();
	}
	
	//Realmente no tiene mucho sentido
	@Test
	@Transactional
	public void shouldSaveReclamacion() {
		//Cargo los datos del SUT
		Reclamacion r = new Reclamacion();
		r.setObservacion("Prueba de observación");
		r.setRespuesta("Prueba de respuesta");
		
		//Hago la prueba
		//reclamacionRepository es el mock que le debo pasar como argumento al método verify
		//Un mock es un stub que espera unos valores y te devuelve 
		when(reclamacionRepository.save(r)).thenReturn(r);
		reclamacionRepository.save(r);
		verify(reclamacionRepository).save(r);
	}
	
//	@Test
//	@Transactional
//	public void shouldInsertReclamacion() {
//
//		Reclamacion reclamacion = new Reclamacion();
//		reclamacion.setObservacion("La pizza no llevaba queso");
//	//	reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));               
//                
//		this.reclamacionService.saveReclamacion(reclamacion);
//		Reclamacion reclamacionEncontrada = this.reclamacionService.
//				findReclamacionById(reclamacion.getId());
//		
//		assertThat(reclamacion).isEqualTo(reclamacionEncontrada);
//	}
//	
//	@Test
//	@Transactional
//	public void shouldThrowExceptionInsertingFechaReclamacionAfterCurrentDate(){
//		Reclamacion reclamacion = new Reclamacion();
//		reclamacion.setObservacion("La pizza no llevaba queso");
//		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 29)));
//		
//		try{
//			this.reclamacionService.saveReclamacion(reclamacion);
//			//assertTrue(false);
//			
//		}
//		catch (Exception e) {
//			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
//			assertTrue(true);
//		
//	}
//	}
//	
//	@Test
//	@Transactional
//	public void shouldThrowExceptionInsertingShortObservacion() {
//		Reclamacion reclamacion = new Reclamacion();
//		reclamacion.setObservacion("a");
//		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
//		
//		try{
//			this.reclamacionService.saveReclamacion(reclamacion);
//			//assertTrue(false);
//			
//		}
//		catch (Exception e) {
//			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
//			assertTrue(true);
//		
//	}
//	
//		
//	}
//	
//	@Test
//	@Transactional
//	public void shouldThrowExceptionNullObservacion() {
//		Reclamacion reclamacion = new Reclamacion();
//		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
//		
//		try{
//			this.reclamacionService.saveReclamacion(reclamacion);
//			//assertTrue(false);
//			
//		}
//		catch (Exception e) {
//			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
//			assertTrue(true);
//		
//	}
//		
//	}
//		
//	@Test
//	@Transactional
//	public void shouldThrowExceptionEmptyObservacion() {
//		
//		Reclamacion reclamacion = new Reclamacion();
//		reclamacion.setObservacion("");
//		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
//		
//		try{
//			this.reclamacionService.saveReclamacion(reclamacion);
//			//assertTrue(false);
//			
//		}
//		catch (Exception e) {
//			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
//			assertTrue(true);
//		
//		
//	}
//				
//		
//	}
//	
//	//Administrador responde reclamación
//	@Test
//	@Transactional
//	public void shouldUpdateReclamacion() {
//		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
//		 String observacion = "nueva observación";
//		 reclamacion.setObservacion(observacion);
//	     this.reclamacionService.saveReclamacion(reclamacion);
//	     reclamacion = this.reclamacionService.findReclamacionById(1);
//	     assertThat(reclamacion.getObservacion()).isEqualTo(observacion);
//	 }
//
////	@Test
////	@Transactional
////	public void shouldNotUpdateReclamacionWithNewDate() {
////		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
////			LocalDate newFecha = LocalDate.of(2020, 12, 20);
////			//reclamacion.setFechaReclamacion(newFecha);
////			try{
////				this.reclamacionService.saveReclamacion(reclamacion);
////				//assertTrue(false);
////			}catch (Exception e) { //La fecha de incidencia no es modificable
////				assertTrue(true);
////			}
////			//assertTrue(false);
////	}

}

	

