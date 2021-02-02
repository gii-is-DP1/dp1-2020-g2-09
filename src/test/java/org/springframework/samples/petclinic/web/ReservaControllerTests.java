package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.naming.Binding;

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
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.samples.petclinic.service.ReservaService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ReservaController.class,
		includeFilters = @ComponentScan.Filter(value = ReservaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)

class ReservaControllerTests {

	private static final int TEST_MESA_ID = 1;
	private static final String TEST_user= "spring";
	private static final int TEST_RESERVA_ID = 1;
	private static final int TEST_CLIENTE_ID = 1;
	@Autowired
	private ReservaController reservaController;


	@MockBean
	private ReservaService reservaService;
        
    @MockBean
	private MesaService mesaService;
    
    @MockBean 
    private ClienteService clienteService;
    
    @MockBean
    private UserService userService;
    
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Reserva r = new Reserva();
		tipoReserva tr = new tipoReserva();
		tr.setName("ALMUERZO");
		tr.setId(1);
		
		r.setId(TEST_RESERVA_ID);
		r.setFechaReserva(LocalDate.of(2021, 11, 24));
		r.setHora(LocalTime.of(12, 12));
		r.setNumeroPersonas(6);
		r.setTipoReserva(tr);
		
		
		Cliente cliente = new Cliente();
		cliente.setApellidos("Roldán Cadena");
		cliente.setEmail("jrc@gmail.com");
		cliente.setFechaNacimiento(LocalDate.of(2000, 6,7));
		cliente.setId(TEST_CLIENTE_ID);
		cliente.setNombre("Jesús");
		cliente.setTelefono(123456789);
		
		r.setCliente(cliente);
		
		User u1 = new User();
		Optional<User> op= Optional.of(u1);
		given(this.userService.findUser(TEST_user)).willReturn(op);
		
		cliente.setUser(u1);
		
		Mesa m = new Mesa();
		m.setCapacidad(3);
		m.setId(TEST_MESA_ID);
		Collection<Mesa> m1= new ArrayList<>();
		m1.add(m);
		r.setMesasEnReserva(m1);
		
		given(this.mesaService.findIdMesaByReserva(TEST_RESERVA_ID)).willReturn(TEST_MESA_ID);
		given(this.reservaService.findReservas()).willReturn(Lists.newArrayList(r));
		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(m);
		given(this.mesaService.findByReserva(TEST_RESERVA_ID)).willReturn(Lists.newArrayList(m));

		given(this.reservaService.findById(TEST_RESERVA_ID)).willReturn(r);
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(cliente);
		given(this.reservaService.findReservasByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(r));
		
		given(this.userService.findUser(u1.getUsername())).willReturn(op);
		given(this.clienteService.findCuentaByUser(u1)).willReturn(cliente);
				
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/reservas/new"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("reserva"))
				.andExpect(view().name("reservas/createOrUpdateReservaForm"));
	}
	
	//Da fallo
	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/reservas/new")
							.with(csrf())
							.param("numeroPersonas", "4")
							.param("tipoReserva.name", "ALMUERZO")
							.param("fechaReserva", "2022/02/12")
							.param("hora", "22:22"))	//el error dice que no se puede convertir de string a localtime
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/reservas/"+TEST_RESERVA_ID+"/allMesasDisponibles"))
				.andExpect(status().isOk());
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/reservas/new")
							.with(csrf())
							.param("numeroPersonas", "0")
							.param("tipoReserva", "CENA")
							.param("fechaReserva", "2015/02/12")
							.param("hora","10:22")
							.param("mesasEnReserva","1"))
				.andExpect(status().isOk())
				.andExpect(view().name("reservas/createOrUpdateReservaForm"));
	}

    @WithMockUser(value = "spring")
	@Test
	void testInitUpdateForm() throws Exception {
		mockMvc.perform(get("/reservas/{reservaId}/edit", TEST_RESERVA_ID))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("reserva"))
				.andExpect(view().name("reservas/createOrUpdateReservaForm"));
	}
    
    //Da fallo
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormSuccess() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/edit", TEST_RESERVA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")

							.param("tipoReserva.name", "CENA")
							.param("fechaReserva", "2021/02/12")
							.param("hora","22:22"))
							//.param("hora",String.valueOf(LocalTime.of(10, 12))))
							//.param("mesasEnReserva","1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/reservas/{reservaId}/allMesasDisponibles"));
	}
    
    @WithMockUser(value = "spring")
	@Test
	void testProcessUpdateFormHasErrors() throws Exception {
		mockMvc.perform(post("/reservas/{reservaId}/edit", TEST_RESERVA_ID)
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipoReserva", "CENA")
							.param("fechaReserva", "2015/02/12")
							.param("hora","12:20:09")
							.param("mesasEnReserva","1"))	
				.andExpect(model().attributeHasErrors("reserva"))
				.andExpect(status().isOk())
				.andExpect(view().name("reservas/createOrUpdateReservaForm"));
	}
    
    @WithMockUser(value = "spring")
    @Test
    void testShowReservaList() throws Exception {
    	mockMvc.perform(get("/allReservas")).andExpect(status().isOk())
		.andExpect(view().name("reservas/reservasList"))
		.andExpect(model().attributeExists("reservas"));
    }
    
    //Hay una parte que no se prueba (mirar con Coverage). ¿Se tendrá que probar en el service?
    @WithMockUser(value = "spring")
    @Test
    void testMesasDisponibles() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles", TEST_RESERVA_ID)).andExpect(status().isOk())
		.andExpect(view().name("mesas/mesasDisponibles"))
		.andExpect(model().attributeExists("miReserva"))
		.andExpect(model().attributeExists("mesasDisponiblesSolucion"));
    }
    
    //No funciona
    @WithMockUser(value = "spring")
    @Test
    void testDetallesReserva() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/verDetalles", TEST_RESERVA_ID)).andExpect(status().isOk())
		.andExpect(view().name("reservas/verDetallesReserva"))
		.andExpect(model().attributeExists("reserva"))
		.andExpect(model().attributeExists("usuario"))
		.andExpect(model().attributeExists("cliente"))
		.andExpect(model().attributeExists("mesa"));
    }
    
    
    @WithMockUser(value = "spring")
    @Test
    void testAnadirMesaAReserva() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles/{mesaId}", TEST_RESERVA_ID, TEST_MESA_ID))
    	.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/reservas/user"));
    }
    
    //Da fallo
    @WithMockUser(value = "spring")
    @Test
    void testShowMisReservas() throws Exception {
    	mockMvc.perform(get("/reservas/user", TEST_RESERVA_ID))
    	.andExpect(status().isOk()).andExpect(status().is2xxSuccessful())
    	.andExpect(view().name("reservas/reservaUser"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testInitDeleteReserva() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/delete", TEST_RESERVA_ID))
    			.andExpect(view().name("welcome"))
    			.andExpect(model().attributeDoesNotExist("reserva"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testinitReserva() throws Exception {
    	mockMvc.perform(get("/reservas/mesas/{reservaId}", TEST_RESERVA_ID))
    			.andExpect(view().name("redirect:/allReservas"));
    			
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testdeleteReserva() throws Exception {
    	mockMvc.perform(get("/reserva/{reservaId}/delete", TEST_RESERVA_ID))
    			.andExpect(status().is3xxRedirection())
    			.andExpect(view().name("redirect:/allReservas"))
    			.andExpect(model().attributeDoesNotExist("reserva"));

    			
    }
}