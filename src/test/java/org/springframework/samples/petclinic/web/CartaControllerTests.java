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
import java.util.Collection;
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
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Otro;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.service.BebidaService;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = CartaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class CartaControllerTests {
	
	private static final int TEST_BEBIDA_ID = 1;
	private static final int TEST_OTROS_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_CARTA_ID2 = 2;
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	
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
    private OfertaService ofertaService;
    
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		TamanoProducto t=new TamanoProducto();
		t.setId(5);
		t.setName("ENORME");
		
		
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
		
		Pizza pizza = new Pizza();
		pizza.setCoste(1.0);
		pizza.setNombre("miPizza");
		pizza.setPersonalizada(false);
		pizza.setId(TEST_PIZZA_ID);
		pizza.setIngredientes(lista_ingredientes);
		pizza.setTamano(t);
		pizza.setCliente(null);
		
		
		Bebida b = new Bebida();
		b.setId(TEST_BEBIDA_ID);
		b.setCoste(10.0);
		b.setEsCarbonatada(true);
		b.setNombre("Hidromiel");
		b.setTamano(t);
		
		Otro patatas = new Otro();
		patatas.setId(TEST_OTROS_ID);
		patatas.setCoste(12.0);
		patatas.setNombre("Patatas fritas");
		patatas.setIngredientes(lista_ingredientes);
		
		Carta carta = new Carta();
		carta.setId(TEST_CARTA_ID);
		carta.setNombre("CartitaGonsi");
		carta.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta.setFechaFinal(LocalDate.of(2021, 4, 10));
		
		Carta carta2 = new Carta();
		carta2.setId(TEST_CARTA_ID);
		carta2.setNombre("CartitaGonsi");
		carta2.setFechaCreacion(LocalDate.of(2020, 2, 2));
		carta2.setFechaFinal(LocalDate.of(2021, 4, 10));
		
		LocalDate hoy = LocalDate.now();
		
//		Collection<Pizza> pizzas = new ArrayList<Pizza>();
//		pizzas.add(pizza);
//		Collection<Bebida> bebidas = new ArrayList<Bebida>();
//		bebidas.add(b);
//		Collection<Otro> otros = new ArrayList<Otro>();
//		otros.add(patatas);
		List<Integer> pizzasIds = new ArrayList<Integer>();
		pizzasIds.add(TEST_PIZZA_ID);
		List<Integer> bebidasIds = new ArrayList<Integer>();
		bebidasIds.add(TEST_BEBIDA_ID);
		List<Integer> otrosIds = new ArrayList<Integer>();
		otrosIds.add(TEST_OTROS_ID);
		List<Integer> ofertas = new ArrayList<Integer>();
		ofertas.add(TEST_OFERTA_ID);
		
//		carta.setPizzasEnCarta(pizzas);
//		carta.setBebidasEnCarta(bebidas);
//		carta.setOtrosEnCarta(otros);
		
		given(this.CartaService.findCartas()).willReturn(Lists.newArrayList(carta,carta2));
		given(this.CartaService.findCartaById(TEST_CARTA_ID)).willReturn(carta);
		given(this.CartaService.findCartaById(TEST_CARTA_ID2)).willReturn(carta2);
		given(this.CartaService.findCartaByFechaCreacionYFechaFinal(hoy)).willReturn(carta);
		given(this.PizzaService.findIdPizzaById(TEST_CARTA_ID)).willReturn(pizzasIds);
		given(this.BebidaService.findIdBebidaByCartaId(TEST_CARTA_ID)).willReturn(bebidasIds);
		given(this.OtrosService.findIdOtroById(TEST_CARTA_ID)).willReturn(otrosIds);
		given(this.PizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(pizza);
		given(this.BebidaService.findById(TEST_BEBIDA_ID)).willReturn(b);
		given(this.OtrosService.findOtrosById(TEST_OTROS_ID)).willReturn(patatas);
		given(this.PizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(pizza);
		given(this.BebidaService.findById(TEST_BEBIDA_ID)).willReturn(b);
		given(this.OtrosService.findOtrosById(TEST_OTROS_ID)).willReturn(patatas);
		given(this.ofertaService.numeroPizzasEnOferta(TEST_PIZZA_ID)).willReturn(ofertas);
		given(this.ofertaService.numeroBebidasEnOferta(TEST_BEBIDA_ID)).willReturn(ofertas);
		given(this.ofertaService.numeroOtrosEnOferta(TEST_OTROS_ID)).willReturn(ofertas);
		//given(this.ofertaService.ponerEstadoOfertaAFalse(TEST_OFERTA_ID)).willReturn();
	
	
	}

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
    void showCartaActiva() throws Exception {
    	Carta carta = this.CartaService.findCartaByFechaCreacionYFechaFinal(LocalDate.now());
    	int id = carta.getId();
    	mockMvc.perform(get("/cartas/cartaActiva", TEST_CARTA_ID)
						.with(csrf()))
			//.andExpect(status().isOk())
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/"+id+"/VerCarta"));
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
							.param("nombre", "cartaPrincipal"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allCartas"));
		
		
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/cartas/new")
					.with(csrf())
					.param("nombre", "q")
					.param("fecha", "j"))
		.andExpect(model().attributeHasErrors())			
		.andExpect(view().name("cartas/createOrUpdateCartaForm"));
	}
    
    @WithMockUser(value = "spring")
   	@Test
   	void testinitDeleteCartaSuccess() throws Exception {       
    	mockMvc.perform(get("/cartas/{cartaId}/delete", TEST_CARTA_ID))
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
		.andExpect(model().attributeExists("otros"));
    }
    
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirPizzaACartaSuccess() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirPizzaACarta/{pizzaId}", TEST_CARTA_ID2, TEST_PIZZA_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirPizzaACartaDuplicated() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirPizzaACarta/{pizzaId}", TEST_CARTA_ID, TEST_PIZZA_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/PizzaDuplicadaEnCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirBebidaACartaSuccess() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}", TEST_CARTA_ID2, TEST_BEBIDA_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    

    @WithMockUser(value = "spring")
   	@Test
   	void testañadirBebidaACartaDuplicated() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirBebidaACarta/{bebidaId}", TEST_CARTA_ID, TEST_BEBIDA_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))

		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/BebidaDuplicadaEnCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirOtroACartaSuccess() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirOtroACarta/{otroId}", TEST_CARTA_ID2, TEST_OTROS_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testañadirOtroACartaDuplicated() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/anadirOtroACarta/{otroId}", TEST_CARTA_ID, TEST_OTROS_ID)
				.with(csrf())
				.param("carta.nombre", "cartaPrincipal")
				.param("carta.fechaFinal", "2021/11/12"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/OtroDuplicadaEnCarta"));
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
						.param("nombre", "PEPE")
						.param("coste", "20")
						.param("tamano.name", "GRANDE")
						.param("tipoMasa.name", "FINA")
						.param("ingredientes", "queso"))
			//.andExpect(status().isOk())
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/pizzas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationPizzaFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/pizza/new", TEST_CARTA_ID)
						.with(csrf())
						.param("nombre", "jejeeeeeeeeeeeeeeeee")
						.param("coste", "-1")
						.param("tipoMasa.name", "NORMAL")
						.param("ingredientes", "queso"))
			.andExpect(status().isOk())
			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void initBebidaCreationForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/bebida/new", TEST_CARTA_ID)
						.with(csrf()))
						.andExpect(status().isOk())
		    			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"))
		    			.andExpect(model().attributeExists("bebida"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationBebidaFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/bebida/new", TEST_CARTA_ID)
						.with(csrf())
						.param("nombre", "PEPE")
						.param("coste", "20.0")
						.param("esCarbonatada", "true")
						.param("tamano.name", "GRANDE"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/bebidas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationBebidaFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/bebida/new", TEST_CARTA_ID)
						.with(csrf())
						.param("nombre", "holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
						.param("coste", "-12")
						.param("esCarbonatada", "true")
						.param("tamano.name", "GRANDE"))
    		.andExpect(status().isOk())
			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
    }
    
    @WithMockUser(value = "spring")
   	@Test
   	void testinitOtroCreationForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/otro/new", TEST_CARTA_ID))
    			.andExpect(status().isOk())
    			.andExpect(view().name("Otros/createOrUpdateOtrosForm"))
    			.andExpect(model().attributeExists("otro"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationOtroFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/otro/new", TEST_CARTA_ID)
						.with(csrf())
						.param("nombre", "Papas")
						.param("coste", "1.5")
						.param("ingredientes", "Patatas"))
			//.andExpect(status().isOk())
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/otros"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessCreationOtroFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/otro/new", TEST_CARTA_ID)
						.with(csrf())
						.param("nombre", "Papas")
						.param("coste", "-1")
						.param("ingredientes", "Patatas"))
			.andExpect(status().isOk())
			.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
    }

    @WithMockUser(value = "spring")
    @Test
    void testinitUpdatePizzaForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/pizza/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
						.with(csrf()))
						.andExpect(status().isOk())
		    			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		    			//.andExpect(model().attributeExists("pizza"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdatePizzaFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/pizza/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
						.with(csrf())
						.param("nombre", "PEPE")
						.param("coste", "20")
						.param("tamano.name", "GRANDE")
						.param("tipoMasa.name", "FINA")
						.param("ingredientes", "queso"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/pizzas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdatePizzaFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/pizza/{pizzaId}/edit", TEST_CARTA_ID, TEST_PIZZA_ID)
						.with(csrf())
						.param("nombre", "holaaaaaaaaaaaaaaaaaaaaaaaaa")
						.param("coste", "-1")
						.param("tamano.name", "GRANDE")
						.param("tipoMasa.name", "FINA")
						.param("ingredientes", "queso"))
    		.andExpect(status().isOk())
			.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
    }
    
    
    @WithMockUser(value = "spring")
    @Test
    void testinitUpdateBebidaForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/bebida/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID)
						.with(csrf()))
						.andExpect(status().isOk())
		    			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
		    			//.andExpect(model().attributeExists("bebida"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdateBebidaFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/bebida/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID)
						.with(csrf())
						.param("nombre", "PEPE")
						.param("coste", "20.0")
						.param("esCarbonatada", "true")
						.param("tamano.name", "GRANDE"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/bebidas"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdateBebidaFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/bebida/{bebidaId}/edit", TEST_CARTA_ID, TEST_BEBIDA_ID)
						.with(csrf())
						.param("nombre", "PEPEeeeeeeeeeeeeeeeee")
						.param("coste", "-20.0")
						.param("esCarbonatada", "true")
						.param("tamano.name", "GRANDE"))
    		.andExpect(status().isOk())
			.andExpect(view().name("bebidas/createOrUpdateBebidaForm"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testinitUpdateOtroForm() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/otro/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
						.with(csrf()))
						.andExpect(status().isOk())
		    			.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		    			//.andExpect(model().attributeExists("otro"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdateOtrosFormSuccess() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/otro/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
						.with(csrf())
						.param("nombre", "Papas")
						.param("coste", "1.5")
						.param("ingredientes", "Patatas"))
    		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/otros"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testprocessUpdateOtrosFormFails() throws Exception {
    	mockMvc.perform(post("/cartas/{cartaId}/otro/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
						.with(csrf())
						.param("nombre", "Papassssssssssssssssssssssssss")
						.param("coste", "-1.5")
						.param("ingredientes", "Patatas"))
    		.andExpect(status().isOk())
			.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testdeletePizzaFromCarta() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/pizza/{pizzaId}/deleteFromCarta", TEST_CARTA_ID, TEST_PIZZA_ID)
						.with(csrf()))		
    		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testdeleteOtroFromCarta() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/otros/{otrosId}/deleteFromCarta", TEST_CARTA_ID, TEST_OTROS_ID)
						.with(csrf()))		
    		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testdeleteBebidaFromCarta() throws Exception {
    	mockMvc.perform(get("/cartas/{cartaId}/bebida/{bebidaId}/deleteFromCarta", TEST_CARTA_ID, TEST_BEBIDA_ID)
						.with(csrf()))		
    		.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}/VerCarta"));
    }
    
   
    
}