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
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.PedidoRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class PedidoServiceTests {

	@Mock
	PedidoRepository pedidoRepository;
	
	PedidoService pedidoService;
	
	@BeforeEach
	void setUp() {
		pedidoService = new PedidoService(pedidoRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindPedidos() {
		when(pedidoRepository.findAll()).thenReturn(new ArrayList<>());
		pedidoService.findPedidos();
		verify(pedidoRepository).findAll();
	}
	
	@Test
	@Transactional
	public void shouldFindPedidoForCocinero() {
		when(pedidoRepository.findPedidoForCocinero()).thenReturn(new ArrayList<>());
		pedidoService.findPedidoForCocinero();
		verify(pedidoRepository).findPedidoForCocinero();
	}
	
	@Test
	@Transactional
	public void shouldFindPedidoForRepartidor() {
		when(pedidoRepository.findPedidoForRepartidor()).thenReturn(new ArrayList<>());
		pedidoService.findPedidoForRepartidor();
		verify(pedidoRepository).findPedidoForRepartidor();
	}

	
	
	@Test
	@Transactional
	public void shouldFindPedidosByCliente() {
		
		List<Pedido> pedidos = new ArrayList<>();
		Pedido pedido = new Pedido();
		pedido.setDireccion("C/ Niña de la Alfalfa");
		pedido.setFechaPedido(LocalDate.now());
		pedido.setGastosEnvio(0.0);
		pedido.setPrecio(0.0);
		
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
        
		pedido.setCliente(cliente);
		pedidos.add(pedido);
		
		when(pedidoRepository.findPedidosByCliente(anyInt())).thenReturn(pedidos);
		pedidoService.findPedidosByCliente(777);
		verify(pedidoRepository).findPedidosByCliente(777);
	}
	
	@Test
	@Transactional
	public void shouldNotFindPedidosByCliente() {
		
		verify(pedidoRepository, never()).findPedidosByCliente(777);
	}
	
	
	@Test
	@Transactional
	public void shouldFindPedidoById() {
		
		Pedido pedido = new Pedido();
		pedido.setDireccion("C/ Niña de la Alfalfa");
		pedido.setFechaPedido(LocalDate.now());
		pedido.setGastosEnvio(0.0);
		pedido.setPrecio(0.0);
		
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
        
		pedido.setCliente(cliente);
		
		when(pedidoRepository.findPedidoById(anyInt())).thenReturn(pedido);
		pedidoService.findPedidoById(777);
		verify(pedidoRepository).findPedidoById(777);
	}
	
	@Test
	@Transactional
	public void shouldNotFindPedidoById() {
		
		verify(pedidoRepository, never()).findPedidoById(777);
	}
	
	@Test
	@Transactional
	public void shouldFindEstadoPedido() {
		when(pedidoRepository.findEstadoPedido()).thenReturn(new ArrayList<>());
		pedidoService.findEstadoPedido();
		verify(pedidoRepository).findEstadoPedido();
	}
	
	@Test
	@Transactional
	public void findTipoPago() {
		when(pedidoRepository.findTipoPago()).thenReturn(new ArrayList<>());
		pedidoService.findTipoPago();
		verify(pedidoRepository).findTipoPago();
	}
	
	@Test
	@Transactional
	public void findTipoEnvio() {
		when(pedidoRepository.findTipoEnvio()).thenReturn(new ArrayList<>());
		pedidoService.findTipoEnvio();
		verify(pedidoRepository).findTipoEnvio();
	}
	
	@Test
	@Transactional
	public void shouldCogerPrecioPizza() {
		when(pedidoRepository.cogerPrecioPizza(anyInt())).thenReturn(12.25);
		pedidoService.cogerPrecioPizza(2);
		verify(pedidoRepository).cogerPrecioPizza(2);
	}
	
	@Test
	@Transactional
	public void shouldNotCogerPrecioPizza() {
		verify(pedidoRepository, never()).cogerPrecioPizza(2);
	}
	
	@Test
	@Transactional
	public void shouldCogerPrecioBebida() {
		when(pedidoRepository.cogerPrecioBebida(anyInt())).thenReturn(7.9);
		pedidoService.cogerPrecioBebida(3);
		verify(pedidoRepository).cogerPrecioBebida(3);
	}
	
	@Test
	@Transactional
	public void shouldNotCogerPrecioBebida() {
	
		verify(pedidoRepository, never()).cogerPrecioBebida(3);
	}
	
	@Test
	@Transactional
	public void shouldCogerPrecioOtros() {
		when(pedidoRepository.cogerPrecioOtros(anyInt())).thenReturn(22.5);
		pedidoService.cogerPrecioOtros(4);
		verify(pedidoRepository).cogerPrecioOtros(4);
	}
	
	@Test
	@Transactional
	public void shouldNotCogerPrecioOtros() {
		
		verify(pedidoRepository, never()).cogerPrecioOtros(4);
	}
	
	@Test
	@Transactional
	public void shouldfindIdPedidoByReclamacionId() {
		when(pedidoRepository.findIdPedidoByReclamacionId(anyInt())).thenReturn(1);
		pedidoService.findIdPedidoByReclamacionId(1);
		verify(pedidoRepository).findIdPedidoByReclamacionId(1);
	}
	
	@Test
	@Transactional
	public void  shouldNotFindIdPedidoByReclamacionId() {
		verify(pedidoRepository, never()).findIdPedidoByReclamacionId(1);
	}
	
	@Test
	@Transactional
	public void shouldFindPedidoByFecha() {
		Pedido pedido = new Pedido();
		pedido.setDireccion("C/ Niña de la Alfalfa");
		pedido.setFechaPedido(LocalDate.now());
		pedido.setGastosEnvio(0.0);
		pedido.setPrecio(0.0);
		
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
        
		pedido.setCliente(cliente);
		when(pedidoRepository.findPedidoByFecha(org.mockito.ArgumentMatchers.any(), anyInt())).thenReturn(pedido);
		pedidoService.findPedidoByFecha(LocalDate.now(), 1);
		verify(pedidoRepository).findPedidoByFecha(LocalDate.now(), 1);
	}
	
	@Test
	@Transactional
	public void shouldNotFindPedidoByFecha() {
		verify(pedidoRepository, never()).findPedidoByFecha(LocalDate.now(), 1);
	}
	
	
}
