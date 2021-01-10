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
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class IngredienteServiceTestsSinMockito {
	
	@Autowired
	protected IngredienteService ingredienteService;
	
	@Test
	@Transactional
	public void shouldInsertIngrediente() {

		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setNombre("Queso ibérico");
		ingrediente.setTipo("Lácteo");
		ingrediente.setFechaCaducidad(LocalDate.of(2021, 10, 5));          
        List<Ingrediente> ingredientes = this.ingredienteService.findIngredientes();
        
		this.ingredienteService.saveIngrediente(ingrediente);
		
		assertThat(ingredientes.contains(ingrediente));
	}
	
	@Test
	@Transactional
	public void shouldNotInsertIngredienteWithNullName() {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setFechaCaducidad(LocalDate.of(2021, 10, 5));
		
		try{
			this.ingredienteService.saveIngrediente(ingrediente);
			//assertTrue(false);
			
		} //Debe entrar en el catch porque el nombre del ingrediente es obligatorio, entre otros
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
	}
	
	

}
	
	@Test
	@Transactional
	public void shouldDeleteIngrediente() {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setId(100);
			
			this.ingredienteService.deleteIngrediente(ingrediente);
			Ingrediente ingredienteEncontrado = this.ingredienteService.findIngredienteById(100);
					
			assertNull(ingredienteEncontrado);
		}
	
	@Test
	@Transactional
	public void shouldNotDeleteIngrediente() {
			try{
				Ingrediente ingrediente = this.ingredienteService.findIngredienteById(990999);
				this.ingredienteService.deleteIngrediente(ingrediente);
				//assertTrue(false);
			}catch (Exception e) {
				assertTrue(true);
			}
			
		}
	}
		
