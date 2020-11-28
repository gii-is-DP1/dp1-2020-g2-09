package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class BebidaServiceTests {

	@Autowired
	protected BebidaService bebidaService;
	
	@Test
	void shouldFindBebidaById() {
		
		Bebida bebidaEncontrada = this.bebidaService.findById(1);

		assertThat(bebidaEncontrada).isNotNull();
	}
	
	@Test
	void shouldNotFindBebidaById() {

		Bebida bebidaEncontrada = this.bebidaService.findById(99);

		assertThat(bebidaEncontrada).isNull();
	}
	
	@Test
	void shouldFindIdBebidaById() {
		
		List<Integer> lista = this.bebidaService.findIdBebidaById(1);
		
		assertThat(lista).isNotNull();
	}
	
	@Test
	void shouldNotFindIdBebidaById() {
		
		List<Integer> lista = this.bebidaService.findIdBebidaById(99);
		
		assertThat(lista).isEmpty();
	}
	
	@Test
	@Transactional
	public void shouldInsertBebida() {

		Bebida bebida = new Bebida();
		bebida.setNombre("Pepsi");
		bebida.setEsCarbonatada(true);
		bebida.setContador(1);
		bebida.setCoste(12);
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		bebida.setTamano(tamanoProducto);
		
		this.bebidaService.saveBebida(bebida);
        Bebida bebidaEncontrada = this.bebidaService.findById(bebida.getId());
        assertThat(bebida).isEqualTo(bebidaEncontrada);
	}
	
	@Test
	@Transactional
	void shouldUpdateBebida() {
		Bebida bebida = this.bebidaService.findById(1);
		String oldNombre = bebida.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		bebida.setNombre(newNombre);
		this.bebidaService.saveBebida(bebida);

		bebida = this.bebidaService.findById(1);
		assertThat(bebida.getNombre()).isEqualTo(newNombre);
	}
	
	@Test
	@Transactional
	void shouldDeleteBebida() {
		Bebida bebida = this.bebidaService.findById(3);
		
		this.bebidaService.deleteBebida(bebida);
		
		bebida = this.bebidaService.findById(3);
		
		assertNull(bebida);
	}
	
}
