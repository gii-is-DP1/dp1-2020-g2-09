package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.samples.petclinic.model.User;
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


@WebMvcTest(value = InformeController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

public class InformeControllerTests {
	
	private static final int TEST_INFORME_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_USER_ID = 1;
	private static final int TEST_MESA_ID = 1;
	private static final int TEST_INGREDIENTE_ID = 1;

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
		
		
		//pizza
		Pizza pizza1 = new Pizza();
		pizza1.setId(TEST_PIZZA_ID);
		pizza1.setCoste(12);
		pizza1.setNombre("Barbacoa");
		
		Alergenos alergeno1 = new Alergenos();
		alergeno1.setName("contiene lactosa");
		alergeno1.setId(55);
		
		Ingrediente ingrediente1 = new Ingrediente();
		ingrediente1.setAlergenos(alergeno1);
		ingrediente1.setFechaCaducidad(LocalDate.of(2021, 12, 05));
		ingrediente1.setId(55);
		ingrediente1.setNombre("tomate");
		ingrediente1.setTipo("contiene lácteos");
		
		Ingrediente ingrediente2 = new Ingrediente();
		ingrediente2.setAlergenos(alergeno1);
		ingrediente2.setFechaCaducidad(LocalDate.of(2021, 12, 05));
		ingrediente2.setId(55);
		ingrediente2.setNombre("tomate");
		ingrediente2.setTipo("contiene lácteos");
		List<Ingrediente> lista_ingredientes = new ArrayList<Ingrediente>();
		lista_ingredientes.add(ingrediente1);
		lista_ingredientes.add(ingrediente2);
		pizza1.setIngredientes(lista_ingredientes);
		
		TamanoProducto t=new TamanoProducto();
		t.setId(66);
		t.setName("mini");
		pizza1.setTamano(t);
		
		tipoMasa t2=new tipoMasa();
		t2.setId(66);
		t2.setName("extrafina");
		pizza1.setTipoMasa(t2);
		
		List<Pizza> pizzasEnPedido=new ArrayList<Pizza>();
		pizzasEnPedido.add(pizza1);
		
		Integer costeP=pizza1.getCoste();
		
		//bebida
		TamanoProducto tamp=new TamanoProducto();
		tamp.setId(5);
		tamp.setName("ENORME");
		
		Bebida b = new Bebida();
		b.setId(TEST_BEBIDA_ID);
		b.setCoste(10);
		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(tamp);
		
		List<Bebida> bebidasEnPedido= new ArrayList<Bebida>();
		bebidasEnPedido.add(b);
		
		Integer costeB=b.getCoste();
		
		pedido.setPrecio((double)costeP+costeB);
		
		pedido.setPizzasEnPedido(pizzasEnPedido);
		pedido.setBebidasEnPedido(bebidasEnPedido);
		
		Mesa mesa1 = new Mesa();
		Mesa mesa2 = new Mesa();
		List<Mesa> listaMesas = new ArrayList<>();
		listaMesas.add(mesa1);
		listaMesas.add(mesa2);
	
		given(this.pedidoService.findPedidos()).willReturn(Lists.newArrayList(pedido));
		given(this.pedidoService.findPedidos()).willReturn(new ArrayList<>());
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.pedidoService.findPedidosByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(pedido));
		given(this.pedidoService.findPedidoForCocinero()).willReturn(Lists.newArrayList(pedido));
		given(this.pedidoService.findPedidoForRepartidor()).willReturn(Lists.newArrayList(pedido));
		given(this.mesaService.findMesas()).willReturn(listaMesas);
		given(this.mesaService.CountMesa(TEST_MESA_ID)).willReturn(0);
		given(this.ingredienteService.findIngredientes()).willReturn(lista_ingredientes);
		given(this.ingredienteService.CountIngrediente(Mockito.anyInt())).willReturn(0,10);
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