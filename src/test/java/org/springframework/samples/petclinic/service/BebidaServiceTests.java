package org.springframework.samples.petclinic.service;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.repository.BebidaRepository;



@ExtendWith(MockitoExtension.class)
public class BebidaServiceTests {

	
	@Mock
	BebidaRepository bebidaRepository;
	
	BebidaService bebidaService;
	
	@BeforeEach
	void setUp() {
		bebidaService = new BebidaService(bebidaRepository);
	}
	
	
	@Test
	void shouldFindAll() {
		when(bebidaRepository.findAll()).thenReturn(new ArrayList<>());
		bebidaService.findBebidas();
		verify(bebidaRepository).findAll();
	}
	
	@Test
	void shouldFindBebidaById() {
		
		Bebida bebida = new Bebida();
		bebida.setContador(0);
		bebida.setCoste(8);
		bebida.setEsCarbonatada(true);
		bebida.setId(99);
		bebida.setNombre("Coca cola");
		TamanoProducto tamano = new TamanoProducto();
		tamano.setName("Grande");
		bebida.setTamano(tamano);
		
		when(bebidaRepository.findBebidaById(anyInt())).thenReturn(bebida);
		bebidaService.findById(7);
		verify(bebidaRepository).findBebidaById(7);
	}
	
	@Test
	void shouldNotFindBebidaById() {

//		when(bebidaRepository.findBebidaById(anyInt())).thenReturn(bebida);
//		bebidaService.findById(7);
		verify(bebidaRepository, never()).findBebidaById(99);
	}
	
	@Test
	void shouldFindIdBebidaById() {
		
		when(bebidaRepository.findIdBebidaByCartaId(anyInt())).thenReturn(new ArrayList<>());
		bebidaService.findIdBebidaByCartaId(12);
		verify(bebidaRepository).findIdBebidaByCartaId(12);
	}
	
	@Test
	void shouldNotFindIdBebidaById() {
		
//		when(bebidaRepository.findIdBebidaByCartaId(anyInt())).thenReturn(new ArrayList<>());
//		bebidaService.findIdBebidaByCartaId(12);
		verify(bebidaRepository, never()).findIdBebidaByCartaId(100);
	}
	
	@Test
	void shouldFindPizzaPedidoById() {
		when(bebidaRepository.findBebidaPedidoById(anyInt())).thenReturn(new ArrayList<>());
		bebidaService.findBebidaPedidoById(10);
		verify(bebidaRepository).findBebidaPedidoById(10);
	}
	
	@Test
	void shouldNotFindPizzaPedidoById() {
		
		verify(bebidaRepository, never()).findBebidaPedidoById(10);
	}
	
}
