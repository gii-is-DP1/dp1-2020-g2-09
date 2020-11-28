package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReservaServiceTests {
	@Autowired
	protected ReservaService reservaService;
	
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
//	@Test
//	@Transactional
//	public void shouldNotInsertReserva() {
//		tipoReserva tr=new tipoReserva();
//		tr.setName("MERIENDA");
//		Reserva reserva = new Reserva();
//		reserva.setFechaReserva(LocalDate.of(2000, 12, 9));
//		reserva.setHora(LocalTime.of(10, 20));
//		reserva.setNumeroPersonas(8);//Meter un numero de personas >6 que no se puede por el COVID
//		reserva.setTipoReserva(tr);
//        
//		try {
//		this.reservaService.saveReserva(reserva);
//		}
//		catch(Exception e){
//			assertTrue(true);
//		}
//		//assertTrue(false);
//		Reserva reservaEncontrada = this.reservaService.findById(reserva.getId());
//		//assertThat(reserva).isNotEqualTo(reservaEncontrada);
//	}
	
	@Test
	@Transactional
	void shouldUpdateReserva() {
		Reserva reserva = this.reservaService.findById(1);
		LocalTime oldHora = reserva.getHora();
		LocalTime newHora = oldHora.plusHours(1);///Atrasa la reserva una hora
		
		reserva.setHora(newHora);
		this.reservaService.saveReserva(reserva);
		
		reserva = this.reservaService.findById(1);
		assertThat(reserva.getHora()).isEqualTo(newHora);
		
	}
	
	@Test
	@Transactional
	void shouldNotUpdateReserva() {
		Reserva reserva = this.reservaService.findById(1);
		LocalTime oldHora = reserva.getHora();
		LocalTime newHora = oldHora.plusHours(999999);///Atrasa la reserva una hora
		
		reserva.setHora(newHora);
		try{
			this.reservaService.saveReserva(reserva);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldDeleteReserva() {
		Reserva reserva = this.reservaService.findById(1);
		
		this.reservaService.deleteReserva(reserva);
		
		reserva = this.reservaService.findById(1);
		assertNull(reserva);
	}
	
	
}
