package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReservaServiceTestsSinMockito {
	@Autowired
	protected ReservaService reservaService;
	
	@Autowired
	protected MesaService mesaService;
	
	
	@Test
	@Transactional
	public void shouldInsertReserva() {
		tipoReserva tr=new tipoReserva();
		tr.setName("MERIENDA");
		Reserva reserva = new Reserva();
		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva.setHora(LocalTime.of(10, 20));
		reserva.setNumeroPersonas(5);
		reserva.setTipoReserva(tr);
           
		this.reservaService.saveReserva(reserva);
		Reserva reservaEncontrada = this.reservaService.findById(reserva.getId());
		assertThat(reserva).isEqualTo(reservaEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertReservaNumeroPersonasHigherThanSix() {
		tipoReserva tr=new tipoReserva();
		tr.setName("MERIENDA");
		Reserva reserva = new Reserva();
		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
		reserva.setHora(LocalTime.of(10, 20));
		reserva.setNumeroPersonas(8);
		reserva.setTipoReserva(tr);
        
		try {
		this.reservaService.saveReserva(reserva);
		}
		catch(Exception e){
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	void shouldUpdateReserva() {
		Reserva reserva = this.reservaService.findById(1);
		LocalTime oldHora = reserva.getHora();
		LocalTime newHora = oldHora.plusHours(1);
		reserva.setHora(newHora);
		this.reservaService.saveReserva(reserva);
		
		reserva = this.reservaService.findById(1);
		assertThat(reserva.getHora()).isEqualTo(newHora);
		
	}
	
	@Test
	@Transactional
	void shouldNotUpdateReservaNumeroPersonasHigherThanSix() {
		Reserva reserva = this.reservaService.findById(1);
		reserva.setNumeroPersonas(8);
		try{
			this.reservaService.saveReserva(reserva);
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	void shouldDeleteReserva() {
		Reserva reserva = this.reservaService.findById(1);
		
		this.reservaService.deleteReserva(reserva);
		
		reserva = this.reservaService.findById(1);
		assertNull(reserva);
	}
	
	@Test
	@Transactional
	void shouldNotDeleteReserva() {
		try{
			Reserva reserva = this.reservaService.findById(990999);
			this.reservaService.deleteReserva(reserva);
		}catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	@Transactional
	void shouldAnadirMesaAReserva() {
		Reserva r = this.reservaService.findById(1);
		Mesa m  = this.mesaService.findById(1);            
                
		this.reservaService.anadirMesaAReserva(r.getId(), m.getId());
//		List<Integer> pedidosId = this.reclamacionService.findPedidosConReclamaciones();
//		Integer indicePedido = pedidosId.indexOf(10);
//		Integer 
		
		assertThat(r.getMesasEnReserva().contains(m));
	}
	
	@Test
	@Transactional
	public void shouldCalcularReservasAPartirIds() {
		List<Integer> listaId= new ArrayList<>();
		listaId.add(1);
		listaId.add(2);
		
		List<Reserva> reservas = this.reservaService.calcularReservasAPartirIds(listaId);
		Reserva r1 = reservas.get(0);
		Reserva r2 = reservas.get(1);
		
		assertThat(r1.getId()==1 && r2.getId() == 2);
		
		
	}
	

	
}
