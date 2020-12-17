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
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Ingrediente;
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
	

	@Autowired
	private PizzaController pizzaController;


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
		
		Ingrediente ingrediente1 = new Ingrediente();
		Alergenos alergeno1 = new Alergenos();
		
		alergeno1.setName("contiene lactosa");
		alergeno1.setId(55);
		
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
		
		given(this.pizzaService.findPizzas()).willReturn(Lists.newArrayList(pizza1));
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(new Pizza());
		given(this.pizzaService.findIdPizzaById(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
		
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.cartaService.findCartaById(TEST_CARTA_ID)).willReturn(new Carta());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(new Cliente());
		given(this.ingredienteService.findIngredienteById(TEST_OFERTA_ID)).willReturn(new Ingrediente());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/pizzas/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"))
				.andExpect(model().attributeExists("pizza"));
		
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pizzas/new")
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allPizzas"));
		

	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pizzas/{pizzaId}/new", TEST_PIZZA_ID)
							.with(csrf())
							.param("contador", "ññññ")
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano", "mini")
							.param("tipoMasa", "extrafina")
							.param("ingredientes", "tomate"))

				.andExpect(model().attributeHasErrors("pizza"))
				.andExpect(status().isOk())
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/pizzas/{pizzaId}/edit", TEST_PIZZA_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("pizza"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdatePizzaFormSuccess() throws Exception {
		mockMvc.perform(post("/pizzas/{pizzaId}/edit", TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina")
				.param("ingredientes", "tomate"))

	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allPizzas"));

	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdatePizzaFormHasErrors() throws Exception {
		mockMvc.perform(post("/pizzas/{pizzaId}/edit", TEST_PIZZA_ID)
				.with(csrf())
				.param("contador", "ññññ")
				.param("coste", "lllll")
				.param("nombre", "Pizza2")
				.param("tamano", "mini")
				.param("tipoMasa", "extrafina")
				.param("ingredientes", "tomate"))

	.andExpect(status().isOk())
	.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));

	}
}