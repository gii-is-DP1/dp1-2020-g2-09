package org.springframework.samples.petclinic.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = CartaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

class CartaControllerTests {
	
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_PIZZA_ID = 1;
	

	@Autowired
	private CartaController cartaController;

//	@Autowired
//	private IngredienteController ingredienteController;

	@MockBean
	private CartaService CartaService;
    @MockBean
    private PizzaService PizzaService;
    @MockBean
    private OtrosService OtrosService;
    @MockBean
    private BebidaService BebidaService;
    @MockBean
    private IngredienteService ingredienteService;
    @MockBean
    private PedidoService pedidoService;
    
	@Autowired
	private MockMvc mockMvc;

//	@BeforeEach
//	void setup() {
//		Otros patatas = new Otros();
//		patatas.setId(3);
//		patatas.setCoste(12);
//		patatas.setNombre("Patatas fritas");
//		patatas.setContador(1);
//		given(this.OtrosService.findOtros()).willReturn(Lists.newArrayList(patatas));
//		given(this.OtrosService.findOtrosById(TEST_OTROS_ID)).willReturn(new Otros());
//		//given(this.otrosService.findIdOtrosByCartaId(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
//		
//		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
//	}

	@WithMockUser(value = "spring")
   	@Test
   	void testshowCartaList() throws Exception {
    	mockMvc.perform(get("/allCartas"))
    	.andExpect(status().isOk())
		.andExpect(view().name("cartas/cartasList"))
		.andExpect(model().attributeExists("cartas"));
    }
	
	@WithMockUser(value = "spring")
        @Test
	void testinitCreationForm() throws Exception {
		mockMvc.perform(get("/cartas/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("cartas/createOrUpdateCartaForm"))
				.andExpect(model().attributeExists("carta"));
	}

	@WithMockUser(value = "spring")
        @Test
	void testprocessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/cartas/new")
							.with(csrf())
							.param("nombre", "cartaPrincipal")
							.param("fecha", "2020/11/12"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allCartas"));
		
		
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/cartas/new")
					.with(csrf())
					.param("nombre", "11111")
					.param("fecha", "aaaa"))
			.andExpect(status().isOk())
			.andExpect(view().name("cartas/createOrUpdateCartaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/cartas/{cartaId}/edit", TEST_CARTA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("cartas/createOrUpdateCartaForm"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateCartaFormSuccess() throws Exception {
		mockMvc.perform(post("/cartas/{cartaId}/edit", TEST_CARTA_ID)
				.with(csrf())
				.param("nombre", "cartaPrincipal")
				.param("fecha", "2020/11/12"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/allCartas"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateOtrosFormHasErrors() throws Exception {
		mockMvc.perform(post("/cartas/{cartaId}/edit", TEST_CARTA_ID)
				.with(csrf())
				.param("nombre", "11111")
				.param("fecha", "aaaa"))
		.andExpect(status().isOk())
		.andExpect(view().name("cartas/createOrUpdateCartaForm"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testinitDeleteCartaSuccess() throws Exception {
    	Carta carta = new Carta();
        
        int cartaId = 10;

        // given
        //given(this.CartaService.findCartaById(cartaId)).willReturn(carta);
        
    	mockMvc.perform(post("/cartas/{cartaId}/delete", TEST_CARTA_ID))
    	.andExpect(status().isNoContent())
    	.andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/allCartas"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
    void testverCartaSuccess() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/VerCarta", TEST_CARTA_ID)
				.with(csrf())
				.param("nombre", "cartaPrincipal")
				.param("fecha", "2020/11/12"))
    	.andExpect(status().isOk())
		.andExpect(view().name("cartas/verCarta"))
		.andExpect(model().attributeExists("pizzas"))
		.andExpect(model().attributeExists("bebidas"))
		.andExpect(model().attributeExists("otros"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testshowPizzaLista() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/pizzas", TEST_CARTA_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("pizzas/pizzasList"))
		.andExpect(model().attributeExists("Pizzas"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testshowBebidaLista() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/bebidas", TEST_CARTA_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("bebidas/bebidasList"))
		.andExpect(model().attributeExists("bebidas"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testshowOtrosLista() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/otros", TEST_CARTA_ID))
    	.andExpect(status().isOk())
		.andExpect(view().name("Otros/OtrosList"))
		.andExpect(model().attributeExists("Otros"));
    }
    
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirPizzaACartaSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/anadirPizzaACarta/{pizzaId}", TEST_CARTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("nombre", "cartaPrincipal")
				.param("fecha", "2020/11/12")
				.param("tamano", "Grande")
				.param("tipoMasa", "Fina")
				.param("ingredientes", "queso"))
		.andExpect(status().isOk())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirBebidaACartaSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}", TEST_CARTA_ID, TEST_BEBIDA_ID)
				.with(csrf())
				.param("nombre", "cartaPrincipal")
				.param("fecha", "2020/11/12")
				.param("tamano", "Grande")
				.param("esCarbonatada", "true"))
		.andExpect(status().isOk())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirOtroACartaSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/anadirOtroACarta/{otroId}", TEST_CARTA_ID, TEST_BEBIDA_ID)
				.with(csrf())
				.param("nombre", "cartaPrincipal")
				.param("fecha", "2020/11/12")
				.param("ingredientes", "queso"))
		.andExpect(status().isOk())
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
	
    @WithMockUser(value = "spring")
   	@Test
   	void testinitPizzaCreationForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/pizza/new", TEST_CARTA_ID))
    			.andExpect(status().isOk())
    			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"))
    			.andExpect(model().attributeExists("pizza"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationPizzaFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/pizza/new", TEST_CARTA_ID)
						.with(csrf())
						.param("tamano", "Grande")
						.param("tipoMasa", "Fina")
						.param("ingredientes", "queso")
						.param("nombre", "PEPE")
						.param("coste", "20.0")
						.param("contador", "1"))
			.andExpect(status().isOk())
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/pizzas"));
    }
    
}