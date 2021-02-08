package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
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
                
		this.reclamacionService.saveReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.
				findReclamacionById(reclamacion.getId());
		
		assertThat(reclamacion).isEqualTo(reclamacionEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertReclamacionShortObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setId(100);
		reclamacion.setObservacion("a");
		this.reclamacionService.saveReclamacion(reclamacion);
		
		Reclamacion reclamacionEncontrada = this.reclamacionService.findReclamacionById(100);
		assertNull(reclamacionEncontrada);
		
	}
	
	@Test
	@Transactional
	public void shouldNotInsertReclamacionNullObservacion() {
		Reclamacion reclamacion = new Reclamacion();
		reclamacion.setId(100);
		this.reclamacionService.saveReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.findReclamacionById(100);
		assertNull(reclamacionEncontrada);
	}

	
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
	void shouldDeleteReclamacion() {
		Reclamacion reclamacion = this.reclamacionService.findReclamacionById(1);
		this.reclamacionService.deleteReclamacion(reclamacion);
		Reclamacion reclamacionEncontrada = this.reclamacionService.findReclamacionById(1);
		assertNull(reclamacionEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldInsertReclamacionAPedido() {
		Pedido p = this.pedidoService.findPedidoById(1);
		Reclamacion r = this.reclamacionService.findReclamacionById(1);      
		this.reclamacionService.anadirReclamacionAPedido(r.getId(), p.getId());
		assertThat(p.getReclamacion().contains(r));
	}
	
	

}
