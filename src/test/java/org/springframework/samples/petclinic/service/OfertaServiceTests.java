package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.repository.OfertaRepository;
import org.springframework.transaction.annotation.Transactional;

//@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@ExtendWith(MockitoExtension.class)
public class OfertaServiceTests {
	
	@Mock
	OfertaRepository ofertaRepository;
	
	OfertaService ofertaService;
	
	@BeforeEach
	void setup() {
		ofertaService = new OfertaService(ofertaRepository);
	}
	
	@Test
	@Transactional
	public void shouldFindOfertaById() {
		
		
		//Cargo los datos del SUT
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.now());
		o.setFechaFinal(LocalDate.of(2020, 12, 29));
		
		NivelSocio ns = new NivelSocio();
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		
		//Hago la prueba
		when(ofertaRepository.findOfertaById(anyInt())).thenReturn(o);
		ofertaService.findOfertaById(10);
		
		//Testeo que se ha llamado al método indicado una vez
		verify(ofertaRepository).findOfertaById(10);
	}
	
	 
	
	@Test
	@Transactional
	public void shouldNotFindOfertaById() {
		verify(ofertaRepository, never()).findOfertaById(10);
	}

	@Test
	@Transactional
	public void shouldFindAllOfertas() {
		when(ofertaRepository.findAll()).thenReturn(new ArrayList<>());
		ofertaService.findOfertas();
		verify(ofertaRepository).findAll();
	}
	
	@Test
	@Transactional
	public void shouldNotFindAllReclamaciones() {
		verify(ofertaRepository, never()).findAll();
	}
	
	@Test
	@Transactional
	public void shouldSaveOferta() {
		//Cargo los datos del SUT
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.now());
		o.setFechaFinal(LocalDate.of(2020, 12, 29));
		
		NivelSocio ns = new NivelSocio();
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setName("GRANDE");
		o.setTamanoOferta(to);
				
	
		
		when(ofertaRepository.save(o)).thenReturn(o);
		ofertaRepository.save(o);
		verify(ofertaRepository).save(o);
	}
	
//	@Test
//	@Transactional
//	public void shouldNotSaveOferta() {
//		//Cargo los datos del SUT
//		Oferta o = new Oferta();
//		o.setCoste(20.0);
//		o.setFechaInicial(LocalDate.now());
//		o.setFechaFinal(LocalDate.of(2020, 12, 29));
//				
//		NivelSocio ns = new NivelSocio();
//		ns.setName("ORO");
//		o.setNivelSocio(ns);
//				
//		TamanoOferta to = new TamanoOferta();
//		to.setName("GRANDE");
//		o.setTamanoOferta(to);
//				
//		
//		verify(ofertaRepository, never())).saveOferta(o);
//		
//	}
	
//	@Test
//	@Transactional
//	public void shouldInsertOferta() {
//		
//		Oferta oferta = new Oferta();
//		TamanoOferta tamanoOferta = new TamanoOferta();
//		tamanoOferta.setName("Grande");
//			
//		NivelSocio nivelSocio = new NivelSocio();
//		nivelSocio.setName("Oro");
//		
//		EstadoPedido estadoPedido = new EstadoPedido();
//		estadoPedido.setName("Entregado");
//			  
//		TipoEnvio tipoEnvio = new TipoEnvio();
//		tipoEnvio.setName("Recogida en tienda");
//			  
//		TipoPago tipoPago = new TipoPago(); 	
//		tipoPago.setName("Efectivo");
//			 	
//		Pedido pedido = new Pedido();
//		pedido.setDireccion("Dirección 1");
//		pedido.setFechaPedido(LocalDate.of(2020, 12, 9));
//		pedido.setEstadoPedido(estadoPedido);
//		pedido.setGastosEnvio(2.99);
//		pedido.setPrecio(10.0);			
//		pedido.setTipoEnvio(tipoEnvio);
//		pedido.setTipoPago(tipoPago);
//			
//		Collection<Oferta> pedidoConOferta = new ArrayList<Oferta>();
//		pedidoConOferta.add(oferta);
//		pedido.setOfertasEnPedido(pedidoConOferta);
//		
//		oferta.setCoste(12.1);
//		oferta.setFechaInicial(LocalDate.of(2020, 11, 9));
//		oferta.setFechaFinal(LocalDate.of(2020, 11, 18));
//		oferta.setNivelSocio(nivelSocio);
//		oferta.setTamanoOferta(tamanoOferta); 
//	                
//		this.ofertaService.saveOferta(oferta);
//		Oferta ofertaEncontrada = this.ofertaService.findOfertaById(oferta.getId());
//		assertThat(oferta).isEqualTo(ofertaEncontrada);
//	}
//	
//	@Test
//	@Transactional
//	public void shouldNotInsertOfertaWithNullTamano(){
//		Oferta oferta = new Oferta();
//		//TamanoOferta tamanoOferta = new TamanoOferta();
//		//tamanoOferta.setName("Grande");
//			
//		NivelSocio nivelSocio = new NivelSocio();
//		nivelSocio.setName("Oro");
//		
//		EstadoPedido estadoPedido = new EstadoPedido();
//		estadoPedido.setName("Entregado");
//			  
//		TipoEnvio tipoEnvio = new TipoEnvio();
//		tipoEnvio.setName("Recogida en tienda");
//			  
//		TipoPago tipoPago = new TipoPago(); 	
//		tipoPago.setName("Efectivo");
//			 	
//		Pedido pedido = new Pedido();
//		pedido.setDireccion("Dirección 1");
//		pedido.setFechaPedido(LocalDate.of(2020, 12, 9));
//		pedido.setEstadoPedido(estadoPedido);
//		pedido.setGastosEnvio(2.99);
//		pedido.setPrecio(10.0);			
//		pedido.setTipoEnvio(tipoEnvio);
//		pedido.setTipoPago(tipoPago);
//			
//		Collection<Oferta> pedidoConOferta = new ArrayList<Oferta>();
//		pedidoConOferta.add(oferta);
//		pedido.setOfertasEnPedido(pedidoConOferta);
//		
//		oferta.setCoste(12.1);
//		oferta.setFechaInicial(LocalDate.of(2020, 11, 9));
//		oferta.setFechaFinal(LocalDate.of(2020, 11, 18));
//		oferta.setNivelSocio(nivelSocio);
//	     
//		try {
//		this.ofertaService.saveOferta(oferta);
//		} catch(Exception e) {
//			assertTrue(true);
//		}
//		
//		
//	}
//	 
//	 @Test
//	 @Transactional
//	 public void shouldUpdateOferta() {
//		 Oferta oferta = this.ofertaService.findOfertaById(1);
//		 Double costeActualizado = 5.5;
//		 oferta.setCoste(costeActualizado);
//	     this.ofertaService.saveOferta(oferta);
//	     oferta = this.ofertaService.findOfertaById(1);
//	     assertThat(oferta.getCoste()).isEqualTo(costeActualizado);
//	 }
//	 
//	 @Test
//	 @Transactional
//	 public void shouldNotUpdateOfertaWithCosteEqualsToZero() {
//		 Oferta oferta = this.ofertaService.findOfertaById(1);
//			Double newCoste = 0.0;
//			oferta.setCoste(newCoste);
//			try{
//				this.ofertaService.saveOferta(oferta);
//				//assertTrue(false);
//			}catch (Exception e) { //El coste de una oferta no puede ser menor o igual que 0.
//				assertTrue(true);
//			}
//			//assertTrue(false);
//	 }
//	 
//	 @Test
//	 @Transactional
//	 public void shouldNotUpdateOfertaWithWithFechaFinalBeforeCurrentDate() {
//		 Oferta oferta = this.ofertaService.findOfertaById(1);
//			LocalDate newFechaFinal = LocalDate.of(2020, 10, 25);
//			oferta.setFechaFinal(newFechaFinal);
//			try{
//				this.ofertaService.saveOferta(oferta);
//				//assertTrue(false);
//			}catch (Exception e) { //La fecha de fin de oferta no debe ser anterior a la fecha actual.
//				assertTrue(true);
//			}
//			//assertTrue(false);
//		 
//	 }
//	 
//	 @Test
//	 @Transactional
//	 void shouldDeleteOferta() {
////		 Oferta oferta = this.ofertaService.findOfertaById(1);			
////		this.ofertaService.deleteOferta(oferta);		
////		oferta = this.ofertaService.findOfertaById(1);
////		assertNull(oferta);
//		 
//		 
//		 //Me creo una oferta que no está linkeada a ningún pedido y así no
//		 //no da problemas al borrar oferta de un pedido.
//		 
//		 Oferta oferta = new Oferta();
//		 oferta.setId(100);
//		oferta.setCoste(12.1);
//		oferta.setFechaInicial(LocalDate.of(2020, 11, 9));
//		oferta.setFechaFinal(LocalDate.of(2020, 11, 18));
//		
//		NivelSocio ns = new NivelSocio();
//		ns.setName("PLATA");
//		oferta.setNivelSocio(ns);
//		
//		this.ofertaService.saveOferta(oferta);
//		this.ofertaService.deleteOferta(oferta);
//		
//		oferta = this.ofertaService.findOfertaById(100);
//		assertNull(oferta);
//	 }
	 
	 
	 
	 

}
