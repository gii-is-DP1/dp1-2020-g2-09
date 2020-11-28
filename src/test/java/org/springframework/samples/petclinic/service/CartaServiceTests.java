package org.springframework.samples.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Administrador;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CartaServiceTests {
	@Autowired
	protected CartaService cartaService;
	
	@Test
	@Transactional
	public void shouldInsertCarta() {//Son null todos los Collection<> dentro de cada Collection<> en pizza bebidas y otros, NO VALIDAR QUE NO SEAN NULL
		TamanoProducto tamaño=new TamanoProducto();
		tamaño.setName("Mucho");
		
		Bebida bebida= new Bebida();
		bebida.setEsCarbonatada(true);
		bebida.setContador(0);
		bebida.setCoste(20);
		bebida.setNombre("cocacola");
		bebida.setTamano(tamaño);
		
		tipoMasa tm=new tipoMasa();
		tm.setName("Fina");
		
		Pizza pizza= new Pizza();
		pizza.setContador(0);
		pizza.setCoste(20);
		pizza.setNombre("Carbonara");
		pizza.setTipoMasa(tm);
		pizza.setTamano(tamaño);
		
		Alergenos a1= new Alergenos();
		a1.setName("Lactosa");
		Alergenos a2= new Alergenos();
		a1.setName("Vegetal");
		
		
		Ingrediente i1= new Ingrediente();
		i1.setFechaCaducidad(LocalDate.of(2020,2,29));
		i1.setNombre("tomate");
		i1.setTipo("verdura");
		i1.setAlergenos(a2);
		Ingrediente i2= new Ingrediente();
		i1.setFechaCaducidad(LocalDate.of(2019,5,9));
		i1.setNombre("queso");
		i1.setTipo("lacteo");
		i1.setAlergenos(a1);
		Ingrediente[] ingredientes = {i1,i2};
		
		Otros otro=new Otros();
		otro.setContador(0);
		otro.setCoste(20);
		otro.setNombre("pollo");
		otro.setIngredientes(ingredientes);
		
		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFecha(LocalDate.of(2020, 2, 2));
		
		Collection<Bebida> cartaDeBebidas = new HashSet<Bebida>();
		cartaDeBebidas.add(bebida);
		carta.setCartaDeBebidas(cartaDeBebidas);
		
		Collection<Pizza> cartaDePizzas=new HashSet<Pizza>();
		cartaDePizzas.add(pizza);
		carta.setCartaDePizzas(cartaDePizzas);
		
		Collection<Otros> cartaDeOtros=new HashSet<Otros>();
		cartaDeOtros.add(otro);
		carta.setCartaDeOtros(cartaDeOtros);
	        
                
		this.cartaService.saveCarta(carta);
		Carta cartaEncontrada = this.cartaService
				.findCartaById(carta.getId());
		assertThat(carta).isEqualTo(cartaEncontrada);
	}
	
	@Test
	@Transactional
	void shouldUpdateCarta() {
		Carta carta = this.cartaService.findCartaById(1);
		String oldNombre = carta.getNombre();
		String newNombre = oldNombre+"Yeah";
		
		carta.setNombre(newNombre);
		this.cartaService.saveCarta(carta);
		
		carta = this.cartaService.findCartaById(1);
		assertThat(carta.getNombre()).isEqualTo(newNombre);
		
	}
	
//	@Test
//	@Transactional
//	void shouldNotUpdateCarta() {
//		Carta carta = this.cartaService.findCartaById(1);
//		String oldNombre = carta.getNombre();
//		String newNombre = oldNombre+"Yeaaaah";
//		
//		carta.setNombre(newNombre);
//		try{
//			this.cartaService.saveCarta(carta);
//			//assertTrue(false);
//		}catch (Exception e) {
//			assertTrue(true);
//		}
//		//assertTrue(false);
//	}
	
	@Test
	@Transactional
	void shouldDeleteCarta() {
		Carta carta = this.cartaService.findCartaById(1);
		
		this.cartaService.deleteCarta(carta);
				
		assertNull(carta);
	}
//	
//	@Test
//	@Transactional
//	void shouldFindCartaByFecha() {
//		Carta carta=this.cartaService.findCartaById(1);	
//		
//		LocalDate cartaFecha= LocalDate.of(2020,04,30);//esta fecha debe ser la misma que la de la carta con id=1
//        this.cartaService.saveCarta(carta);
//		Carta cartaEncontrada = this.cartaService.findCartaByFecha(cartaFecha);
//
//		assertThat(carta).isEqualTo(cartaEncontrada);
//	}
}
