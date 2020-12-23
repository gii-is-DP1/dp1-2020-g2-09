package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.CocineroRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class CocineroServiceTests {

	@Mock
	CocineroRepository cocineroRepository;
	
	CocineroService cocineroService;
	
	@BeforeEach
	void setUp() {
		cocineroService = new CocineroService(cocineroRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindCocineroById() {

		Cocina cocinero = new Cocina();
		cocinero.setNombre("Paco");
		cocinero.setApellidos("Florentino");
		cocinero.setTelefono(683020234);
		cocinero.setEmail("paquito@gmail.com");
		cocinero.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		cocinero.setUser(usuario); 
		
		when(cocineroRepository.findCocineroById(anyInt())).thenReturn(cocinero);
		cocineroService.findCocineroById(7);
		verify(cocineroRepository).findCocineroById(7);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindCocineroById() {

//		when(cocineroRepository.findCocineroById(anyInt())).thenReturn(cocinero);
//		cocineroService.findCocineroById(7);
		verify(cocineroRepository, never()).findCocineroById(50);
		
	}
	
	@Test
	@Transactional
	public void shouldFindAllCocineros() {
		
		when(cocineroRepository.findAll()).thenReturn(new ArrayList<>());
		cocineroService.findCocineros();
		verify(cocineroRepository).findAll();
	}
	
	
}
