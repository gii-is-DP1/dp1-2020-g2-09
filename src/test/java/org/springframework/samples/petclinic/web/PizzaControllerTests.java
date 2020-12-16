package org.springframework.samples.petclinic.web;

import static org.hamcrest.xml.HasXPath.hasXPath;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = PizzaController.class,
includeFilters = @ComponentScan.Filter(value = PizzaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

class PizzaControllerTests {
	
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	private static final int TEST_INGREDIENTE_ID = 1;
	

	@Autowired
	private PizzaController otrosController;


	@MockBean
	private PizzaService pizzaService;
    @MockBean
	private PedidoService pedidoService;
    @MockBean
    private CartaService cartaService;
    @MockBean
    private OfertaService ofertaService;
    @MockBean
    private ClienteService clienteService;
    @MockBean
    private IngredienteService ingredienteService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Pizza pizza1 = new Pizza();
		pizza1.setId(3);
		pizza1.setCoste(12);
		pizza1.setNombre("Barbacoa");
		pizza1.setContador(1);
		//pizza1.setIngredientes(ingredientes);
		
		TamanoProducto t=new TamanoProducto();
		t.setId(66);
		t.setName("mini");
		pizza1.setTamano(t);
		
		tipoMasa t2=new tipoMasa();
		t2.setId(66);
		t2.setName("extrafina");
		pizza1.setTipoMasa(t2);
		
		given(this.pizzaService.findPizzas()).willReturn(Lists.newArrayList(pizza1));
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(new Pizza());
		//given(this.pizzaService.findIdPizzaById(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
		
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.cartaService.findCartaById(TEST_CARTA_ID)).willReturn(new Carta());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(new Cliente());
		//given(this.ingredienteService.findIngredienteById(TEST_OFERTA_ID)).willReturn(new Ingrediente());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/ofertas/{ofertaId}/ofertas/new", TEST_OFERTA_ID)).andExpect(status().isOk())
				.andExpect(view().name("ofertas/createOrUpdateOfertaForm")).andExpect(model().attributeExists("Pizza"));
		
		mockMvc.perform(get("/pedidos/{pedidoId}/pedidos/new", TEST_PEDIDO_ID)).andExpect(status().isOk())
				.andExpect(view().name("pedidos/createOrUpdatePedidoForm")).andExpect(model().attributeExists("Pizza"));
		
		mockMvc.perform(get("/cartas/{cartaId}/cartas/new", TEST_CARTA_ID)).andExpect(status().isOk())
				.andExpect(view().name("cartas/createOrUpdateCartaForm")).andExpect(model().attributeExists("Pizza"));
		
		mockMvc.perform(get("/clientes/{clienteId}/clientes/new", TEST_CLIENTE_ID)).andExpect(status().isOk())
				.andExpect(view().name("clientes/createOrUpdateClienteForm")).andExpect(model().attributeExists("Pizza"));

		/*mockMvc.perform(get("/ingredientes/{ingredienteId}/ingredientes/new", TEST_INGREDIENTE_ID)).andExpect(status().isOk())
				.andExpect(view().name("ingredientes/createOrUpdateIngredienteForm")).andExpect(model().attributeExists("Pizza"));*/
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/pizzas/new", TEST_OFERTA_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/ofertas/{ofertaId}"));
		
		mockMvc.perform(post("/pedidos/{pedidoId}/pizzas/new", TEST_PEDIDO_ID)
						.with(csrf())
						.param("contador", "2")
						.param("coste", "13")
						.param("nombre", "Pizza2")
						.param("tamano", "mini")
						.param("tipoMasa", "extrafina"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
		
		mockMvc.perform(post("/cartas/{cartaId}/pizzas/new", TEST_CARTA_ID)
						.with(csrf())
						.param("contador", "2")
						.param("coste", "13")
						.param("nombre", "Pizza2")
						.param("tamano", "mini")
						.param("tipoMasa", "extrafina"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}"));
		
		mockMvc.perform(post("/cuentas/{cuentaId}/pizzas/new", TEST_CLIENTE_ID)
					.with(csrf())
					.param("contador", "2")
					.param("coste", "13")
					.param("nombre", "Pizza2")
					.param("tamano", "mini")
					.param("tipoMasa", "extrafina"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cuentas/{cuentaId}"));

		/*mockMvc.perform(post("/ingredientes/{ingredienteId}/pizzas/new", TEST_INGREDIENTE_ID)
					.with(csrf())
					.param("contador", "2")
					.param("coste", "13")
					.param("nombre", "Pizza2")
					.param("tamano", "mini")
					.param("tipoMasa", "extrafina"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/ingredientes/{ingredienteId}"));*/
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/pizzas/{pizzaId}/edit", TEST_OFERTA_ID, TEST_PIZZA_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina"))
				.andExpect(model().attributeHasNoErrors("oferta"))
				.andExpect(model().attributeHasErrors("Pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/pedidos/{pedidoId}/pizzas/{pizzaId}/edit", TEST_PEDIDO_ID, TEST_PIZZA_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina"))
				.andExpect(model().attributeHasNoErrors("pedido"))
				.andExpect(model().attributeHasErrors("Pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/cartas/{cartaId}/pizzas/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina"))
				.andExpect(model().attributeHasNoErrors("carta"))
				.andExpect(model().attributeHasErrors("Pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/cuentas/{cuentaId}/pizzas/{pizzaId}/edit", TEST_CLIENTE_ID, TEST_PIZZA_ID)
					.with(csrf())
					.param("contador", "2")
					.param("coste", "13")
					.param("nombre", "Pizza2")
					.param("tamano", "mini")
					.param("tipoMasa", "extrafina"))
			.andExpect(model().attributeHasNoErrors("cliente")) //cliente o cuenta?
			.andExpect(model().attributeHasErrors("Pizza"))
			.andExpect(status().isOk())
			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		/*mockMvc.perform(post("/ingredientes/{ingredienteId}/pizzas/{pizzaId}/edit", TEST_INGREDIENTE_ID, TEST_PIZZA_ID)
					.with(csrf())
					.param("contador", "2")
					.param("coste", "13")
					.param("nombre", "Pizza2")
					.param("tamano", "mini")
					.param("tipoMasa", "extrafina"))
			.andExpect(model().attributeHasNoErrors("ingrediente"))
			.andExpect(model().attributeHasErrors("Pizza"))
			.andExpect(status().isOk())
			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));*/
		
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/ofertas/{ofertaId}/pizzas/{pizzaId}/edit", TEST_OFERTA_ID, TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(get("/pedidos/{pedidoId}/pizzas/{pizzaId}/edit", TEST_PEDIDO_ID, TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(get("/cartas/{cartaId}/pizzas/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(get("/cuentas/{cuentaId}/pizzas/{pizzaId}/edit", TEST_CLIENTE_ID, TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));

		/*mockMvc.perform(get("/ingredientes/{ingredienteId}/pizzas/{pizzaId}/edit", TEST_INGREDIENTE_ID, TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));*/
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/pizzas/{pizzaId}/edit", TEST_OFERTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/ofertas/{ofertaId}"));

		mockMvc.perform(post("/pedidos/{pedidoId}/pizzas/{pizzaId}/edit", TEST_PEDIDO_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
		
		mockMvc.perform(post("/cartas/{cartaId}/pizzas/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/cartas/{cartaId}"));
		
		mockMvc.perform(post("/cuentas/{cuentaId}/pizzas/{pizzaId}/edit", TEST_OFERTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/cuentas/{cuentaId}"));

		/*mockMvc.perform(post("/ingredientes/{ingredienteId}/pizzas/{pizzaId}/edit", TEST_PEDIDO_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/ingredientes/{ingredienteId}"));*/
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/ofertas/{ofertaId}/pizzas/{pizzaId}/edit", TEST_OFERTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(model().attributeHasNoErrors("oferta"))
	.andExpect(model().attributeHasErrors("Pizza")).andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/pedidos/{pedidoId}/pizzas/{pizzaId}/edit", TEST_PEDIDO_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(model().attributeHasNoErrors("pedido"))
	.andExpect(model().attributeHasErrors("Pizza")).andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/cartas/{cartaId}/pizzas/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(model().attributeHasNoErrors("carta"))
	.andExpect(model().attributeHasErrors("Pizza")).andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		mockMvc.perform(post("/cuentas/{cuentaId}/pizzas/{pizzaId}/edit", TEST_CLIENTE_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(model().attributeHasNoErrors("cliente")) //cliente o cuenta
	.andExpect(model().attributeHasErrors("Pizza")).andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
		/*mockMvc.perform(post("/ingredientes/{ingredienteId}/pizzas/{pizzaId}/edit", TEST_INGREDIENTE_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina"))
	.andExpect(model().attributeHasNoErrors("ingrediente"))
	.andExpect(model().attributeHasErrors("Pizza")).andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));*/
	}
    
    
	@WithMockUser(value = "spring")
    @Test
void testShowPizzaListXml() throws Exception {
	mockMvc.perform(get("/pizzas.xml").accept(MediaType.APPLICATION_XML)).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
			.andExpect(content().node(hasXPath("/pizzas/pizzasList[id=3]/id")));
}
}
