package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
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
	
	@Test
	@Transactional
	public void shouldFindByReserva() {
		Reserva r = new Reserva();
		tipoReserva tr = new tipoReserva();
		tr.setName("MERIENDA");
		tr.setId(3);
		r.setId(10);
		r.setFechaReserva(LocalDate.of(2020, 11, 24));
		r.setHora(LocalTime.of(20, 34));
		r.setNumeroPersonas(6);
		r.setTipoReserva(tr);

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6);
		List<Mesa> lm = new ArrayList<Mesa>();
		lm.add(mesa);
                
		when(mesaRepository.findByReserva(anyInt())).thenReturn(lm);
		mesaService.findByReserva(10);
		verify(mesaRepository).findByReserva(10);
	}
	
	@Test
	@Transactional
	public void shouldNotFindByReserva() {
		verify(mesaRepository, never()).findByReserva(10);
		
	}
	
	@Test
	@Transactional
	public void shouldfindIdMesaByReserva() {
		Reserva r = new Reserva();
		tipoReserva tr = new tipoReserva();
		tr.setName("MERIENDA");
		tr.setId(3);
		r.setId(10);
		r.setFechaReserva(LocalDate.of(2020, 11, 24));
		r.setHora(LocalTime.of(20, 34));
		r.setNumeroPersonas(6);
		r.setTipoReserva(tr);

		Mesa mesa = new Mesa();
		mesa.setId(10);
		mesa.setCapacidad(6);
		List<Mesa> lm = new ArrayList<Mesa>();
		lm.add(mesa);
                
		when(mesaRepository.findIdMesaByReserva(anyInt())).thenReturn(10);
		mesaService.findIdMesaByReserva(10);
		verify(mesaRepository).findIdMesaByReserva(10);
	}
	
	@Test
	@Transactional
	public void shouldNotFindIdMesaByReserva() {
		verify(mesaRepository, never()).findIdMesaByReserva(10);
		
	}
	
	@Test
	@Transactional
	public void shouldCountMesa() {
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		when(mesaRepository.CountMesa(anyInt())).thenReturn(l);
		mesaService.CountMesa(1);
		verify(mesaRepository).CountMesa(1);
		
	}
	
}
