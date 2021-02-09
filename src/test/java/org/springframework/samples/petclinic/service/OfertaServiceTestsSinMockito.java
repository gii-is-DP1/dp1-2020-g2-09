package org.springframework.samples.petclinic.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class OfertaServiceTestsSinMockito {
	
	@Autowired
	protected OfertaService ofertaService;
	
	@Autowired
	protected PizzaService pizzaService;

	@Autowired
	protected BebidaService bebidaService;

	@Autowired
	protected OtrosService otrosService;
	
	
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
		oferta.setId(1000);
	     
		this.ofertaService.saveOferta(oferta);
		Oferta ofertaEncontrada = this.ofertaService.findOfertaById(1000);
		assertNull(ofertaEncontrada);
		
		
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
	 void shouldAsociarOfertaAPizza() {
		 	Oferta o = this.ofertaService.findOfertaById(1);
			Pizza p = this.pizzaService.findPizzaById(1);
			this.ofertaService.asociarOfertaAPizza(o.getId(), p.getId());
			assertThat(o.getPizzasEnOferta().contains(p));	 
	}
		
	 @Test
	 @Transactional
	 void shouldAsociarOfertaABebida() {
		 	Oferta o = this.ofertaService.findOfertaById(1);
			Bebida b = this.bebidaService.findById(1);
			this.ofertaService.asociarOfertaABebida(o.getId(), b.getId());
			assertThat(o.getBebidasEnOferta().contains(b));	 
	}
	 
	 @Test
	 @Transactional
	 void shouldAsociarOfertaAOtro() {
		 	Oferta o = this.ofertaService.findOfertaById(1);
			Otro ot = this.otrosService.findOtrosById(1);
			this.ofertaService.asociarOfertaAOtro(o.getId(), ot.getId());
			assertThat(o.getOtrosEnOferta().contains(ot));	 
	}
	 
	 @Test
	 @Transactional
	 void shouldPonerEstadoOfertaAFalse() {
		 Oferta of=this.ofertaService.findOfertaById(1);
		this.ofertaService.ponerEstadoOfertaAFalse(of.getId());
		assertFalse(of.getEstadoOferta());
	 }
	
}
