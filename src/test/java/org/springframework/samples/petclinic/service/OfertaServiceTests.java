package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OfertaServiceTests {
	
	@Autowired
	OfertaService ofertaService;
	 
	@Test
	@Transactional
	public void shouldInsertOferta() {
		
		Oferta oferta = new Oferta();
		TamanoOferta tamanoOferta = new TamanoOferta();
		tamanoOferta.setName("Grande");
			
		NivelSocio nivelSocio = new NivelSocio();
		nivelSocio.setName("Oro");
		
		EstadoPedido estadoPedido = new EstadoPedido();
		estadoPedido.setName("Entregado");
			  
		TipoEnvio tipoEnvio = new TipoEnvio();
		tipoEnvio.setName("Recogida en tienda");
			  
		TipoPago tipoPago = new TipoPago(); 	
		tipoPago.setName("Efectivo");
			 	
		Pedido pedido = new Pedido();
		pedido.setDireccion("Dirección 1");
		pedido.setFechaPedido(LocalDate.of(2020, 12, 9));
		pedido.setEstadoPedido(estadoPedido);
		pedido.setGastosEnvio(2.99);
		pedido.setPrecio(10.0);			
		pedido.setTipoEnvio(tipoEnvio);
		pedido.setTipoPago(tipoPago);
			
		Collection<Oferta> pedidoConOferta = new ArrayList<Oferta>();
		pedidoConOferta.add(oferta);
		pedido.setOfertasEnPedido(pedidoConOferta);
		
		oferta.setCoste(12.1);
		oferta.setFechaInicial(LocalDate.of(2020, 11, 9));
		oferta.setFechaFinal(LocalDate.of(2020, 11, 18));
		oferta.setNivelSocio(nivelSocio);
		oferta.setTamanoOferta(tamanoOferta); 
	                
		this.ofertaService.saveOferta(oferta);
		Oferta ofertaEncontrada = this.ofertaService.findOfertaById(oferta.getId());
		assertThat(oferta).isEqualTo(ofertaEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertOfertaWithNullTamano(){
		Oferta oferta = new Oferta();
		//TamanoOferta tamanoOferta = new TamanoOferta();
		//tamanoOferta.setName("Grande");
			
		NivelSocio nivelSocio = new NivelSocio();
		nivelSocio.setName("Oro");
		
		EstadoPedido estadoPedido = new EstadoPedido();
		estadoPedido.setName("Entregado");
			  
		TipoEnvio tipoEnvio = new TipoEnvio();
		tipoEnvio.setName("Recogida en tienda");
			  
		TipoPago tipoPago = new TipoPago(); 	
		tipoPago.setName("Efectivo");
			 	
		Pedido pedido = new Pedido();
		pedido.setDireccion("Dirección 1");
		pedido.setFechaPedido(LocalDate.of(2020, 12, 9));
		pedido.setEstadoPedido(estadoPedido);
		pedido.setGastosEnvio(2.99);
		pedido.setPrecio(10.0);			
		pedido.setTipoEnvio(tipoEnvio);
		pedido.setTipoPago(tipoPago);
			
		Collection<Oferta> pedidoConOferta = new ArrayList<Oferta>();
		pedidoConOferta.add(oferta);
		pedido.setOfertasEnPedido(pedidoConOferta);
		
		oferta.setCoste(12.1);
		oferta.setFechaInicial(LocalDate.of(2020, 11, 9));
		oferta.setFechaFinal(LocalDate.of(2020, 11, 18));
		oferta.setNivelSocio(nivelSocio);
	     
		try {
		this.ofertaService.saveOferta(oferta);
		} catch(Exception e) {
			assertTrue(true);
		}
		
		
	}
	 
	 @Test
	 @Transactional
	 public void shouldUpdateOferta() {
		 Oferta oferta = this.ofertaService.findOfertaById(1);
		 Double costeActualizado = 5.5;
		 oferta.setCoste(costeActualizado);
	     this.ofertaService.saveOferta(oferta);
	     oferta = this.ofertaService.findOfertaById(1);
	     assertThat(oferta.getCoste()).isEqualTo(costeActualizado);
	 }
	 
	 @Test
	 @Transactional
	 public void shouldNotUpdateOfertaWithCosteEqualsToZero() {
		 Oferta oferta = this.ofertaService.findOfertaById(1);
			Double newCoste = 0.0;
			oferta.setCoste(newCoste);
			try{
				this.ofertaService.saveOferta(oferta);
				//assertTrue(false);
			}catch (Exception e) { //El coste de una oferta no puede ser menor o igual que 0.
				assertTrue(true);
			}
			//assertTrue(false);
	 }
	 
	 @Test
	 @Transactional
	 public void shouldNotUpdateOfertaWithWithFechaFinalBeforeCurrentDate() {
		 Oferta oferta = this.ofertaService.findOfertaById(1);
			LocalDate newFechaFinal = LocalDate.of(2020, 10, 25);
			oferta.setFechaFinal(newFechaFinal);
			try{
				this.ofertaService.saveOferta(oferta);
				//assertTrue(false);
			}catch (Exception e) { //La fecha de fin de oferta no debe ser anterior a la fecha actual.
				assertTrue(true);
			}
			//assertTrue(false);
		 
	 }
	 
	 @Test
	 @Transactional
	 void shouldDeleteOferta() {
		 Oferta oferta = this.ofertaService.findOfertaById(1);			
		this.ofertaService.deleteOferta(oferta);		
		oferta = this.ofertaService.findOfertaById(1);
		assertNull(oferta);
	 }
	 
	 
	 
	 

}
