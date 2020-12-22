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
import org.springframework.samples.petclinic.model.Repartidor;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.RepartidorRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class RepartidorServiceTests {

	@Mock
	RepartidorRepository repartidorRepository;
	
	RepartidorService repartidorService;
	
	@BeforeEach
	void setUp() {
		repartidorService = new RepartidorService(repartidorRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindRepartidorById() {

		Repartidor repartidor = new Repartidor();
		repartidor.setNombre("Paco");
		repartidor.setApellidos("Florentino");
		repartidor.setTelefono(683020234);
		repartidor.setEmail("paquito@gmail.com");
		repartidor.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		repartidor.setUser(usuario); 
		
		when(repartidorRepository.findRepartidorById(anyInt())).thenReturn(repartidor);
		repartidorService.findRepartidorById(8);
		verify(repartidorRepository).findRepartidorById(8);
	}
	
	@Test
	@Transactional
	public void shouldNotFindRepartidorById() {

		Repartidor repartidor = new Repartidor();
		repartidor.setNombre("Paco");
		repartidor.setApellidos("Florentino");
		repartidor.setTelefono(683020234);
		repartidor.setEmail("paquito@gmail.com");
		repartidor.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
		repartidor.setUser(usuario); 
		
		when(repartidorRepository.findRepartidorById(anyInt())).thenReturn(repartidor);
		repartidorService.findRepartidorById(8);
		verify(repartidorRepository, never()).findRepartidorById(10);
	}
	
	@Test
	@Transactional
	public void shouldFindAllRepartidores() {
		
		when(repartidorRepository.findAll()).thenReturn(new ArrayList<>());
		repartidorService.findRepartidores();
		verify(repartidorRepository).findAll();
	}
	
}
