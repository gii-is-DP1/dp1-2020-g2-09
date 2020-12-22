package org.springframework.samples.petclinic.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

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
		//cliente.setFechaAlta(LocalDate.now());
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
       //this.clienteService.saveCliente(cliente);
        
        when(clienteRepository.findByUser(usuario)).thenReturn(cliente);
		clienteService.findCuentaByUser(usuario);
		verify(clienteRepository).findByUser(usuario);
		
	}
	
}
