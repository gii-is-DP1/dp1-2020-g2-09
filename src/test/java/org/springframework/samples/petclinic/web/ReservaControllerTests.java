package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
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
import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Any;

@WebMvcTest(value = ReservaController.class,
		includeFilters = @ComponentScan.Filter(value = ReservaFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)

class ReservaControllerTests {

	private static final int TEST_MESA_ID = 1;
	private static final int TEST_MESA_ID2 = 2;
	private static final int TEST_MESA_ID3 = 3;
	private static final String TEST_user= "spring";
	private static final int TEST_RESERVA_ID = 1;
	private static final int TEST_RESERVA_ID2 = 1;
	private static final int TEST_RESERVA_ID3 = 3;
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

	@SuppressWarnings("unchecked")
	@BeforeEach
	void setup() {
		Reserva r = new Reserva();
		tipoReserva tr = new tipoReserva();
		tr.setName("ALMUERZO");
		tr.setId(1);
		r.setId(TEST_RESERVA_ID);
		r.setFechaReserva(LocalDate.of(2021, 11, 24));
		r.setHora(LocalTime.of(13, 12));
		r.setNumeroPersonas(6);
		r.setTipoReserva(tr);
		
		Reserva r2 = new Reserva();
		r2.setId(TEST_RESERVA_ID2);
		r2.setFechaReserva(LocalDate.of(2021, 10, 24));
		r2.setHora(LocalTime.of(13, 12));
		r2.setNumeroPersonas(2);
		r2.setTipoReserva(tr);
		
		Reserva r3 = new Reserva();
		r3.setId(TEST_RESERVA_ID3);
		r3.setFechaReserva(LocalDate.of(2021, 11, 24));
		r3.setHora(LocalTime.of(13, 12));
		r3.setNumeroPersonas(4);
		r3.setTipoReserva(tr);
		
		Reserva r4 = new Reserva();
		r4.setId(TEST_RESERVA_ID3);
		r4.setFechaReserva(LocalDate.of(2021, 11, 24));
		r4.setHora(LocalTime.of(12, 10));
		r4.setNumeroPersonas(4);
		r4.setTipoReserva(tr);
		
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
		
		//Creación de mesas y lista de mesas
		Mesa m = new Mesa();
		m.setCapacidad(6);
		m.setId(TEST_MESA_ID);
		Mesa m2 = new Mesa();
		m2.setCapacidad(6);
		m2.setId(TEST_MESA_ID2);
		Mesa m3 = new Mesa();
		m3.setCapacidad(3);
		m3.setId(TEST_MESA_ID3);
		List<Mesa> listaMesas = new ArrayList<Mesa>();
		listaMesas.add(m2);
		listaMesas.add(m);
		listaMesas.add(m3);
		/////////////////////////////////////////////////
		List<Mesa> m12= new ArrayList<Mesa>();
		m12.add(m2);
		r.setMesasEnReserva(listaMesas);
		r2.setMesasEnReserva(m12);
		
		List<Integer> reservasId1 =new ArrayList<Integer>(0);
		List<Integer> reservasId2 = new ArrayList<Integer>();
		reservasId2.add(TEST_RESERVA_ID2);
		List<Reserva> reservas =new ArrayList<Reserva>();
		reservas.add(r2);
		reservas.add(r3);
		reservas.add(r4);
		List<Mesa> m123= new ArrayList<Mesa>();
		r3.setMesasEnReserva(m123);
		m123.add(m3);
		
		//this.reservaService.saveReserva(r);
		given(this.mesaService.findIdMesaByReserva(TEST_RESERVA_ID)).willReturn(TEST_MESA_ID);
	//	given(this.reservaService.findReservas()).willReturn(Lists.newArrayList(r))
		given(this.mesaService.findById(TEST_MESA_ID)).willReturn(m);
		given(this.mesaService.findByReserva(TEST_RESERVA_ID)).willReturn(Lists.newArrayList(m));
		given(this.mesaService.findMesas()).willReturn(listaMesas);
		given(this.reservaService.findById(TEST_RESERVA_ID)).willReturn(r);
		given(this.clienteService.findCuentaById(TEST_CLIENTE_ID)).willReturn(cliente);
		given(this.reservaService.findReservasByCliente(TEST_CLIENTE_ID)).willReturn(Lists.newArrayList(r));
		given(this.reservaService.findReservasIdByMesaId(anyInt())).willReturn(reservasId2, reservasId1);
		given(this.reservaService.calcularReservasAPartirIds(Mockito.anyList())).willReturn(reservas);
		
		given(this.reservaService.findById(TEST_RESERVA_ID3)).willReturn(r3);		
		given(this.userService.findUser(u1.getUsername())).willReturn(op);
		given(this.clienteService.findCuentaByUser(u1)).willReturn(cliente);
		
		doAnswer(new Answer() {
		    public Object answer(InvocationOnMock invocation) {
		        Object[] args = invocation.getArguments();
		        ((Reserva)args[0]).setId(1);
		        return null; // void method, so return null
		    }
		}).when(this.reservaService).saveReserva(any(Reserva.class));
		
				
	}

	@WithMockUser(value = "spring")
        @Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/reservas/new"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("reserva"))
				.andExpect(view().name("reservas/createOrUpdateReservaForm"));
	}
	
	//Da fallo porque Spring no le da del tiron el id a la variable del controlador
	@WithMockUser(value = "spring")
        @Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/reservas/new")
							.with(csrf())
							.param("numeroPersonas", "6")
							.param("tipoReserva.name", "CENA")
							.param("fechaReserva", "2021/11/24")
							.param("hora", "21:12"))	//el error dice que no se puede convertir de string a localtime
				.andExpect(view().name("redirect:/reservas/1/allMesasDisponibles"));
				
	}

	@WithMockUser(value = "spring")
    @Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/reservas/new")
							.with(csrf())
							.param("numeroPersonas", "0")
							.param("tipoReserva.name", "CENA")
							.param("fechaReserva", "2021/02/12")
							.param("hora","10:22"))
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
    
  /* @WithMockUser(value = "spring")
    @Test
    void testMesasDisponibles() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles", TEST_RESERVA_ID2))
    	.andExpect(status().isOk())
		.andExpect(view().name("mesas/mesasDisponibles"))
		.andExpect(model().attributeExists("miReserva"))
		.andExpect(model().attributeExists("mesasDisponiblesSolucion"));
    }*/
    @WithMockUser(value = "spring")
    @Test
    void testMesasDisponibles() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles", TEST_RESERVA_ID3))
    	.andExpect(status().isOk())
		.andExpect(view().name("mesas/mesasDisponibles"))
		.andExpect(model().attributeExists("miReserva"))
		.andExpect(model().attributeExists("mesasDisponibles"));
    }
    
    @WithMockUser(value = "spring")
    @Test
    void testMesasDisponiblesIf() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles", TEST_RESERVA_ID3))
    	.andExpect(status().isOk())
		.andExpect(view().name("mesas/mesasDisponibles"))
		.andExpect(model().attributeExists("miReserva"))
		.andExpect(model().attributeExists("mesasDisponibles"));
    }
   /* @WithMockUser(value = "spring")
    @Test
    void testMesasDisponiblesElse() throws Exception {
    	mockMvc.perform(get("/reservas/{reservaId}/allMesasDisponibles", TEST_RESERVA_ID2))
    	.andExpect(status().isOk())
		.andExpect(view().name("mesas/mesasDisponibles"))
		.andExpect(model().attributeExists("miReserva"))
		.andExpect(model().attributeExists("mesasDisponiblesSolucion"));
    }*/
}