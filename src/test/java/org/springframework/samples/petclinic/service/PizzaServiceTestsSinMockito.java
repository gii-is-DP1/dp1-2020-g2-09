package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PizzaServiceTestsSinMockito {

	@Autowired
	protected PizzaService pizzaService;
	
	@Autowired
	protected CartaService cartaService;
	
	
	@Test
	@Transactional
	public void shouldInsertPizza() {

		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		Pizza pizza = new Pizza();
		pizza.setNombre("Probando");
		pizza.setCoste(14.0);
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
	public void shouldNotInsertPizzaWithNullNombre() {
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		Pizza pizza = new Pizza();
		pizza.setCoste(14.0);
		pizza.setTamano(tamanoProducto);
		tipoMasa tipo = new tipoMasa();
		tipo.setName("Fina");
		pizza.setTipoMasa(tipo);
		pizza.setId(1000);
		
		this.pizzaService.savePizza(pizza);
		Pizza pizzaEncontrada = this.pizzaService.findPizzaById(1000);
        assertNull(pizzaEncontrada);
		
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
	 void shouldAñadirPizzaACarta() {
		 	Carta c = this.cartaService.findCartaById(1);
			Pizza p = this.pizzaService.findPizzaById(1);
			this.pizzaService.añadirPizzaACarta(p.getId(), c.getId());
			assertThat(c.getPizzasEnCarta().contains(p));	 
	}
	
	 @Test
	 @Transactional
	 void shouldDeletePizzaFromComposicionCarta() {
		 	Carta c = this.cartaService.findCartaById(1);
			Pizza p = this.pizzaService.findPizzaById(1);
			this.pizzaService.deletePizzaFromComposicionCarta(p.getId());
			assertThat(!c.getPizzasEnCarta().contains(p));	 
	}
	
}
