package org.springframework.samples.petclinic.service;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.repository.OtrosRepository;

@ExtendWith(MockitoExtension.class)
public class OtrosServiceTests {

	@Mock
	OtrosRepository otrosRepository;
	
	OtrosService otrosService;
	
	@BeforeEach
	void setUp() {
		otrosService = new OtrosService(otrosRepository);
	}
	
	@Test
	void shouldFindAll() {
		
		when(otrosRepository.findAll()).thenReturn(new ArrayList<>());
		otrosService.findOtros();
		verify(otrosRepository).findAll();
	}
	
	@Test
	void shouldFindOtroById() {
		
		Otro otro = new Otro();
		otro.setContador(1);
		otro.setCoste(12);
		otro.setId(16);
		Collection<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		Ingrediente e = new Ingrediente();
		e.setNombre("Queso");
		ingredientes.add(e);
		otro.setIngredientes(ingredientes);
		otro.setNombre("Hamburguesa");
		
		when(otrosRepository.findOtrosById(anyInt())).thenReturn(otro);
		otrosService.findOtrosById(99);
		verify(otrosRepository).findOtrosById(99);
	}
	
	@Test
	void shouldNotFindOtroById() {
		
//		when(otrosRepository.findOtrosById(anyInt())).thenReturn(otro);
//		otrosService.findOtrosById(99);
		verify(otrosRepository, never()).findOtrosById(99);
	}
	
	@Test
	void shouldFindIdOtroById() {
		
		when(otrosRepository.findIdOtroById(anyInt())).thenReturn(new ArrayList<>());
		otrosService.findIdOtroById(7);
		verify(otrosRepository).findIdOtroById(7);
	}
	
	@Test
	void shouldNotFindIdOtroById() {
		
//		when(otrosRepository.findIdOtroById(anyInt())).thenReturn(new ArrayList<>());
//		otrosService.findIdOtroById(7);
		verify(otrosRepository, never()).findIdOtroById(7);
	}
	
	@Test
	void shouldFindOtroPedidoById() {
		when(otrosRepository.findOtrosPedidoById(anyInt())).thenReturn(new ArrayList<>());
		otrosService.findOtrosPedidoById(5);
		verify(otrosRepository).findOtrosPedidoById(5);
	}
	
	@Test
	void shouldNotFindOtroPedidoById() {
		
		verify(otrosRepository, never()).findOtrosPedidoById(10);
	}
	
	
	
}
