package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClienteServiceTestsSinMockito {

	@Autowired
	protected ClienteService clienteService;
	
	
	@Test
	@Transactional
	public void shouldInsertCliente() {

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
                
		this.clienteService.saveCliente(cliente);
		Cliente clienteEncontrado = this.clienteService.findCuentaById(cliente.getId());
		assertThat(cliente).isEqualTo(clienteEncontrado);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertClienteNullNombre() {

		Cliente cliente = new Cliente();
		cliente.setApellidos("Florentino");
		cliente.setTelefono(683020234);
		cliente.setEmail("paquito@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 12, 9));
		User usuario = new User();
		usuario.setUsername("PAquitoO");
		usuario.setPassword("Tomate y papas");
		usuario.setEnabled(true);
        cliente.setUser(usuario);
        
        cliente.setId(1000);
                
		this.clienteService.saveCliente(cliente);
		Cliente clienteEncontrado = this.clienteService.findCuentaById(1000);
		assertNull(clienteEncontrado);
	}
	
	@Test
	@Transactional
	void shouldUpdateCliente() {
		Cliente cliente = this.clienteService.findCuentaById(1);
		String oldNombre = cliente.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		cliente.setNombre(newNombre);
		this.clienteService.saveCliente(cliente);

		cliente = this.clienteService.findCuentaById(1);
		assertThat(cliente.getNombre()).isEqualTo(newNombre);
	}
	
	@Test
	@Transactional
	void shouldDeleteCliente() {
		Cliente cliente = this.clienteService.findCuentaById(1);
		
		this.clienteService.deleteCliente(cliente);
		
		cliente = this.clienteService.findCuentaById(1);
		
		assertNull(cliente);
	}
	
}
