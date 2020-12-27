package org.springframework.samples.petclinic.web;


import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Reclamacion;
import org.springframework.samples.petclinic.model.Reclamaciones;
import org.springframework.samples.petclinic.model.User;
import org.springframework.samples.petclinic.service.ClienteService;
import org.springframework.samples.petclinic.service.ReclamacionService;
import org.springframework.samples.petclinic.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ReclamacionController {

	private ReclamacionService reclamacionService;
	private UserService userService;
	private ClienteService clienteService;
	
	//PROBLEMA: EL CONSTRUCTOR HACE QUE ME FALLEN TODAS LAS PRUEBAS DEL RECLAMACIONCONTROLLERTESTS
	//Me dice Failed to load application context
	//Si quito el userService y el clienteService como parámetros del constructor 
	//y quito todo lo de dentro del método menos lo de reclamacionService
	//vuelve a funcionar, pero claro si lo quito después me mete 
	//un NullPointer a la hora de crear la sesión del usuario  y mostrar sus reclamaciones.
	@Autowired
	public ReclamacionController(ReclamacionService reclamacionService, 
			UserService userService, ClienteService clienteService) {
		this.reclamacionService = reclamacionService;
			this.userService = userService;
			this.clienteService = clienteService;
	}
	
	@InitBinder("reclamacion")
	public void initReclamacionBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ReclamacionValidator());
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = { "/allReclamaciones" })
	
	public String showReclamacionList(Map<String, Object> model) {
		Reclamaciones reclamaciones = new Reclamaciones();
		reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findPedidosConReclamaciones());
		//reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamaciones());
		//List<Integer> pedidosConReclamaciones = this.reclamacionService.findPedidosConReclamaciones();
		//model.put("pedidosReclamaciones", pedidosConReclamaciones);
		model.put("reclamaciones", reclamaciones);
		return "reclamaciones/reclamacionesList";
	} 
	
	//para ver las reclamaciones del cliente que ha iniciado sesión
		@GetMapping("/reclamaciones/user")
		public String showMisReclamaciones(Map<String, Object> model) {
			Reclamaciones reclamaciones = new Reclamaciones();
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserDetails userDetails = null;
			if (principal instanceof UserDetails) {
			  userDetails = (UserDetails) principal;
			}
			String userName = userDetails.getUsername();
		    User usuario = this.userService.findUser(userName).get();
		    Cuenta cliente= this.clienteService.findCuentaByUser(usuario);
		    reclamaciones.getReclamacionesList().addAll(this.reclamacionService.findReclamacionesByCliente(cliente.getId()));
			model.put("reclamaciones", reclamaciones);
			return "reclamaciones/reclamacionUser";
		} 
	
		//Aqui tenemos que añadir la reclamación sobre un pedido seleccionado
		@GetMapping("/pedidos/{pedidoId}/anadirReclamacion/new")
		public String initCreationForm(Map<String, Object> model, @PathVariable("pedidoId") int pedidoId) {
			Reclamacion reclamacion = new Reclamacion();
			reclamacion.setRespuesta("Lo sentimos mucho, ...");
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar nueva reclamacion
		@PostMapping(value = "/pedidos/{pedidoId}/anadirReclamacion/new")
		public String processCreationForm(@PathVariable("pedidoId") int pedidoId, @Valid Reclamacion reclamacion, BindingResult result, ModelMap model) {
			if (result.hasErrors()) {
				model.put("reclamacion", reclamacion);
				return "reclamaciones/createOrUpdateReclamacionForm";
			}
			else {
				ReclamacionValidator ofValidator = new ReclamacionValidator();
				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				this.reclamacionService.saveReclamacion(reclamacion);
				Integer reclamacionId=reclamacion.getId();
				this.reclamacionService.añadirReclamacionAPedido(pedidoId, reclamacionId);
				return "redirect:/allReclamaciones";
			} 
		}
		
		
		//iniciar actualizacion -> Administrador responde reclamación
		@GetMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String initUpdateForm(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			reclamacion.setRespuesta("");
			model.put("reclamacion", reclamacion);
			return "reclamaciones/createOrUpdateReclamacionForm";
		}
		
		//mandar actualizacion
		@PostMapping(value = "/reclamaciones/{reclamacionId}/edit")
		public String processUpdateReclamacionForm(@Valid Reclamacion reclamacion, BindingResult result,
				@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			if (result.hasErrors()) {
				reclamacion.setId(reclamacionId);
				model.put("reclamacion", reclamacion);
				return "reclamaciones/createOrUpdateReclamacionForm";
				
			}
			else {
				ReclamacionValidator ofValidator = new ReclamacionValidator();
				ValidationUtils.invokeValidator(ofValidator, reclamacion, result);
				reclamacion.setId(reclamacionId);
				this.reclamacionService.saveReclamacion(reclamacion);
				return "redirect:/allReclamaciones";
			}
		}
		
		/* //borrar reclamacion
		@GetMapping(value = "/reclamaciones/{reclamacionId}/delete")
		public String initDeleteReclamacion(@PathVariable("reclamacionId") int reclamacionId, ModelMap model) {
			Reclamacion reclamacion = this.reclamacionService.findReclamacionById(reclamacionId);
			this.reclamacionService.deleteReclamacion(reclamacion);
			return "redirect:/allReclamaciones";
		} */
		
//		@ModelAttribute("reclamacion")
//		public Reclamacion findReclamacion(@PathVariable("reclamacionId") int reclamacionId) {
//			return this.findReclamacion(reclamacionId);
//		}
}