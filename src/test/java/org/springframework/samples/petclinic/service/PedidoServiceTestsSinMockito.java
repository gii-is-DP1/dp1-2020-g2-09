package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PedidoServiceTestsSinMockito {

	@Autowired
	protected PedidoService pedidoService;
	
	@Autowired
	protected ClienteService clienteService;
	
	
/*	@Test
	void shouldFindPedidoByUser() {
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

        this.clienteService.saveCliente(cliente);
		Cuenta clienteEncontrado = this.clienteService.findCuentaByUser(usuario);

		assertThat(cliente).isEqualTo(clienteEncontrado);
	}*/
	
/*	@Test
	void shouldFindClienteByUserYaCreado() {
		User usuario = new User();
		usuario.setUsername("margarcac1");
		usuario.setPassword("margarcac1");
		usuario.setEnabled(true);
		Cuenta clienteEncontrado = this.clienteService.findCuentaByUser(usuario);
		assertThat(clienteEncontrado.getUser().getUsername()).isEqualTo("margarcac1");
//		System.out.println("Cliente: " + clienteEncontrado.getUser().getUsername());
	}*/
	
	@Test
	@Transactional
	public void shouldInsertPedido() {
		Pedido pedido = new Pedido();
		TipoPago pago = new TipoPago();
		TipoEnvio envio = new TipoEnvio();
		EstadoPedido estado = new EstadoPedido();
		Cliente cliente = this.clienteService.findCuentaById(1);
		pedido.setDireccion("C/Ferrara 4, 9A");
		pedido.setPrecio(50.65);
		pedido.setGastosEnvio(3.5);
		pedido.setEstadoPedido(estado);
		pedido.setFechaPedido(LocalDate.of(2020, 11, 9));
		pedido.setCliente(cliente);
		pedido.setTipoEnvio(envio);
		pedido.setTipoPago(pago);              
		this.pedidoService.savePedido(pedido);
		List<Pedido> pedidoEncontrados = this.pedidoService.findPedidos();
		assertThat(pedido).isIn(pedidoEncontrados);
	}
	
	@Test
	@Transactional
	void shouldUpdatePedido() {
		Pedido pedido = this.pedidoService.findPedidoById(2);
		Double newPrecio=55.2;
		
		pedido.setPrecio(newPrecio);
		this.pedidoService.savePedido(pedido);

		pedido = this.pedidoService.findPedidoById(2);
		assertThat(pedido.getPrecio()).isEqualTo(newPrecio);
	}
	
	@Test
	@Transactional
	void shouldDeletePedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		
		this.pedidoService.deletePedido(pedido);
		
		pedido = this.pedidoService.findPedidoById(1);
		
		assertNull(pedido);
	}
	
}

