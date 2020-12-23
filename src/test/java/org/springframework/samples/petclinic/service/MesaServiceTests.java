package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.repository.MesaRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class MesaServiceTests {
	
	
	MesaService mesaService;
	@Mock
	MesaRepository mesaRepository;	

	
	@BeforeEach	
	void setUp() {
		mesaService = new MesaService(mesaRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindById() {

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6);
                
		when(mesaRepository.findById(anyInt())).thenReturn(mesa);
		mesaService.findById(7);
		verify(mesaRepository).findById(7);
	}
	
	@Test
	@Transactional
	public void shouldNotFindById() {

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6); 
                
//		when(mesaRepository.findById(anyInt())).thenReturn(mesa);
//		mesaService.findById(9);
		verify(mesaRepository, never()).findById(70);
	}
	

	@Test
	@Transactional
	public void shouldFindMesas() {
		
		when(mesaRepository.findAll()).thenReturn(new ArrayList<>());
		mesaService.findMesas();
		verify(mesaRepository).findAll();
	}
}
