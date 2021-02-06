package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Bebida;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.EstadoPedido;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = PedidoController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class,
includeFilters = @ComponentScan.Filter(value = BebidaFormatter.class, type = FilterType.ASSIGNABLE_TYPE)
)
public class PedidoControllerTests {
	
	private static final int TEST_BEBIDA_ID = 1;
	//private static final int TEST_OTROS_ID = 1;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_PEDIDO_ID2=2;
	private static final int TEST_PEDIDO_ID3=3;
	private static final int TEST_PEDIDO_ID4=4;
	private static final int TEST_PEDIDO_ID5=5;

	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final String TEST_user= "spring";
	
	@MockBean
	private PedidoService PedidoService;
	@MockBean
	private UserService userService;
	@MockBean
	private ClienteService clienteService;
	@MockBean
	private CartaService CartaService;
    @MockBean
    private PizzaService PizzaService;
    @MockBean
    private OtrosService OtrosService;
    @MockBean
    private BebidaService BebidaService;
    @MockBean
	private OfertaService ofertaService;
    
    
	@Autowired
	private MockMvc mockMvc;
 
	
	@BeforeEach
	void setup() {
		User u1 = new User();
		Optional<User> op= Optional.of(u1);
		given(this.userService.findUser(TEST_user)).willReturn(op);

		Cliente cliente = new Cliente();
		cliente.setUser(u1);
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		EstadoPedido estd = new EstadoPedido();
		estd.setName("EN COCINA");
		estd.setId(1);
		
		EstadoPedido estd2 = new EstadoPedido();
		estd2.setName("PREPARADO");
		estd2.setId(2);
		
		TipoPago tp = new TipoPago();
		tp.setName("TARJETA");
		
		TipoEnvio te= new TipoEnvio();
		te.setId(1);		
		te.setName("RECOGER EN TIENDA");
		
		TipoEnvio te2 = new TipoEnvio();
		te2.setName("A DOMICILIO");
		te2.setId(2);
		
		Pedido pedido=new Pedido();
		pedido.setId(TEST_PEDIDO_ID);
		pedido.setEstadoPedido(estd2);
		pedido.setDireccion("C/Ferrara, 4");
		pedido.setTipoEnvio(te);
		pedido.setCliente(cliente);
		pedido.setGastosEnvio(3.50);
		pedido.setTipoPago(tp);
		pedido.setPrecio(34.50);

		Pedido pedido2=new Pedido();
		pedido2.setId(TEST_PEDIDO_ID2);
		pedido2.setEstadoPedido(estd2);
		pedido2.setDireccion("C/Ferrara, 4");
		pedido2.setTipoEnvio(te2);
		pedido2.setCliente(cliente);
		pedido2.setGastosEnvio(3.50);
		pedido2.setTipoPago(tp);
		
		Pedido pedido3=new Pedido();
		pedido3.setId(TEST_PEDIDO_ID3);
		pedido3.setEstadoPedido(estd);
		pedido3.setDireccion("C/Ferrara, 4");
		pedido3.setTipoEnvio(te2);
		pedido3.setCliente(cliente);
		pedido3.setGastosEnvio(3.50);
		pedido3.setTipoPago(tp);
		
		Pedido pedido4=new Pedido();
		pedido4.setId(TEST_PEDIDO_ID4);
		pedido4.setEstadoPedido(estd);
		pedido4.setDireccion("C/Ferrara, 4");
		pedido4.setTipoEnvio(te);
		pedido4.setCliente(cliente);
		pedido4.setGastosEnvio(3.50);
		pedido4.setTipoPago(tp);
		
		LocalDate hoy = LocalDate.now();
		pedido.setFechaPedido(hoy);
		pedido2.setFechaPedido(hoy);
		pedido3.setFechaPedido(hoy);
		pedido4.setFechaPedido(hoy);
		
//		doAnswer(new Answer() {
//		    public Object answer(InvocationOnMock invocation) {
//		        Object[] args = invocation.getArguments();
//		        ((Pedido)args[0]).setId(1);
//		        return null; // void method, so return null
//		    }
//		}).when(this.PedidoService).savePedido(any(Pedido.class));
		
//		doAnswer(new Answer() {
//		    public Object answer(InvocationOnMock invocation) {
//		        Object[] args = invocation.getArguments();
//		        ((Carta)args[0]).setId(1);
//		        return null; // void method, so return null
//		    }
//		}).when(this.PedidoService).savePedido(any(Pedido.class));
		
//		doAnswer(new Answer() {
//		    public Object answer(InvocationOnMock invocation) {
//		        Object[] args = invocation.getArguments();
//		        EstadoPedido estd = new EstadoPedido();
//		        estd.setId(2);
//		        TipoEnvio envio = new TipoEnvio();
//		        envio.setId(1);
//		        ((Pedido)args[0]).setTipoEnvio(envio);
//		        ((Pedido)args[1]).setEstadoPedido(estd);
//		        return invocation.getMock(); // void method, so return null
//		    }
//		}).when(this.PedidoService).putRecogido(any());
		
		
		//pizza
		Pizza pizza1 = new Pizza();
		pizza1.setId(TEST_PIZZA_ID);
		pizza1.setCoste(12.0);
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
		List<Ingrediente> lista_ingredientes = new ArrayList<Ingrediente>();
		lista_ingredientes.add(ingrediente1);
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
		
		Double costeP=pizza1.getCoste();
		
		//bebida
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
		
		Double costeB=b.getCoste();
		
		pedido.setPrecio((double)costeP+costeB);
		
		pedido.setPizzasEnPedido(pizzasEnPedido);
		pedido.setBebidasEnPedido(bebidasEnPedido);
		
		
		Carta carta=new Carta();
		carta.setBebidasEnCarta(bebidasEnPedido);
		carta.setFechaCreacion(LocalDate.now().minusDays(30));
		carta.setFechaFinal(LocalDate.now().plusDays(30));
		carta.setId(TEST_CARTA_ID);
		carta.setNombre("cumpleaos");
		carta.setPizzasEnCarta(pizzasEnPedido);
		
		
		given(this.clienteService.findCuentaByUser(u1)).willReturn(cliente);
		given(this.PedidoService.findPedidos()).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedido);
		given(this.PedidoService.findPedidoById(TEST_PEDIDO_ID2)).willReturn(pedido2);
		given(this.PedidoService.findPedidoById(TEST_PEDIDO_ID3)).willReturn(pedido3);
		given(this.PedidoService.findPedidoById(TEST_PEDIDO_ID4)).willReturn(pedido4);
		given(this.PedidoService.findPedidosByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoForCocinero()).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoForRepartidor()).willReturn(Lists.newArrayList(pedido));
		given(this.CartaService.findCartaByFechaCreacionYFechaFinal(hoy)).willReturn(carta);
		
	}
	

	@WithMockUser(value = "spring")
   	@Test
   	void testshowPedidoList() throws Exception {
    	mockMvc.perform(get("/allPedidos"))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/pedidosList"))
		.andExpect(model().attributeExists("pedidos"));
    }

	
	@WithMockUser(value = "spring")
   	@Test
   	void testshowMisPedidos() throws Exception {
    	mockMvc.perform(get("/pedidos/user"))
    	.andExpect(status().isOk())
    	.andExpect(view().name("pedidos/pedidoUser"))
		.andExpect(model().attributeExists("pedidos"));
    }
	
	@WithMockUser(value = "spring")
   	@Test
   	void testshowPedidoCocinero() throws Exception {
    	mockMvc.perform(get("/pedidos/cocinero"))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/pedidosList"))
		.andExpect(model().attributeExists("pedidos"));
    }
	
	
	@WithMockUser(value = "spring")
   	@Test
   	void testshowPedidoReparto() throws Exception {
    	mockMvc.perform(get("/pedidos/repartidor"))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/pedidosList"))
		.andExpect(model().attributeExists("pedidos"));
    }
	
	
	@WithMockUser(value = "spring")
        @Test
	void testinitCreationForm() throws Exception {
		mockMvc.perform(get("/pedidos/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("pedidos/createOrUpdatePedidoForm"))
				.andExpect(model().attributeExists("pedido"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testprocessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/new")
							.with(csrf())
							.param("direccion", "C/ferrara, 4")
							.param("tipoPago.name", "TARJETA")
							.param("tipoEnvio.name", "DOMICILIO"))

				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/user"));
			
	}

	//PEDIDO ESTA VALIDADO??
	/*@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/new")
					.with(csrf())
					.param("direccion", "")
					.param("tipoPago.name", "TARJETA")
					.param("tipoEnvio.name", "DOMICILIO"))
		.andExpect(model().attributeHasErrors())			
		.andExpect(view().name("cartas/createOrUpdateCartaForm"));
	}*/

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/pedidos/{pedidoId}/edit", TEST_PEDIDO_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("pedidos/createOrUpdatePedidoForm"))
				.andExpect(model().attributeExists("pedido"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdatePeFormSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/edit", TEST_PEDIDO_ID)
				.with(csrf())
				.param("direccion", "C/ferrara, 3")
				.param("tipoPago.name", "EFECTIVO")
				.param("tipoEnvio.name", "DOMICILIO"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/user"));
	}
    
    /* NO SE SI PEDIDO ESTA VALIDADO
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateOtrosFormHasErrors() throws Exception {
		mockMvc.perform(post("/cartas/{cartaId}/edit", TEST_CARTA_ID)
				.with(csrf())
				.param("nombre", "d")
				.param("fecha", "d"))
		.andExpect(model().attributeHasErrors())			
		.andExpect(view().name("cartas/createOrUpdateCartaForm"));
    }*/
    
    @WithMockUser(value = "spring")
   	@Test
   	void testinitDeletePedidoSuccess() throws Exception {       
    	mockMvc.perform(get("/pedidos/{pedidoId}/delete", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/pedidos/user"));
    }
 
    @WithMockUser(value = "spring")
   	@Test
   	void showCartaParaPedidosList() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/allCartas", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/"+TEST_CARTA_ID+"/verCarta"));

    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testverCartaPedido() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/verCarta", TEST_PEDIDO_ID, TEST_CARTA_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/verCartaParaPedido"))
		.andExpect(model().attributeExists("cartaId"))
		.andExpect(model().attributeExists("pedido"));
		/*.andExpect(model().attributeExists("pizzas"))
		.andExpect(model().attributeExists("bebidas"))
		.andExpect(model().attributeExists("otros"))
		.andExpect(model().attributeExists("PizzasP"))*/	
		
    }
    
    //anadir Pizza es igual q otros y q bebidas
    @WithMockUser(value = "spring")
   	@Test
   	void testanadirPizza() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/verCarta/anadirPizza/{pizzaId}", TEST_PEDIDO_ID, TEST_CARTA_ID, TEST_PIZZA_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen"));
		//.andExpect(model().attributeExists("pedido"))
    	//.andExpect(model().attributeExists("cartaId"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testverResumenPedido() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen", TEST_PEDIDO_ID, TEST_CARTA_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/resumenPedido"))
		.andExpect(model().attributeExists("cartaId"))
		.andExpect(model().attributeExists("pedido"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaSuccessIfCaso() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/finalizarPedido", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/user"));
    	
    }


    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaPreparadoSuccessCasoIf1() throws Exception {
    	mockMvc.perform(get("/cocinero/{pedidoId}/estadoPedido", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/cocinero"));
    	
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaPreparadoSuccessCasoIf2() throws Exception {
    	mockMvc.perform(get("/cocinero/{pedidoId}/estadoPedido", TEST_PEDIDO_ID2))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/cocinero"));
    	
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaPreparadoSuccessCasoIf3() throws Exception {
    	mockMvc.perform(get("/cocinero/{pedidoId}/estadoPedido", TEST_PEDIDO_ID3))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/cocinero"));
    	
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaPreparadoSuccessCasoIf4() throws Exception {
    	mockMvc.perform(get("/cocinero/{pedidoId}/estadoPedido", TEST_PEDIDO_ID4))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/cocinero"));
    	
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testPreparadoEnRepartoSuccessCasoIf1() throws Exception {
    	mockMvc.perform(get("/repartidor/{pedidoId}/estadoPedido", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/repartidor"));
    }
    @WithMockUser(value = "spring")
   	@Test
   	void testPreparadoEnRepartoSuccessCasoIf2() throws Exception {
    	mockMvc.perform(get("/repartidor/{pedidoId}/estadoPedido", TEST_PEDIDO_ID3))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/repartidor"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testverPedidoSuccess() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/VerPedido", TEST_PEDIDO_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/resumenPedido"))
		.andExpect(model().attributeExists("pedido"));
    }
    
  //borrar Pizza es igual q otros y q bebidas
    @WithMockUser(value = "spring")
   	@Test
   	void testeliminarPizza() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/borrarP", TEST_PEDIDO_ID, TEST_CARTA_ID, TEST_PIZZA_ID))
    	.andExpect(status().isOk())
//    	.andExpect(model().attributeExists("cartaId"))
//		.andExpect(model().attributeExists("pedido"))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen"));
		
    }
    
    
}
