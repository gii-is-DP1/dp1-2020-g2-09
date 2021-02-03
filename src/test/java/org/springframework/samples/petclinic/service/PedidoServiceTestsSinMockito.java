package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
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
	
	@Autowired
	protected PizzaService pizzaService;
	
	@Autowired
	protected BebidaService bebidaService;
	
	@Autowired
	protected OtrosService otrosService;
	
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
	
	@Test
	@Transactional
	void shouldPutEnCocina() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		EstadoPedido newEstado = new EstadoPedido();
		newEstado.setName("EN COCINA");
		this.pedidoService.putEnCocina(pedido.getId());
		assertThat(newEstado.equals(pedido.getEstadoPedido()));
		
	}
	
	@Test
	@Transactional
	void shouldPutPreparado() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		EstadoPedido newEstado = new EstadoPedido();
		newEstado.setName("PREPARADO");
		this.pedidoService.putPreparado(pedido.getId());
		assertThat(newEstado.equals(pedido.getEstadoPedido()));
	}
	
	@Test
	@Transactional
	void shouldPutEnReparto() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		EstadoPedido newEstado = new EstadoPedido();
		newEstado.setName("EN REPARTO");
		this.pedidoService.putEnReparto(pedido.getId());
		assertThat(newEstado.equals(pedido.getEstadoPedido()));
	}
	
	@Test
	@Transactional
	void shouldPutEntregado() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		EstadoPedido newEstado = new EstadoPedido();
		newEstado.setName("ENTREGADO");
		this.pedidoService.putEntregado(pedido.getId());
		assertThat(newEstado.equals(pedido.getEstadoPedido()));
	}
	
	@Test
	@Transactional
	void shouldPutRecogido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		EstadoPedido newEstado = new EstadoPedido();
		newEstado.setName("RECOGIDO");
		this.pedidoService.putRecogido(pedido.getId());
		assertThat(newEstado.equals(pedido.getEstadoPedido()));
	}
	
	@Test
	@Transactional
	void shouldAñadirPizzaAPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Pizza pizza = this.pizzaService.findPizzaById(1);
		this.pedidoService.añadirPizzaAPedido(pedido.getId(), pizza.getId());
		assertThat(pedido.getPizzasEnPedido().contains(pizza));
		
	}
	
	@Test
	@Transactional
	void shouldAñadirBebidaAPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Bebida bebida = this.bebidaService.findById(1);
		this.pedidoService.añadirBebidaAPedido(pedido.getId(), bebida.getId());
		assertThat(pedido.getBebidasEnPedido().contains(bebida));
		
	}
	
	@Test
	@Transactional
	void shouldAñadirOtrosAPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Otro otro = this.otrosService.findOtrosById(1);
		this.pedidoService.añadirOtrosAPedido(pedido.getId(), otro.getId());
		assertThat(pedido.getOtrosEnPedido().contains(otro));
		
	}
	
	@Test
	@Transactional
	void shouldEliminarPizzaPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Pizza pizza = new Pizza();
		pizza.setId(1);
		this.pedidoService.añadirPizzaAPedido(pedido.getId(), pizza.getId());
		List<Pizza> pizzas = new ArrayList<>(pedido.getPizzasEnPedido());
		Pizza p = pizzas.get(0);
		this.pedidoService.eliminarPizzaPedido(pedido.getId(), p.getId());
		assertThat(!pedido.getPizzasEnPedido().contains(p));
		
		
	}
	
	@Test
	@Transactional
	void shouldEliminarBebidaPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Bebida bebida = new Bebida();
		bebida.setId(1);
		this.pedidoService.añadirBebidaAPedido(pedido.getId(), bebida.getId());
		List<Bebida> bebidas = new ArrayList<>(pedido.getBebidasEnPedido());
		Bebida b = bebidas.get(0);
		this.pedidoService.eliminarBebidaPedido(pedido.getId(), b.getId());
		assertThat(!pedido.getBebidasEnPedido().contains(b));
		
		
	}
	
	
	@Test
	@Transactional
	void shouldEliminarOtroPedido() {
		Pedido pedido = this.pedidoService.findPedidoById(1);
		Otro otro = new Otro();
		otro.setId(1);
		this.pedidoService.añadirOtrosAPedido(pedido.getId(), otro.getId());
		List<Otro> otros = new ArrayList<>(pedido.getOtrosEnPedido());
		Otro o = otros.get(0);
		this.pedidoService.eliminarOtrosPedido(pedido.getId(), o.getId());
		assertThat(!pedido.getOtrosEnPedido().contains(o));
		
		
	}
	
	
	
}

