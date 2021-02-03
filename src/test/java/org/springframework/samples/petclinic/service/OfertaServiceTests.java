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
	public void shouldNotFindAllOfertas() {
		verify(ofertaRepository, never()).findAll();
	}
	
	@Test
	@Transactional
	public void shouldFindOfertasByEstadoOferta() {
		when(ofertaRepository.findOfertasByEstadoOferta(org.mockito.ArgumentMatchers.anyBoolean())).thenReturn(new ArrayList<>());
		ofertaService.findOfertasByEstadoOferta(true);
		verify(ofertaRepository).findOfertasByEstadoOferta(true);
	}
	
	@Test
	@Transactional
	public void shouldNotFindOfertasByEstadoOferta() {
		verify(ofertaRepository, never()).findOfertasByEstadoOferta(true);
	}
	
	@Test
	@Transactional
	public void shouldFindTamanoOferta() {
		when(ofertaRepository.findTamanoOferta()).thenReturn(new ArrayList<>());
		ofertaService.findTamanoOferta();
		verify(ofertaRepository).findTamanoOferta();
	}
	
	@Test
	@Transactional
	public void shouldNotFindTamanoOferta() {
		verify(ofertaRepository, never()).findTamanoOferta();
	}
	
	@Test
	@Transactional
	public void shouldFindNivelSocio() {
		when(ofertaRepository.findNivelSocio()).thenReturn(new ArrayList<>());
		ofertaService.findNivelSocio();
		verify(ofertaRepository).findNivelSocio();
	}
	
	@Test
	@Transactional
	public void shouldNotFindNivelSocio() {
		verify(ofertaRepository, never()).findNivelSocio();
	}
	
	@Test
	@Transactional
	public void shouldFindOfertasEnPedidoById() {
		when(ofertaRepository.findOfertasEnPedidoById(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.findOfertasEnPedidoById(1);
		verify(ofertaRepository).findOfertasEnPedidoById(1);
		
	}
	
	@Test
	@Transactional
	public void shouldNotFindOfertasEnPedidoById() {
		verify(ofertaRepository, never()).findOfertasEnPedidoById(1);
	}
	
	@Test
	@Transactional
	public void shouldNumeroPizzasEnOferta() {
		when(ofertaRepository.numeroPizzasEnOferta(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.numeroPizzasEnOferta(1);
		verify(ofertaRepository).numeroPizzasEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldNotNumeroPizzasEnOferta() {
		verify(ofertaRepository, never()).numeroPizzasEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldNumeroBebidasEnOferta() {
		when(ofertaRepository.numeroBebidasEnOferta(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.numeroBebidasEnOferta(1);
		verify(ofertaRepository).numeroBebidasEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldNotNumeroBebidasEnOferta() {
		verify(ofertaRepository, never()).numeroBebidasEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldNumeroOtrosEnOferta() {
		when(ofertaRepository.numeroOtrosEnOferta(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.numeroOtrosEnOferta(1);
		verify(ofertaRepository).numeroOtrosEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldNotNumeroOtrosEnOferta() {
		verify(ofertaRepository, never()).numeroOtrosEnOferta(1);
	}
	
	@Test
	@Transactional
	public void shouldFindPizzasEnOferta() {
		when(ofertaRepository.findPizzasEnOferta()).thenReturn(new ArrayList<>());
		ofertaService.findPizzasEnOferta();
		verify(ofertaRepository).findPizzasEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldNotFindPizzasEnOferta() {
		verify(ofertaRepository, never()).findPizzasEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldFindBebidasEnOferta() {
		when(ofertaRepository.findBebidasEnOferta()).thenReturn(new ArrayList<>());
		ofertaService.findBebidasEnOferta();
		verify(ofertaRepository).findBebidasEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldNotFindBebidasEnOferta() {
		verify(ofertaRepository, never()).findBebidasEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldFindOtrosEnOferta() {
		when(ofertaRepository.findOtrosEnOferta()).thenReturn(new ArrayList<>());
		ofertaService.findOtrosEnOferta();
		verify(ofertaRepository).findOtrosEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldNotFindOtrosEnOferta() {
		verify(ofertaRepository, never()).findOtrosEnOferta();
	}
	
	@Test
	@Transactional
	public void shouldFindPizzasEnOfertaByOfertaId(){
		when(ofertaRepository.findPizzasEnOfertaByOfertaId(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.findPizzasEnOfertaByOfertaId(1);
		verify(ofertaRepository).findPizzasEnOfertaByOfertaId(1);
	}
	
	@Test
	@Transactional
	public void shouldNotFindPizzasEnOfertaByOfertaId() {
		verify(ofertaRepository, never()).findPizzasEnOfertaByOfertaId(1);
	}
	
	@Test
	@Transactional
	public void shouldFindBebidasEnOfertaByOfertaId(){
		when(ofertaRepository.findBebidasEnOfertaByOfertaId(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.findBebidasEnOfertaByOfertaId(1);
		verify(ofertaRepository).findBebidasEnOfertaByOfertaId(1);
	}
	
	@Test
	@Transactional
	public void shouldNotFindBebidasEnOfertaByOfertaId() {
		verify(ofertaRepository, never()).findBebidasEnOfertaByOfertaId(1);
	}
	
	@Test
	@Transactional
	public void shouldFindOtrosEnOfertaByOfertaId(){
		when(ofertaRepository.findOtrosEnOfertaByOfertaId(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.findOtrosEnOfertaByOfertaId(1);
		verify(ofertaRepository).findOtrosEnOfertaByOfertaId(1);
	}
	
	@Test
	@Transactional
	public void shouldNotFindOtrosEnOfertaByOfertaId() {
		verify(ofertaRepository, never()).findOtrosEnOfertaByOfertaId(1);
	}
	
	
	
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
