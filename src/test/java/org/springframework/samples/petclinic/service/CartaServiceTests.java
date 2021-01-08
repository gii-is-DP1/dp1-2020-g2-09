package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.repository.CartaRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class CartaServiceTests {

	@Mock
	CartaRepository cartaRepository;
	
	CartaService cartaService;
	
	@BeforeEach
	void setUp() {
		cartaService = new CartaService(cartaRepository);
	}
	
	@Test
	@Transactional
	public void insertarCarta() {
		
		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2020, 4, 10));
		
		when(cartaRepository.save(carta)).thenReturn(carta);
		cartaService.saveCarta(carta);
		verify(cartaRepository).save(carta);
	}
	
	@Test
	@Transactional
	public void shouldFindCartaById() {

		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2020, 4, 10));
		
		when(cartaRepository.findById(anyInt())).thenReturn(carta);
		cartaService.findCartaById(77);
		verify(cartaRepository).findById(77);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindCartaById() {

		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2020, 4, 10));
		
		when(cartaRepository.findById(anyInt())).thenReturn(carta);
		cartaService.findCartaById(77);
		verify(cartaRepository, never()).findById(88);
		
	}
	
	@Test
	@Transactional
	public void shouldFindCartaByFechasDeCreacionYFinal() {
	
		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2021, 4, 10));
		LocalDate hoy = LocalDate.now();
		
		when(cartaRepository.findCartaByFechaCreacionYFechaFinal(hoy))
		.thenReturn(carta);
		cartaService.findCartaByFechaCreacionYFechaFinal(hoy);
		verify(cartaRepository).findCartaByFechaCreacionYFechaFinal(hoy);
	}
	
	@Test
	@Transactional
	public void shouldNotFindCartaByFechasDeCreacionYFinal() {
	
		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2021, 4, 10));
		LocalDate hoy = LocalDate.now();
		when(cartaRepository.findCartaByFechaCreacionYFechaFinal(hoy))
		.thenReturn(carta);
		cartaService.findCartaByFechaCreacionYFechaFinal(hoy);
		verify(cartaRepository, never()).findCartaByFechaCreacionYFechaFinal(LocalDate.of(2019, 3, 26));
	}
	
	@Test
	@Transactional
	public void findAllCartas() {
		
		when(cartaRepository.findAll()).thenReturn(new ArrayList<>());
		cartaService.findCartas();
		verify(cartaRepository).findAll();
	}
	
}
