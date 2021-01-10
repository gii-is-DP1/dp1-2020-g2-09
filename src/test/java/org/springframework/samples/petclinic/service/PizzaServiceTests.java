package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.User;
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
	
	@Test
	void shouldFindTipoMasa() {
		when(pizzaRepository.findTipoMasa()).thenReturn(new ArrayList<>());
		pizzaService.findTipoMasa();
		verify(pizzaRepository).findTipoMasa();
	}
	
	@Test
	void shouldFindTamaño() {
		when(pizzaRepository.findTamaño()).thenReturn(new ArrayList<>());
		pizzaService.findTamaño();
		verify(pizzaRepository).findTamaño();
	}
	
	@Test
	void shouldFindPizzaByCliente() {
		List<Pizza> pizzas = new ArrayList<>();
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		Pizza pizza = new Pizza();
		pizza.setNombre("Probando");
		pizza.setCoste(14);
		pizza.setTamano(tamanoProducto);
		tipoMasa tipo = new tipoMasa();
		tipo.setName("Fina");
		pizza.setTipoMasa(tipo);
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Paco");
		cliente.setApellidos("Florentino");
		cliente.setTelefono(683020234);
		cliente.setEmail("paquito@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
        cliente.setUser(usuario);  
        
        pizza.setCliente(cliente);
        pizzas.add(pizza);
		
		when(pizzaRepository.findPizzaByCliente(cliente)).thenReturn(pizzas);
		pizzaService.findPizzaByCliente(cliente);
		verify(pizzaRepository).findPizzaByCliente(cliente);
	}
	
	@Test
	void shouldFindPizzaNoPersonalizada() {
		List<Pizza> pizzas = new ArrayList<>();
		TamanoProducto tamanoProducto = new TamanoProducto();
		tamanoProducto.setName("Grande");
		Pizza pizza = new Pizza();
		pizza.setNombre("Probando");
		pizza.setCoste(14);
		pizza.setTamano(tamanoProducto);
		tipoMasa tipo = new tipoMasa();
		tipo.setName("Fina");
		pizza.setTipoMasa(tipo);
		pizzas.add(pizza);
		
		when(pizzaRepository.findPizzaNoPersonalizada()).thenReturn(pizzas);
		pizzaService.findPizzaNoPersonalizada();
		verify(pizzaRepository).findPizzaNoPersonalizada();
	}
}
