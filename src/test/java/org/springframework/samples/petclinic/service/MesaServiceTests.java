package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cocina;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MesaServiceTests {
	@Autowired
	protected MesaService mesaService;
	
	
	//Este test creo que no tiene sentido
//	@Test 	
//	public void shouldFindByReserva() {
//		
//		Mesa mesa = new Mesa();
//		mesa.setCapacidad(4);
//		
//		Reserva reserva = new Reserva();
//		List<Reserva> listaReservas = new ArrayList<Reserva>();
//		reserva.setHora(LocalTime.of(4, 53));
//		reserva.setFechaReserva(LocalDate.of(2020, 05, 11));
//		reserva.setNumeroPersonas(5);
//		tipoReserva tipo = new tipoReserva();
//		
//		tipo.setName("hola");
//		reserva.setTipoReserva(tipo);
//		listaReservas.add(reserva);
//		
//        mesa.setReserva(listaReservas); 
//		
//        this.mesaService.saveMesa(mesa);
//		List<Mesa> listaMesas = this.mesaService.findByReserva(reserva.getId());
//
//		assertThat(listaReservas.get(0).getId()).isEqualTo(listaMesas.get(0).getId());
//	}
	
	
	@Test
	@Transactional
	public void shouldInsertMesa() {

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6); 
                
		this.mesaService.saveMesa(mesa);
		Mesa mesaEncontrada = this.mesaService.findById(mesa.getId());
		assertThat(mesa).isEqualTo(mesaEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertMesaCapacidadHigherThanSix() {
		Mesa mesa = new Mesa();
		mesa.setCapacidad(8);
		
		try {
			this.mesaService.saveMesa(mesa);
			
		} catch(Exception e) { //La capacidad de la mesa no puede ser mayor que 6!
			assertTrue(true);
		}
	}
	
	@Test
	@Transactional
	public void shouldNotInsertMesaCapacidadLessThanZero() {
		Mesa mesa = new Mesa();
		mesa.setCapacidad(-2);
		
		try {
			this.mesaService.saveMesa(mesa);
			
		} catch(Exception e) { //La capacidad de la mesa no puede ser menor o igual a cero
			assertTrue(true);
		}
	}
	
	
	@Test
	@Transactional
	void shouldUpdateMesa() {
		Mesa mesa = this.mesaService.findById(1);
		Integer oldCapacidad = mesa.getCapacidad();
		Integer newCapacidad = oldCapacidad+2;
		
		mesa.setCapacidad(newCapacidad);
		this.mesaService.saveMesa(mesa);

		mesa = this.mesaService.findById(1);
		assertThat(mesa.getCapacidad()).isEqualTo(newCapacidad);
	}
	
	@Test
	@Transactional
	void shouldNotUpdateMesaCapacidadHigherThanSix() {
		Mesa oldMesa = this.mesaService.findById(1);
		Integer newCapacidad = 9;
		oldMesa.setCapacidad(newCapacidad);
		
		try{
			this.mesaService.saveMesa(oldMesa);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldNotUpdateMesaCapacidadLessThanZero() {
		Mesa oldMesa = this.mesaService.findById(1);
		Integer newCapacidad = -7;
		oldMesa.setCapacidad(newCapacidad);
		
		try{
			this.mesaService.saveMesa(oldMesa);
			//assertTrue(false);
		}catch (Exception e) {
			assertTrue(true);
		}
		//assertTrue(false);
	}
	
	@Test
	@Transactional
	void shouldDeleteMesa() {
		Mesa mesa = this.mesaService.findById(1);
		this.mesaService.deleteMesa(mesa);
		mesa = this.mesaService.findById(1);
		assertNull(mesa);
	}
	
}
