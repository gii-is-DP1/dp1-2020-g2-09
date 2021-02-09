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
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class CartaServiceTestsSinMockito {
	@Autowired
	protected CartaService cartaService;
	
	@Test
	@Transactional
	public void shouldInsertCarta() {
		TamanoProducto tamaño=new TamanoProducto();
		tamaño.setName("Mucho");
		
		Bebida bebida= new Bebida();
		bebida.setEsCarbonatada(true);
		bebida.setCoste(20.0);
		bebida.setNombre("cocacola");
		bebida.setTamano(tamaño);
		
		tipoMasa tm=new tipoMasa();
		tm.setName("Fina");
		
		Pizza pizza= new Pizza();
		pizza.setCoste(20.0);
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
		Collection<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			ingredientes.add(i1);
			ingredientes.add(i2);
		
		Otro otro=new Otro();
		otro.setCoste(20.0);
		otro.setNombre("pollo");
		otro.setIngredientes(ingredientes);
		
		Carta carta = new Carta();
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2020, 4, 10));
		
		Collection<Bebida> cartaDeBebidas = new HashSet<Bebida>();
		cartaDeBebidas.add(bebida);
		carta.setBebidasEnCarta(cartaDeBebidas);
		
		Collection<Pizza> cartaDePizzas=new HashSet<Pizza>();
		cartaDePizzas.add(pizza);
		carta.setPizzasEnCarta(cartaDePizzas);
		
		Collection<Otro> cartaDeOtros=new HashSet<Otro>();
		cartaDeOtros.add(otro);
		carta.setOtrosEnCarta(cartaDeOtros);
	        
                
		this.cartaService.saveCarta(carta);
		Carta cartaEncontrada = this.cartaService
				.findCartaById(carta.getId());
		assertThat(carta).isEqualTo(cartaEncontrada);
	}
	
	@Test
	@Transactional
	public void shouldNotInsertCartaNullNombre() {
		TamanoProducto tamaño=new TamanoProducto();
		tamaño.setName("Mucho");
		
		Bebida bebida= new Bebida();
		bebida.setEsCarbonatada(true);
		bebida.setCoste(20.0);
		bebida.setNombre("cocacola");
		bebida.setTamano(tamaño);
		
		tipoMasa tm=new tipoMasa();
		tm.setName("Fina");
		
		Pizza pizza= new Pizza();
		pizza.setCoste(20.0);
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
		Collection<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			ingredientes.add(i1);
			ingredientes.add(i2);
		
		Otro otro=new Otro();
		otro.setCoste(20.0);
		otro.setNombre("pollo");
		otro.setIngredientes(ingredientes);
		
		Carta carta = new Carta();
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2020, 4, 10));
		
		Collection<Bebida> cartaDeBebidas = new HashSet<Bebida>();
		cartaDeBebidas.add(bebida);
		carta.setBebidasEnCarta(cartaDeBebidas);
		
		Collection<Pizza> cartaDePizzas=new HashSet<Pizza>();
		cartaDePizzas.add(pizza);
		carta.setPizzasEnCarta(cartaDePizzas);
		
		Collection<Otro> cartaDeOtros=new HashSet<Otro>();
		cartaDeOtros.add(otro);
		carta.setOtrosEnCarta(cartaDeOtros);
	        
		carta.setId(1000);
                
		this.cartaService.saveCarta(carta);
		Carta cartaEncontrada = this.cartaService
				.findCartaById(1000);
		assertNull(cartaEncontrada);
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
	
	
	@Test
	@Transactional
	void shouldDeleteCarta() {
		Carta carta = this.cartaService.findCartaById(1);
		
		this.cartaService.deleteCarta(carta);
		Carta cartaEncontrada = this.cartaService.findCartaById(1);
				
		assertNull(cartaEncontrada);
	}

}
