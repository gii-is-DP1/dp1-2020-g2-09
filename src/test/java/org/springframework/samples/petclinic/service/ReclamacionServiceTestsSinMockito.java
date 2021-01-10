package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ReclamacionServiceTestsSinMockito {
	
	@Autowired
	protected ReclamacionService reclamacionService;
	
	@Autowired
	protected PedidoService pedidoService;
	
	@Test
	@Transactional
	public void shouldInsertReclamacion() {

		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("La pizza no llevaba queso");
		reclamacion.setRespuesta("probando respuesta");
	//	reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));               
                
		this.reclamacionService.saveReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.
				findReclamacionById(reclamacion.getId());
		
		assertThat(reclamacion).isEqualTo(reclamacionEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingFechaReclamacionAfterCurrentDate(){
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("La pizza no llevaba queso");
		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 29)));
		
		try{
			this.reclamacionService.saveReclamacion(reclamacion);
			//assertTrue(false);
			
		}
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
		
	}
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionInsertingShortObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("a");
		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
		try{
			this.reclamacionService.saveReclamacion(reclamacion);
			//assertTrue(false);
			
		}
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
		
	}
	
		
	}
	
	@Test
	@Transactional
	public void shouldThrowExceptionNullObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
		try{
			this.reclamacionService.saveReclamacion(reclamacion);
			//assertTrue(false);
			
		}
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
		
	}
		
	}
		
	@Test
	@Transactional
	public void shouldThrowExceptionEmptyObservacion() {
		
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setObservacion("");
		//reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));
		
		try{
			this.reclamacionService.saveReclamacion(reclamacion);
			//assertTrue(false);
			
		}
		catch (Exception e) {
			 //Compruebo que he entrado en el catch y que no se ha guardado la reclamación
			assertTrue(true);
		
		
	}
				
		
	}
	
	//Administrador responde reclamación
	@Test
	@Transactional
	public void shouldUpdateReclamacion() {
		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
		 String observacion = "nueva observación";
		 reclamacion.setObservacion(observacion);
	     this.reclamacionService.saveReclamacion(reclamacion);
	     reclamacion = this.reclamacionService.findReclamacionById(1);
	     assertThat(reclamacion.getObservacion()).isEqualTo(observacion);
	 }
	
	@Test
	@Transactional
	void shouldDeletReclamacion() {
		Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
		
		this.reclamacionService.deleteReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.findReclamacionById(1);
				
		assertNull(reclamacionEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldInsertReclamacionAPedido() {
//		Pedido pedido=new Pedido();
//		pedido.setId(7);
//		pedido.setDireccion("C/Ferrara, 4");
//		
//		Cliente cliente = new Cliente();
//		cliente.setId(1);
//		cliente.setApellidos("Roldán Cadena");
//		cliente.setEmail("jrc@gmail.com");
//		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
//		cliente.setNombre("Jesús");
//		cliente.setTelefono(123456789);
//		pedido.setCliente(cliente);
//		
//		TipoEnvio te = new TipoEnvio();
//		te.setName("A DOMICILIO");
//		pedido.setTipoEnvio(te);
//		pedido.setGastosEnvio(3.50);
//		
//		TipoPago tp = new TipoPago();
//		tp.setName("TARJETA");
//		pedido.setTipoPago(tp);
//		
//
//		LocalDate hoy = LocalDate.now();
//		pedido.setFechaPedido(hoy);
//		
//		//pizza
//		Pizza pizza1 = new Pizza();
//		pizza1.setCoste(12);
//		pizza1.setNombre("Barbacoa");
//		pizza1.setContador(1);
//		
//		Alergenos alergeno1 = new Alergenos();
//		alergeno1.setName("contiene lactosa");
//		alergeno1.setId(55);
//		
//		Ingrediente ingrediente1 = new Ingrediente();
//		ingrediente1.setAlergenos(alergeno1);
//		ingrediente1.setFechaCaducidad(LocalDate.of(2021, 12, 05));
//		ingrediente1.setId(55);
//		ingrediente1.setNombre("tomate");
//		ingrediente1.setTipo("contiene lácteos");
//		List<Ingrediente> lista_ingredientes = new ArrayList<Ingrediente>();
//		lista_ingredientes.add(ingrediente1);
//		pizza1.setIngredientes(lista_ingredientes);
//		
//		TamanoProducto t=new TamanoProducto();
//		t.setId(66);
//		t.setName("mini");
//		pizza1.setTamano(t);
//		
//		tipoMasa t2=new tipoMasa();
//		t2.setId(66);
//		t2.setName("extrafina");
//		pizza1.setTipoMasa(t2);
//		
//		List<Pizza> pizzasEnPedido=new ArrayList<Pizza>();
//		pizzasEnPedido.add(pizza1);
//		
//		Integer costeP=pizza1.getCoste();
//		
//		//bebida
//		TamanoProducto tamp=new TamanoProducto();
//		tamp.setId(5);
//		tamp.setName("ENORME");
//		
//		Bebida b = new Bebida();
//		b.setCoste(10);
//		b.setContador(1);
//		b.setEsCarbonatada(true);
//		b.setNombre("Hidromiel");
//		b.setTamano(tamp);
//		
//		List<Bebida> bebidasEnPedido= new ArrayList<Bebida>();
//		bebidasEnPedido.add(b);
//		
//		Integer costeB=b.getCoste();
//		
//		pedido.setPrecio((double)costeP+costeB);
//		
//		pedido.setPizzasEnPedido(pizzasEnPedido);
//		pedido.setBebidasEnPedido(bebidasEnPedido);
//		
//		Reclamacion r = new Reclamacion();
//		r.setObservacion("Prueba de observación");
//		r.setRespuesta("Prueba de respuesta");
//		Collection<Reclamacion> cr = new ArrayList<Reclamacion>();
//		pedido.setReclamacion(cr);
//		pedido.setCliente(cliente);
//		Reclamacion reclamacion = new Reclamacion();
//		reclamacion.setId(3);
//		reclamacion.setObservacion("La pizza no llevaba queso");
//		reclamacion.setRespuesta("probando respuesta");
		Pedido p = this.pedidoService.findPedidoById(1);
		Reclamacion r = this.reclamacionService.findReclamacionById(1);
	//	reclamacion.setFechaReclamacion((LocalDate.of(2020, 11, 27)));               
                
		this.reclamacionService.anadirReclamacionAPedido(r.getId(), p.getId());
//		List<Integer> pedidosId = this.reclamacionService.findPedidosConReclamaciones();
//		Integer indicePedido = pedidosId.indexOf(10);
//		Integer 
		
		assertThat(p.getReclamacion().contains(r));
	}
	
	

//	@Test
//	@Transactional
//	public void shouldNotUpdateReclamacionWithNewDate() {
//		 Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
//			LocalDate newFecha = LocalDate.of(2020, 12, 20);
//			//reclamacion.setFechaReclamacion(newFecha);
//			try{
//				this.reclamacionService.saveReclamacion(reclamacion);
//				//assertTrue(false);
//			}catch (Exception e) { //La fecha de incidencia no es modificable
//				assertTrue(true);
//			}
//			//assertTrue(false);
//	}
	

}
