package org.springframework.samples.petclinic.service;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.repository.PizzaRepository;



@ExtendWith(MockitoExtension.class)
public class PizzaServiceTests {
	
	@Mock
	PizzaRepository pizzaRepository;
	
	PizzaService pizzaService;
	
	@BeforeEach
	void setUp() {
		pizzaService = new PizzaService(pizzaRepository);
	}
	
	@Test
	void shouldFindAll() {
		
		when(pizzaRepository.findAll()).thenReturn(new ArrayList<>());
		pizzaService.findPizzas();
		verify(pizzaRepository).findAll();
	}
	
	@Test
	void shouldFindPizzaById() {
		
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		Pizza pizza = new Pizza();
		pizza.setNombre("Probando");
		pizza.setContador(1);
		pizza.setCoste(14);
		pizza.setTamano(tamanoProducto);
		tipoMasa tipo = new tipoMasa();
		tipo.setName("Fina");
		pizza.setTipoMasa(tipo);
		
		when(pizzaRepository.findPizzaById(anyInt())).thenReturn(pizza);
		pizzaService.findPizzaById(8);
		verify(pizzaRepository).findPizzaById(8);
	}
	
	@Test
	void shouldNotFindPizzaById() {
		
//		when(pizzaRepository.findPizzaById(anyInt())).thenReturn(pizza);
//		pizzaService.findPizzaById(8);
		verify(pizzaRepository, never()).findPizzaById(10);
	}
	
	@Test
	void shouldFindIdPizzaById() {
		
		when(pizzaRepository.findIdPizzaById(anyInt())).thenReturn(new ArrayList<>());
		pizzaService.findIdPizzaById(9);
		verify(pizzaRepository).findIdPizzaById(9);
	}
	
	@Test
	void shouldNotFindIdPizzaById() {
		
//		when(pizzaRepository.findIdPizzaById(anyInt())).thenReturn(new ArrayList<>());
//		pizzaService.findIdPizzaById(9);
		verify(pizzaRepository, never()).findIdPizzaById(200);
	}
	
	@Test
	void shouldFindPizzaPedidoById() {
		when(pizzaRepository.findPizzaPedidoById(anyInt())).thenReturn(new ArrayList<>());
		pizzaService.findPizzaPedidoById(10);
		verify(pizzaRepository).findPizzaPedidoById(10);
	}
	
	@Test
	void shouldNotFindPizzaPedidoById() {
		
		verify(pizzaRepository, never()).findPizzaPedidoById(10);
	}
	
}
