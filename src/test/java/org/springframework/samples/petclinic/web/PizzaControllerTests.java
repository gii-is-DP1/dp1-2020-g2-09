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
import org.springframework.samples.petclinic.model.Carta;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Pizza;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.tipoMasa;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;


@WebMvcTest(value = PizzaController.class,
includeFilters = @ComponentScan.Filter(value = PizzaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class PizzaControllerTests { 
	
	private static final int TEST_PIZZA_ID = 1;
	private static final int TEST_PIZZA_ID2 = 2;

	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	private static final String TEST_user= "spring";

	

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
    @MockBean
    private UserService userService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
        TamanoProducto tp1=new TamanoProducto();
        tp1.setName("GRANDE");
        
        tipoMasa tm1=new tipoMasa();
        tm1.setName("GRUESA");
        tipoMasa tm2=new tipoMasa();
        tm2.setName("RELLENA");
        
		Pizza pizza1 = new Pizza();
		pizza1.setId(3);
		pizza1.setCoste(12.0);
		pizza1.setNombre("miPizza");
		pizza1.setPersonalizada(true);
		pizza1.setTamano(tp1);
		pizza1.setTipoMasa(tm1);
		
		Pizza pizza2 = new Pizza();
		pizza2.setCoste(1.0);
		pizza2.setNombre("miPizza2");
		pizza2.setPersonalizada(true);
		pizza2.setTipoMasa(tm2);
		
		
		Pizza pizza3 = new Pizza();
		pizza3.setCoste(1.0);
		pizza3.setNombre("miPizza3");
		pizza3.setPersonalizada(true);
		
		Pizza pizza4 = new Pizza();
		pizza4.setId(4);
		pizza4.setCoste(12.0);
		pizza4.setNombre("miPizza");
		pizza4.setPersonalizada(true);
		pizza4.setTamano(tp1);
		pizza4.setTipoMasa(tm1);

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
		pizza3.setIngredientes(lista_ingredientes);

		TamanoProducto t=new TamanoProducto();
		t.setId(66);
		t.setName("mini");
		pizza1.setTamano(t);
		pizza3.setTamano(t);

		
//		tipoMasa t2=new tipoMasa();
//		t2.setId(66);
//		t2.setName("extrafina");
//		pizza1.setTipoMasa(t2);
//		tipoMasa t3=new tipoMasa();
//		t3.setId(66);
//		t3.setName("fina");
//		pizza3.setTipoMasa(t3);
		
		List<Pizza> pizzasCliente = new ArrayList<Pizza>();
		pizza1.setCliente(null);
		pizza2.setCliente(null);
		pizza3.setCliente(null);
		pizzasCliente.add(pizza2);
		pizzasCliente.add(pizza1);
		pizzasCliente.add(pizza3);
		pizzasCliente.add(pizza3);
		pizzasCliente.add(pizza3);

		given(this.pizzaService.findPizzas()).willReturn(Lists.newArrayList(pizza1));
		given(this.pizzaService.findPizzaById(TEST_PIZZA_ID)).willReturn(new Pizza());
		given(this.pizzaService.findIdPizzaById(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
		
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.cartaService.findCartaById(TEST_CARTA_ID)).willReturn(new Carta());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(new Cliente());
		given(this.ingredienteService.findIngredienteById(TEST_OFERTA_ID)).willReturn(new Ingrediente());
		
		given(this.pizzaService.findPizzaByCliente(null)).willReturn(pizzasCliente);
		given(this.pizzaService.findPizzaByCliente(cliente)).willReturn(pizzasCliente);

		User u1 = new User();
		Optional<User> op= Optional.of(u1);
		given(this.userService.findUser(TEST_user)).willReturn(op);
	}

	@WithMockUser(value = "spring")
    @Test
	void testInitCreationFormAdmin() throws Exception {
		mockMvc.perform(get("/pizzas/admin/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"))
				.andExpect(model().attributeExists("pizza"));
		
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormSuccessAdmin() throws Exception {
		mockMvc.perform(post("/pizzas/admin/new")
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allPizzas"));
		

	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrorsAdmin() throws Exception {
		mockMvc.perform(post("/pizzas/admin/new")
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
	}

   
    @WithMockUser(value = "spring")
    @Test
	void testInitCreationFormCliente() throws Exception {
		mockMvc.perform(get("/pizzas/cliente/new"))
				.andExpect(view().name("pizzas/createOrUpdatePizzaFormCliente"))
				.andExpect(model().attributeExists("pizza"));
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrorsCliente() throws Exception {
		mockMvc.perform(post("/pizzas/cliente/new")
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "e")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(model().attributeHasErrors("pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaFormCliente"));
		
	}
	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormClienteSucessMasaGruesa() throws Exception {	
		mockMvc.perform(post("/pizzas/cliente/new")
						.with(csrf())
						.param("coste", "4")
						.param("nombre", "miPiffffffffffffzza")
						.param("tamano.name", "GRANDE")
						.param("tipoMasa.name", "GRUESA")
						.param("ingredientes", "tomate"))	
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/pizzas/cliente"));
	}
	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormClienteSucessMasaRellena() throws Exception {	
		mockMvc.perform(post("/pizzas/cliente/new")
						.with(csrf())
						.param("coste", "4")
						.param("nombre", "miPiffffffffffffzza")
						.param("tamano.name", "GRANDE")
						.param("tipoMasa.name", "RELLENA")
						.param("ingredientes", "tomate"))	
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/pizzas/cliente"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormClienteSucessDuplicado() throws Exception {	
		mockMvc.perform(post("/pizzas/cliente/new")
				.with(csrf())
				.param("coste", "1")
				.param("nombre", "miPizza")
				.param("tamano.name", "mini")
				.param("tipoMasa.name", "fina")
				.param("ingredientes", "tomate"))
			.andExpect(view().name("redirect:/NombreDePizzaPersonalizadaDuplicado"));
	}
	
	@WithMockUser(value = "spring")
    @Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/pizzas/admin/{pizzaId}/edit",TEST_PIZZA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"))
				.andExpect(model().attributeExists("pizza"));
		
	}
	@WithMockUser(value = "spring")
    @Test
	void testprocessUpdatePizzaFormHasErrors() throws Exception {
		mockMvc.perform(post("/pizzas/admin/{pizzaId}/edit",TEST_PIZZA_ID)
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(model().attributeHasErrors("pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaForm"));
		
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessUpdatePizzaFormSucess() throws Exception {
		mockMvc.perform(post("/pizzas/admin/{pizzaId}/edit",TEST_PIZZA_ID)
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "pizzacontomate")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allPizzas"));
		
	}

	@WithMockUser(value = "spring")
    @Test
	void testActualizarPizza2() throws Exception { 
		mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("/pizzas/UpdatePizzaFormPedido"))
				.andExpect(model().attributeExists("pizza"))
				.andExpect(model().attributeExists("pedido"))
				.andExpect(model().attributeExists("cartaId"));
		
	}
	@WithMockUser(value = "spring")
    @Test
	void testactualizarPizzaSuccess() throws Exception { 
		mockMvc.perform(get("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new",TEST_PEDIDO_ID,TEST_CARTA_ID))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/createOrUpdatePizzaFormCliente"));
		
	}
	@WithMockUser(value = "spring")
    @Test
	void testprocessUpdatePizzaForm2SuccessIfGruesaGrande() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID)
							.with(csrf())
							.param("nombre", "PizzaNoDuplicada")
							.param("coste", "13")
							.param("tamano.name", "GRANDE")
							.param("tipoMasa.name", "GRUESA")
							.param("ingredientes", "tomate")) 
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/verCarta"));
				
}
	@WithMockUser(value = "spring")
    @Test
	void testprocessUpdatePizzaForm2SuccessIfRellenaPequeña() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID)
							.with(csrf())
							.param("nombre", "PizzaNoDuplicada")
							.param("coste", "13")
							.param("tamano.name", "PEQUEÑA")
							.param("tipoMasa.name", "RELLENA")
							.param("ingredientes", "tomate")) 
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/verCarta"));
				
}
	@WithMockUser(value = "spring")
    @Test
	void testprocessUpdatePizzaForm2SuccessIfDuplicada() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID2)
							.with(csrf())
							.param("nombre", "miPizza")
							.param("coste", "13")
							.param("tamano.name", "GRANDE")
							.param("tipoMasa.name", "GRUESA")
							.param("ingredientes", "tomate")) 
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/NombreDePizzaPersonalizadaDuplicado"));
		}
		
		@WithMockUser(value = "spring")
	    @Test
		void testprocessUpdatePizzaForm2HasErrors() throws Exception {
			mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/new",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID2)
								.with(csrf())
								.param("nombre", "23rwedfs3esadef23r 43r4r43rb4rb43t4b34tasdasdasdhjasouidhosauidhjoasdjo")
								.param("coste", "13")
								.param("tamano.name", "GRANDE")
								.param("tipoMasa.name", "GRUESA")
								.param("ingredientes", "tomate")) 
					.andExpect(status().isOk())
					.andExpect(view().name("pizzas/createOrUpdatePizzaFormCliente"));
}
	@WithMockUser(value = "spring")
    @Test
	void testActualizarPizza2Success() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID)
							.with(csrf())
							.param("nombre", "Pizza222")
							.param("coste", "13")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate")) 
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/{pedidoId}/cartas/{cartaId}/verCarta"));
				
}

	@WithMockUser(value = "spring")
    @Test
	void testProcessUpdatePizzaForm2HasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/cartas/{cartaId}/pizzas/{pizzaId}/edit",TEST_PEDIDO_ID,TEST_CARTA_ID,TEST_PIZZA_ID)
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
				.andExpect(model().attributeHasErrors("pizza"))
				.andExpect(status().isOk())
				.andExpect(view().name("pizzas/UpdatePizzaFormPedido"));
		
	}
	

	
	@WithMockUser(value = "spring")
    @Test
	void testshowPizzaListClienteSuccess() throws Exception {
		mockMvc.perform(get("/pizzas/cliente")
							.with(csrf())
							.param("coste", "13")
							.param("nombre", "Pizza2")
							.param("tamano.name", "mini")
							.param("tipoMasa.name", "extrafina")
							.param("ingredientes", "tomate"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("Pizzas"))
		.andExpect(model().attributeExists("PizzasP"))
		.andExpect(view().name("pizzas/PizzaClienteList"));
		
	}

}
