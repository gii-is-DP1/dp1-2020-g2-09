package org.springframework.samples.petclinic.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	public void findOfertasTrueEnTiempo() {
		LocalDate hoy=LocalDate.now();
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.now());
		o.setFechaFinal(hoy.plusDays(30));
		
		NivelSocio ns = new NivelSocio();
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		
		List<Oferta> l=new ArrayList<>();
		l.add(o);
		
		when(ofertaRepository.findOfertasTrueEnTiempo(hoy)).thenReturn(l);
		ofertaService.findOfertasTrueEnTiempo(hoy);
		verify(ofertaRepository).findOfertasTrueEnTiempo(hoy);	
	}
	
	@Test
	@Transactional
	public void findNotOfertasTrueEnTiempo() {
		LocalDate hoy=LocalDate.now();
		LocalDate fecha=LocalDate.of(2020, 11, 01);
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(fecha);
		o.setFechaFinal(fecha.plusDays(30));
		
		NivelSocio ns = new NivelSocio();
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		
		List<Oferta> l=new ArrayList<>();
		l.add(o);
		
		verify(ofertaRepository, never()).findOfertasTrueEnTiempo(hoy);	
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
	
	@Test
	@Transactional
	public void shouldFindOfertasNivelSocio() {
		when(ofertaRepository.ofertasNivelSocio(anyInt())).thenReturn(new ArrayList<>());
		ofertaService.ofertasNivelSocio(1);
		verify(ofertaRepository).ofertasNivelSocio(1);
	}
	 

}
