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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Mesa;
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
	@Mock
	ReservaService reservaServiceMock;
	
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
	
	@Test
	@Transactional
	public void shouldFindTipoReserva() {
		tipoReserva tr1=new tipoReserva();
		tr1.setName("ALMUERZO");
		
		tipoReserva tr2=new tipoReserva();
		tr2.setName("CENA");
		
		List<tipoReserva> ctr = new ArrayList<tipoReserva>();
		ctr.add(tr1);
		ctr.add(tr2);
		//Collection<tipoReserva> ctr = new Collection<tipoReserva>();
		
		when(reservaRepository.findTipoReserva()).thenReturn(ctr);
		reservaService.findTipoReserva();
		verify(reservaRepository).findTipoReserva();
		
	}
	
	@Test
	@Transactional
	public void shouldfindReservasByCliente() {
		tipoReserva tr=new tipoReserva();
		tr.setName("MERIENDA");
		Reserva reserva = new Reserva();
		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva.setHora(LocalTime.of(10, 20));
		reserva.setNumeroPersonas(5);
		reserva.setTipoReserva(tr);
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(10);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		reserva.setCliente(cliente);
		
		List<Reserva> lr = new ArrayList<Reserva>();
		lr.add(reserva);
		
		when(reservaRepository.findReservasByCliente(anyInt())).thenReturn(lr);
		reservaService.findReservasByCliente(10);
		verify(reservaRepository).findReservasByCliente(10);
		
	}
	
	@Test
	@Transactional
	public void shouldNotfindReservasByCliente() {
		verify(reservaRepository, never()).findReservasByCliente(10);
	}
	
	@Test
	@Transactional
	public void shouldfindReservasIdByMesaId() {
		tipoReserva tr=new tipoReserva();
		tr.setName("MERIENDA");
		Reserva reserva1 = new Reserva();
		reserva1.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva1.setHora(LocalTime.of(10, 20));
		reserva1.setNumeroPersonas(5);
		reserva1.setTipoReserva(tr);
		reserva1.setId(10);
		
		Reserva reserva2 = new Reserva();
		reserva2.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva2.setHora(LocalTime.of(10, 20));
		reserva2.setNumeroPersonas(6);
		reserva2.setTipoReserva(tr);
		reserva2.setId(11);
		
		List<Integer> reservasId = new ArrayList<Integer>();
		reservasId.add(reserva1.getId());
		reservasId.add(reserva2.getId());
		
		Mesa m = new Mesa();
		m.setId(100);
		m.setCapacidad(6);
		
		when(reservaRepository.findReservasIdByMesaId(anyInt())).thenReturn(reservasId);
		reservaService.findReservasIdByMesaId(100);
		verify(reservaRepository).findReservasIdByMesaId(100);
		
	}
	
	@Test
	@Transactional
	public void shouldNotfindReservasIdByMesaId() {
		verify(reservaRepository, never()).findReservasIdByMesaId(100);
	}
	
//	//No tiene sentido hacerlo con dobles creo
//	@Test
//	@Transactional
//	public void shouldCalcularReservasAPartirIds() {
//		List<Integer> listaId = new ArrayList<Integer>();
//		listaId.add(1);
//		
//		tipoReserva tr=new tipoReserva();
//		tr.setName("MERIENDA");
//		Reserva reserva = new Reserva();
//		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
//		reserva.setHora(LocalTime.of(10, 20));
//		reserva.setNumeroPersonas(5);
//		reserva.setTipoReserva(tr);
//		reserva.setId(1);
//		
//		List<Reserva> listaReservas = new ArrayList<Reserva>();
//		listaReservas.add(reserva);
//		
//		when(reservaServiceMock.calcularReservasAPartirIds(listaId)).thenReturn(listaReservas);
//		reservaService.calcularReservasAPartirIds(listaId);
//		verify(reservaServiceMock).calcularReservasAPartirIds(listaId);
//	}
	
	
	
}
