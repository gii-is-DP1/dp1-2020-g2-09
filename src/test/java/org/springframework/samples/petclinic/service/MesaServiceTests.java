package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MesaServiceTests {
	@Autowired
	protected MesaService mesaService;
	
	
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
//		tipo.setName("hola");
//		reserva.setTipoReserva(tipo);
//		listaReservas.add(reserva);
//        mesa.setReserva(listaReservas); 
//		
//        this.mesaService.saveMesa(mesa);
//		List<Mesa> listaMesas = this.mesaService.findByReserva(reserva.getId());
//
//		assertThat(listaReservas).isEqualTo(listaMesas);
//	}
	
	
	@Test
	@Transactional
	public void shouldInsertCliente() {

		Mesa mesa = new Mesa();
		mesa.setCapacidad(6); 
                
		this.mesaService.saveMesa(mesa);
		Mesa mesaEncontrada = this.mesaService.findById(mesa.getId());
		assertThat(mesa).isEqualTo(mesaEncontrada);
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
	void shouldDeleteMesa() {
		Mesa mesa = this.mesaService.findById(1);
		this.mesaService.deleteMesa(mesa);
		mesa = this.mesaService.findById(1);
		assertNull(mesa);
	}
	
}
