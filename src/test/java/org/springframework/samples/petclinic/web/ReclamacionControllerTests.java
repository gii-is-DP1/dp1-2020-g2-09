package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.model.Cliente;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.service.PedidoService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

@WebMvcTest(value = ReclamacionController.class,
includeFilters = @ComponentScan.Filter(value = ReclamacionFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
class ReclamacionControllerTests {

	private static final int TEST_RECLAMACION_ID = 9;

	private static final int TEST_PEDIDO_ID = 99;

	@Autowired
	private ReclamacionController reclamacionController;


	@MockBean
	private ReclamacionService reclamacionService;
        
    @MockBean
	private PedidoService pedidoService;
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Reclamacion r = new Reclamacion();
		Pedido p = new Pedido();
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(1);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		
		p.setCliente(cliente);
		p.setDireccion("Bda San Diego");
		//p.setEstadoPedido(estadoPedido);
		p.setFechaPedido(LocalDate.of(2020, 12, 1));
		p.setId(1);
		//p.setOfertasEnPedido(ofertasEnPedido);
		p.setPrecio(40.5);
		//p.setTipoEnvio(tipoEnvio);
		//p.setTipoPago(tipoPago);
		
		
		r.setId(3);
		r.setFechaReclamacion(LocalDate.of(2020, 11, 24));
		r.setObservacion("aaaaa");
		
		given(this.reclamacionService.findReclamaciones()).willReturn(Lists.newArrayList(r));
		given(this.pedidoService.findPedidoById(TEST_PEDIDO_ID)).willReturn(new Pedido());
		given(this.reclamacionService.findReclamacionById(TEST_RECLAMACION_ID)).willReturn(new Reclamacion());
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/pedidos/{pedidoId}/anadirReclamacion/new",TEST_PEDIDO_ID))
		.andExpect(status().isOk()).andExpect(model().attributeExists("reclamacion"))
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}

	//REVISAR REDIRECCIÓN
	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/anadirReclamacion/new", TEST_PEDIDO_ID)
				.with(csrf())
				.param("fechaReclamacion", "2020/11/24")
				.param("observacion", "aaaaa"))
	.andExpect(status().is3xxRedirection())
	.andExpect(view().name("redirect:/allReclamaciones"));
}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/pedidos/{pedidoId}/anadirReclamacion/new", TEST_PEDIDO_ID)
							.with(csrf())
							.param("fechaReclamacion", "abcd")
							.param("observacion", "aaaaa"))
				.andExpect(model().attributeHasErrors("reclamacion"))
				.andExpect(status().isOk())
				.andExpect(view().name("reclamaciones/createOrUpdateReclamacionForm"));
	}

//    @WithMockUser(value = "spring")
//	@Test
//	void testInitUpdateForm() throws Exception {
//		mockMvc.perform(get("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID))
//				.andExpect(status().isOk()).andExpect(model().attributeExists("mesa"))
//				.andExpect(view().name("mesas/createOrUpdateMesaForm"));
//	}
//    
//    @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormSuccess() throws Exception {
//		mockMvc.perform(post("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID)
//							.with(csrf())
//							.param("numeroPersonas", "6")
//							.param("tipo_reserva", "CENA")
//							.param("fecha_reserva", "2015/02/12"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/reservas/{reservaId}"));
//	}
//    
//    @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormHasErrors() throws Exception {
//		mockMvc.perform(post("/reservas/{reservaId}/mesas/{mesaId}/edit", TEST_RESERVA_ID, TEST_MESA_ID)
//							.with(csrf())
//							.param("numeroPersonas", "6")
//							.param("tipo_reserva", "CENA")
//							.param("fecha_reserva", "2015/02/12"))
//				.andExpect(model().attributeHasNoErrors("reserva"))
//				.andExpect(model().attributeHasErrors("mesa")).andExpect(status().isOk())
//				.andExpect(view().name("mesas/createOrUpdatePetForm"));
//	}
	

}
