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
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Otros;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.service.CartaService;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.OfertaService;
import org.springframework.samples.petclinic.service.OtrosService;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(value = OtrosController.class,
includeFilters = @ComponentScan.Filter(value = OtrosFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)

class OtrosControllerTests {
	
	private static final int TEST_OTROS_ID = 1;
	private static final int TEST_PEDIDO_ID = 1;
	private static final int TEST_CARTA_ID = 1;
	private static final int TEST_OFERTA_ID = 1;
	

	@Autowired
	private OtrosController otrosController;

//	@Autowired
//	private IngredienteController ingredienteController;

	@MockBean
	private OtrosService otrosService;
    @MockBean
	private PedidoService pedidoService;
    @MockBean
    private CartaService cartaService;
    @MockBean
    private OfertaService ofertaService;
    @MockBean
    private IngredienteService ingredienteService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Otros patatas = new Otros();
		patatas.setId(3);
		patatas.setCoste(12);
		patatas.setNombre("Patatas fritas");
		patatas.setContador(1);
		given(this.otrosService.findOtros()).willReturn(Lists.newArrayList(patatas));
		given(this.otrosService.findOtrosById(TEST_OTROS_ID)).willReturn(new Otros());
		//given(this.otrosService.findIdOtrosByCartaId(TEST_CARTA_ID)).willReturn(new ArrayList<Integer>());
		
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.cartaService.findCartaById(TEST_CARTA_ID)).willReturn(new Carta());
		given(this.ofertaService.findOfertaById(TEST_OFERTA_ID)).willReturn(new Oferta());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/Otros/new")).andExpect(status().isOk())
				.andExpect(view().name("Otros/createOrUpdateOtrosForm")).andExpect(model().attributeExists("Otros"));
		
		/*mockMvc.perform(get("/pedidos/{pedidoId}/pedidos/new", TEST_PEDIDO_ID)).andExpect(status().isOk())
				.andExpect(view().name("pedidos/createOrUpdatePedidoForm")).andExpect(model().attributeExists("Otros"));
		
		mockMvc.perform(get("/cartas/{cartaId}/cartas/new", TEST_CARTA_ID)).andExpect(status().isOk())
				.andExpect(view().name("cartas/createOrUpdateCartaForm")).andExpect(model().attributeExists("Otros"));*/
	}

	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/Otros/new")
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Nachos").param("ingredientes", "arroz"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/allOtros"));
		
		/*mockMvc.perform(post("/pedidos/{pedidoId}/Otros/new", TEST_PEDIDO_ID)
						.with(csrf())
						.param("contador", "2")
						.param("coste", "13")
						.param("nombre", "Nachos"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
		
		mockMvc.perform(post("/cartas/{cartaId}/Otros/new", TEST_CARTA_ID)
						.with(csrf())
						.param("contador", "2")
						.param("coste", "13")
						.param("nombre", "Nachos"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/cartas/{cartaId}"));*/
	}

	@WithMockUser(value = "spring")
    @Test
	void testprocessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/Otros/new")
							.with(csrf())
							.param("contador", "ddd")
							.param("coste", "aaa")
							.param("nombre", "111").param("ingredientes", "22222"))
				//.andExpect(model().attributeHasNoErrors("oferta"))
				.andExpect(model().attributeHasErrors("otros"))
				.andExpect(status().isOk())
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		/*mockMvc.perform(post("/pedidos/{pedidoId}/Otros/{OtrosId}/edit", TEST_PEDIDO_ID, TEST_OTROS_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Nachos"))
				.andExpect(model().attributeHasNoErrors("pedido"))
				.andExpect(model().attributeHasErrors("Otros"))
				.andExpect(status().isOk())
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		mockMvc.perform(post("/cartas/{cartaId}/Otros/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
							.with(csrf())
							.param("contador", "2")
							.param("coste", "13")
							.param("nombre", "Nachos"))
				.andExpect(model().attributeHasNoErrors("carta"))
				.andExpect(model().attributeHasErrors("Otros"))
				.andExpect(status().isOk())
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));*/
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/Otros/{OtrosId}/edit", TEST_OTROS_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Otros"))
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		/*mockMvc.perform(get("/pedidos/{pedidoId}/Otros/{OtrosId}/edit", TEST_PEDIDO_ID, TEST_OTROS_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Otros"))
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		mockMvc.perform(get("/cartas/{cartaId}/Otros/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID))
				.andExpect(status().isOk()).andExpect(model().attributeExists("Otros"))
				.andExpect(view().name("Otros/createOrUpdateOtrosForm"));*/
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateOtrosFormSuccess() throws Exception {
		mockMvc.perform(post("/Otros/{OtrosId}/edit", TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Nachos").param("ingredientes", "queso"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allOtros"));

		/*mockMvc.perform(post("/pedidos/{pedidoId}/Otros/{OtrosId}/edit", TEST_PEDIDO_ID, TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Nachos"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/pedidos/{pedidoId}"));
		
		mockMvc.perform(post("/cartas/{cartaId}/Otros/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Nachos"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/cartas/{cartaId}"));*/
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testprocessUpdateOtrosFormHasErrors() throws Exception {
		mockMvc.perform(post("/Otros/{OtrosId}/edit", TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "dddd")
				.param("coste", "aaa")
				.param("nombre", "1111").param("ingredientes", "222"))
		.andExpect(status().isOk())
		.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		/*mockMvc.perform(post("/pedidos/{pedidoId}/Otros/{OtrosId}/edit", TEST_PEDIDO_ID, TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Nachos"))
	.andExpect(model().attributeHasNoErrors("pedido"))
	.andExpect(model().attributeHasErrors("Otros")).andExpect(status().isOk())
	.andExpect(view().name("Otros/createOrUpdateOtrosForm"));
		
		mockMvc.perform(post("/cartas/{cartaId}/Otros/{OtrosId}/edit", TEST_CARTA_ID, TEST_OTROS_ID)
				.with(csrf())
				.param("contador", "2")
				.param("coste", "13")
				.param("nombre", "Nachos"))
	.andExpect(model().attributeHasNoErrors("carta"))
	.andExpect(model().attributeHasErrors("Otros")).andExpect(status().isOk())
	.andExpect(view().name("Otros/createOrUpdateOtrosForm"));*/
	}
    
    
	/*@WithMockUser(value = "spring")
    @Test
void testShowOtrosListXml() throws Exception {
	mockMvc.perform(get("/Otros.xml").accept(MediaType.APPLICATION_XML)).andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
			.andExpect(content().node(hasXPath("/Otros/OtrosList[id=3]/id")));
}*/
}
