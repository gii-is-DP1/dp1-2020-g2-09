package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class ClienteServiceTests {

	
	@Mock
	ClienteRepository clienteRepository;
	
	ClienteService clienteService;
	
	@BeforeEach
	void setUp() {
		clienteService = new ClienteService(clienteRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindClienteById() {

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
		
		when(clienteRepository.findById(anyInt())).thenReturn(cliente);
		clienteService.findCuentaById(7);
		verify(clienteRepository).findById(7);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindClienteById() {
		
//		when(clienteRepository.findById(anyInt())).thenReturn(cliente);
//		clienteService.findCuentaById(7);
		verify(clienteRepository, never()).findById(777);
		
	}
	
	@Test
	@Transactional
	public void shouldFindClienteByUser() {
	
		Cliente cliente = new Cliente();
		cliente.setNombre("Paco");
		cliente.setApellidos("Florentino");
		cliente.setTelefono(683020234);
		cliente.setEmail("paquito@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		//cliente.setFechaAlta(LocalDate.now());
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
        cliente.setUser(usuario);
        
        when(clienteRepository.findByUser(usuario)).thenReturn(cliente);
		clienteService.findCuentaByUser(usuario);
		verify(clienteRepository).findByUser(usuario);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindClienteByUser() {
		User usuario = new User();
		verify(clienteRepository, never()).findByUser(usuario);
	}
	
	@Test
	@Transactional
	public void shouldFindAllClientes() {
		
		when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
		clienteService.findCuentas();
		verify(clienteRepository).findAll();
	}
	
}
