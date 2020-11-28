package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PizzaServiceTests {

	@Autowired
	protected PizzaService pizzaService;
	
	@Test
	void shouldFindPizzaById() {
		
		Pizza pizzaEncontrada = this.pizzaService.findPizzaById(1);

		assertThat(pizzaEncontrada).isNotNull();
	}
	
	@Test
	void shouldNotFindPizzaById() {

		Pizza pizzaEncontrada = this.pizzaService.findPizzaById(99);

		assertThat(pizzaEncontrada).isNull();
	}
	
	@Test
	void shouldFindIdPizzaById() {
		
		List<Integer> lista = this.pizzaService.findIdPizzaById(1);
		
		assertThat(lista).isNotNull();
	}
	
	@Test
	void shouldNotFindIdPizzaById() {
		
		List<Integer> lista = this.pizzaService.findIdPizzaById(99);
		
		assertThat(lista).isEmpty();
	}
	
	@Test
	@Transactional
	public void shouldInsertPizza() {

		Pizza pizza = new Pizza();
		pizza.setNombre("Probando");
		pizza.setContador(1);
		pizza.setCoste(14);
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		pizza.setTamano(tamanoProducto);
		tipoMasa tipo = new tipoMasa();
		tipo.setName("Fina");
		pizza.setTipoMasa(tipo);
		
		this.pizzaService.savePizza(pizza);
		Pizza pizzaEncontrada = this.pizzaService.findPizzaById(pizza.getId());
        assertThat(pizza).isEqualTo(pizzaEncontrada);
	}
	
	@Test
	@Transactional
	void shouldUpdatePizza() {
		Pizza pizza = this.pizzaService.findPizzaById(1);
		String oldNombre = pizza.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		pizza.setNombre(newNombre);
		this.pizzaService.savePizza(pizza);

		pizza = this.pizzaService.findPizzaById(1);
		assertThat(pizza.getNombre()).isEqualTo(newNombre);
	}
	
	@Test
	@Transactional
	void shouldDeletePizza() {
		Pizza pizza = this.pizzaService.findPizzaById(1);
		
		this.pizzaService.deletePizza(pizza);
		
		pizza = this.pizzaService.findPizzaById(1);
		
		assertNull(pizza);
	}
	
	
}