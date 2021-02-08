package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.repository.IngredienteRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class IngredienteServiceTests {


	@Mock
	IngredienteRepository ingredienteRepository;
	
	IngredienteService ingredienteService;
	
	@BeforeEach
	void setUp() {
		ingredienteService = new IngredienteService(ingredienteRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindAllIngredientes() {
		
		when(ingredienteRepository.findAll()).thenReturn(new ArrayList<>());
		ingredienteService.findIngredientes();
		verify(ingredienteRepository).findAll();
	}
	
	@Test
	@Transactional
	public void shouldFindIngredienteById() {

		Ingrediente ing = new Ingrediente();
		ing.setFechaCaducidad(LocalDate.of(2020, 12, 31));
		ing.setNombre("Queso");
		ing.setTipo("jeje");
		Alergenos alergenos = new Alergenos();
		alergenos.setName("alergeno");
		ing.setAlergenos(alergenos);
		
		when(ingredienteRepository.findIngredienteById(anyInt())).thenReturn(ing);
		ingredienteService.findIngredienteById(3);
		verify(ingredienteRepository).findIngredienteById(3);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindClienteById() {
		
		verify(ingredienteRepository, never()).findIngredienteById(777);
		
	}
	
	@Test
	@Transactional
	public void shouldFindAlergenos() {
        
        when(ingredienteRepository.findAlergenos()).thenReturn(new ArrayList<>());
        ingredienteService.findAlergenos();
        verify(ingredienteRepository).findAlergenos();
		
	}
	
	@Test
	@Transactional
	public void shouldFindIngredienteIdByOtrosId() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);ids.add(4);
		
		when(ingredienteRepository.findIngredienteIdByOtrosId(anyInt())).thenReturn(ids);
		ingredienteService.findIngredienteIdByOtrosId(3);
		verify(ingredienteRepository).findIngredienteIdByOtrosId(3);
	}
	
	@Test
	@Transactional
	public void shouldNotFindIngredienteIdByOtrosId() {
		
		verify(ingredienteRepository, never()).findIngredienteIdByOtrosId(3);
	}
	
	@Test
	@Transactional
	public void shouldCountIngrediente() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(13);ids.add(20);
		
		when(ingredienteRepository.CountIngrediente(anyInt())).thenReturn(ids);
		ingredienteService.CountIngrediente(12);
		verify(ingredienteRepository).CountIngrediente(12);
	}
	
	@Test
	@Transactional
	public void shouldNotCountIngrediente() {
		
		verify(ingredienteRepository, never()).CountIngrediente(12);
	}
	
	@Test
	@Transactional
	public void shouldCalculateCaducidadIngrediente() {
		
		List<Date> fechas = new ArrayList<>();
		LocalDate fecha = LocalDate.now();
		Date h = Date.valueOf(fecha);
		fechas.add(h);
		when(ingredienteRepository.CaducidadIngrediente(anyInt())).thenReturn(fechas);
		ingredienteService.CaducidadIngrediente(0);
		verify(ingredienteRepository).CaducidadIngrediente(0);
		
	}
	
	@Test
	@Transactional
	public void shouldNotCalculateCaducidadIngrediente() {
		
		verify(ingredienteRepository, never()).CaducidadIngrediente(1);
		
	}
}
