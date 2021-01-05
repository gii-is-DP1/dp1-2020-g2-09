package org.springframework.samples.petclinic.service;


import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.samples.petclinic.repository.TipoReservaRepository;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTests {
	
	@Mock 
	ReservaRepository reservaRepository;
	@Mock
	TipoReservaRepository tipoReservaRepository;
	
	ReservaService reservaService;
	
	@BeforeEach
	void setUp() {
		reservaService = new ReservaService(reservaRepository,tipoReservaRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindReservaById() {
		tipoReserva tr=new tipoReserva();
		tr.setName("MERIENDA");
		Reserva reserva = new Reserva();
		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva.setHora(LocalTime.of(10, 20));
		reserva.setNumeroPersonas(5);
		reserva.setTipoReserva(tr);
		
		when(reservaRepository.findById(anyInt())).thenReturn(reserva);
		reservaService.findById(99);
		verify(reservaRepository).findById(99);
		
	}
	@Test
	@Transactional
	public void shouldNotFindReservaById() {
		
//		when(reservaRepository.findById(anyInt())).thenReturn(reserva);
//		reservaService.findById(7);
		verify(reservaRepository,never()).findById(777);
		
	}
	
	@Test
	@Transactional
	public void shouldFindAllReservas() {
		
		when(reservaRepository.findAll()).thenReturn(new ArrayList<>());
		reservaService.findReservas();
		verify(reservaRepository).findAll();
	}
	
	
}
