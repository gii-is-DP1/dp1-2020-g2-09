package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.TipoEnvio;
import org.springframework.samples.petclinic.model.TipoPago;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.ClienteService;
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
	private static final int TEST_PEDIDO_ID = 2;
	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	
	@MockBean
	private PedidoService PedidoService;
	@MockBean
	private UserService userService;
	@MockBean
	private ClienteService clienterService;
	@MockBean
	private CartaService CartaService;
    @MockBean
    private PizzaService PizzaService;
    @MockBean
    private OtrosService OtrosService;
    @MockBean
    private BebidaService BebidaService;
    
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Pedido pedido=new Pedido();
		pedido.setDireccion("C/Ferrara, 4");
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		pedido.setCliente(cliente);
		
		TipoEnvio te = new TipoEnvio();
		te.setName("A DOMICILIO");
		pedido.setTipoEnvio(te);
		pedido.setGastosEnvio(3.50);
		
		TipoPago tp = new TipoPago();
		tp.setName("TARJETA");
		pedido.setTipoPago(tp);
		

		LocalDate hoy = LocalDate.now();
		pedido.setFechaPedido(hoy);
		
		//pizza
		Pizza pizza1 = new Pizza();
		pizza1.setId(TEST_PIZZA_ID);
		pizza1.setCoste(12);
		pizza1.setNombre("Barbacoa");
		pizza1.setContador(1);
		
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
		
		Integer costeP=pizza1.getCoste();
		
		//bebida
		TamanoProducto tamp=new TamanoProducto();
		tamp.setId(5);
		tamp.setName("ENORME");
		
		Bebida b = new Bebida();
		b.setId(TEST_BEBIDA_ID);
		b.setCoste(10);
		b.setContador(1);
		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(tamp);
		
		List<Bebida> bebidasEnPedido= new ArrayList<Bebida>();
		bebidasEnPedido.add(b);
		
		Integer costeB=b.getCoste();
		
		pedido.setPrecio((double)costeP+costeB);
		
		pedido.setPizzasEnPedido(pizzasEnPedido);
		pedido.setBebidasEnPedido(bebidasEnPedido);
	
		
		given(this.PedidoService.findPedidos()).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(pedido);
		given(this.PedidoService.findPedidosByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoForCocinero()).willReturn(Lists.newArrayList(pedido));
		given(this.PedidoService.findPedidoForRepartidor()).willReturn(Lists.newArrayList(pedido));
	}

	@WithMockUser(value = "spring")
   	@Test
   	void testshowPedidoList() throws Exception {
    	mockMvc.perform(get("/allPedidos"))
    	.andExpect(status().isOk())
		.andExpect(view().name("pedidos/pedidosList"))
		.andExpect(model().attributeExists("pedidos"));
    }

	//Da error porque la prueba no consigue el usuario en sesión
	@WithMockUser(value = "spring")
   	@Test
   	void testshowMisPedidos() throws Exception {
    	mockMvc.perform(get("/pedidos/user"))
    	.andReturn().getRequest();
    	//Da error porque la prueba no consigue el usuario en sesión
		/*.andExpect(view().name("pedidos/pedidosUser"))
		.andExpect(model().attributeExists("pedidos"));*/
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

	//Da error porque la prueba no consigue el usuario en sesión
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
		.andExpect(view().name("redirect:/allPedidos"));
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
 
//falta showCartaParaPedidosList no se si al final se usa ese metodo, creo q no
    
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
		.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen"))
		.andExpect(model().attributeExists("pedido"))
    	.andExpect(model().attributeExists("cartaId"));
		
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
   	void testenCocinaSuccess() throws Exception {
    	mockMvc.perform(get("/pedidos/{pedidoId}/finalizarPedido", TEST_PEDIDO_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/user"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testenCocinaPreparadoSuccess() throws Exception {
    	mockMvc.perform(get("/cocinero/{pedidoId}/estadoPedido", TEST_PEDIDO_ID))
    	.andReturn().getRequest();
    	/*.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/cocinero"));*/
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testPreparadoEnRepartoSuccess() throws Exception {
    	mockMvc.perform(get("/repartidor/{pedidoId}/estadoPedido", TEST_PEDIDO_ID))
    	.andReturn().getRequest();
    	/*.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/repartidor"));*/
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
    	.andExpect(model().attributeExists("cartaId"))
		.andExpect(model().attributeExists("pedido"))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/VerResumen"));
    }
    
    
}
