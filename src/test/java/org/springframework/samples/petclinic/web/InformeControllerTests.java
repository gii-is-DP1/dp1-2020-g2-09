package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;


@WebMvcTest(value = InformeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

@Slf4j
public class InformeControllerTests {
	
	private static final int TEST_INFORME_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_MESA_ID = 1;

	@Autowired
	private InformeController informeController;

	
	@MockBean
	private BebidaService bebidaService;
	@MockBean
	private MesaService mesaService;
	@MockBean
	private IngredienteService ingredienteService;
	@MockBean
	private  PizzaService pizzaService;
	@MockBean
	private  PedidoService pedidoService;
	
	@MockBean
	private  OfertaService ofertaService;
	
	@MockBean
	private  ClienteService clienteService;
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setup() {
		Oferta o = new Oferta();
		o.setCoste(20.0);
		o.setFechaInicial(LocalDate.of(2021, 11, 10));
		o.setFechaFinal(LocalDate.of(2021, 11, 22));
		o.setEstadoOferta(true);
		o.setId(1);
		
		NivelSocio ns = new NivelSocio();
		ns.setId(2);
		ns.setName("ORO");
		o.setNivelSocio(ns);
		
		TamanoOferta to = new TamanoOferta();
		to.setId(2);
		to.setName("GRANDE");
		o.setTamanoOferta(to);
		given(this.ofertaService.findOfertas()).willReturn(Lists.newArrayList(o));
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(new Pizza());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		
		
	
		Pedido pedido=new Pedido();
		pedido.setDireccion("C/Ferrara, 4");
		
		
		EstadoPedido estd = new EstadoPedido();
		estd.setName("EN COCINA");
		
		Pedido pedido2=new Pedido();
		pedido2.setId(TEST_PEDIDO_ID);
		pedido2.setEstadoPedido(estd);
		pedido2.setDireccion("C/Ferrara, 4");
		
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		pedido.setCliente(cliente);
		pedido2.setCliente(cliente);
		
		
		TipoEnvio te = new TipoEnvio();
		te.setName("A DOMICILIO");
		pedido.setTipoEnvio(te);
		pedido.setGastosEnvio(3.50);
		pedido2.setGastosEnvio(3.50);
		
		TipoPago tp = new TipoPago();
		tp.setName("TARJETA");
		pedido.setTipoPago(tp);
		pedido2.setTipoPago(tp);
		

		LocalDate hoy = LocalDate.now();
		pedido.setFechaPedido(hoy);
		pedido2.setFechaPedido(hoy);
		
		Pizza pizza2 = new Pizza();
		pizza2.setId(1);
		pizza2.setCoste(12.0);
		pizza2.setNombre("Barbacoa");
		
		Collection<Oferta> ofertasEnPedido= new ArrayList<>();
		List<Pizza> pizzasEnOferta= new ArrayList<>();
		o.setPizzasEnOferta(pizzasEnOferta);
		ofertasEnPedido.add(o);
		pedido.setOfertasEnPedido(ofertasEnPedido);
		
		//Creación de pizzas
		List<Pizza> pizzasEnPedido=new ArrayList<Pizza>();
		for(int i=0;i<10;i++) {
			Pizza pizza1 = new Pizza();
			pizza1.setId(i);
			pizza1.setCoste(12.0);
			pizza1.setNombre("Barbacoa");
			TamanoProducto t=new TamanoProducto();
			t.setId(i);
			t.setName("mini");
			pizza1.setTamano(t);
			tipoMasa t2=new tipoMasa();
			t2.setId(i);
			t2.setName("extrafina");
			pizza1.setTipoMasa(t2);
			pizzasEnPedido.add(pizza1);
			pizzasEnPedido.add(pizza1);	
			pizzasEnOferta.add(pizza1);

		}
		
		Pizza pizza12 = new Pizza();
		pizza12.setId(12);
		pizza12.setCoste(12.0);
		pizza12.setNombre("Barbacoa");
		TamanoProducto t2=new TamanoProducto();
		t2.setId(12);
		t2.setName("mini");
		pizza12.setTamano(t2);
		tipoMasa t22=new tipoMasa();
		t22.setId(12);
		t22.setName("extrafina");
		pizza12.setTipoMasa(t22);
		pizzasEnOferta.add(pizza12);

		Alergenos alergeno1 = new Alergenos();
		alergeno1.setName("contiene lactosa");
		alergeno1.setId(55);
		
		List<Ingrediente> lista_ingredientes = new ArrayList<Ingrediente>();
		for(int i=0; i<=15; i++) {
			Ingrediente ingrediente1 = new Ingrediente();
			ingrediente1.setAlergenos(alergeno1);
			ingrediente1.setFechaCaducidad(LocalDate.of(2021, 12, 05));
			ingrediente1.setId(i);
			ingrediente1.setNombre("tomate"+String.valueOf(i));
			ingrediente1.setTipo("contiene lácteos");
			lista_ingredientes.add(ingrediente1);
		}
		for(Pizza p: pizzasEnPedido) {
			p.setIngredientes(lista_ingredientes);
		}

		
		
		TamanoProducto tamp=new TamanoProducto();
		tamp.setId(5);
		tamp.setName("ENORME");
		
		Bebida b = new Bebida();
		b.setId(TEST_BEBIDA_ID);
		b.setCoste(10.0);
		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(tamp);
		
		List<Bebida> bebidasEnPedido= new ArrayList<Bebida>();
		bebidasEnPedido.add(b);
		
		
		pedido.setPrecio((double)0);
		
		pedido.setPizzasEnPedido(pizzasEnPedido);
		pedido.setBebidasEnPedido(bebidasEnPedido);
		
		Mesa mesa1 = new Mesa();
		Mesa mesa2 = new Mesa();
		List<Mesa> listaMesas = new ArrayList<>();
		listaMesas.add(mesa1);
		listaMesas.add(mesa2);
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(pedido);
	
		given(this.pedidoService.findPedidos()).willReturn(pedidos);
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.pedidoService.findPedidosByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(pedido));
		given(this.pedidoService.findPedidoForCocinero()).willReturn(Lists.newArrayList(pedido));
		given(this.pedidoService.findPedidoForRepartidor()).willReturn(Lists.newArrayList(pedido));
		given(this.mesaService.findMesas()).willReturn(listaMesas);
		given(this.mesaService.CountMesa(TEST_MESA_ID)).willReturn(0);
		given(this.ingredienteService.findIngredientes()).willReturn(lista_ingredientes);
		given(this.ingredienteService.CountIngrediente(Mockito.anyInt())).willReturn(-1,0,1,2,3,4,5,6,7,8,9,10,11,14,1000);
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitList() throws Exception {
		mockMvc.perform(get("/informe"))
				.andExpect(status().isOk())
				.andExpect(view().name("informe/InformeList"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testInformeIngredientesMasUsados() throws Exception {
		mockMvc.perform(get("/informe/IngredientesMasUsados"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mapa"))
				.andExpect(view().name("informe/InformeIngredientesMasUsados"));
	}

	@WithMockUser(value = "spring")
    @Test
    void testInformeMesasMasUsadas() throws Exception {
		mockMvc.perform(get("/informe/MesasMasUsadas"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("mapa"))
		.andExpect(view().name("informe/InformeMesasMasUsadas"));

	}
	@WithMockUser(value = "spring")
    @Test
	void testInformeCaducidadIngredientes() throws Exception {
		mockMvc.perform(get("/informe/CaducidadIngredientes"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mapa"))
				.andExpect(view().name("informe/CaducidadIngredientes"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testInformePizzasMasPedidos() throws Exception {
		mockMvc.perform(get("/informe/PizzasMasPedidos"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("mapa"))
				.andExpect(view().name("informe/InformePizzasMasPedidas"));
	}

	

}