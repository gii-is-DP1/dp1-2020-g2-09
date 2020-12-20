package org.springframework.samples.petclinic.web;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Mesas;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.samples.petclinic.service.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;



@Controller
public class InformeController {

	
	private final IngredienteService IngredienteService;
	private final PizzaService PizzaService;
	private final MesaService mesaService;

	@Autowired
	public InformeController(IngredienteService IngredienteService, PizzaService PizzaService,MesaService mesaService) {
		this.mesaService = mesaService;
		this.IngredienteService = IngredienteService;
		this.PizzaService = PizzaService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/informe")
	public String initList(Map<String, Object> model) {
		return "informe/InformeList";
	}
	
	@GetMapping(value = "/informe/IngredientesMasUsados")
	public String informeIngredientesMasUsados(Map<String, Object> model) {
		List<Ingrediente> l = this.IngredienteService.findIngredientes();
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		for(Ingrediente i:l) {
			Integer aux = this.IngredienteService.CountIngrediente(i.getId());
			mapa.put(i.getNombre(), aux);
		}
		Map<String, Integer> mapaOrdered =mapa.entrySet().stream()
		.sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		model.put("mapa", mapaOrdered);
		return "informe/InformeIngredientesMasUsados";
	}
	@GetMapping(value = "/informe/MesasMasUsadas")
	public String informeMesasMasUsadas(Map<String, Object> model) {
		List<Mesa> l = this.mesaService.findMesas();
		Map<Integer, Integer> mapa = new HashMap<Integer, Integer>();
		for(Mesa i:l) {
			Integer aux = this.mesaService.CountMesa(i.getId());
			mapa.put(i.getId(), aux);
		}
		model.put("mapa", mapa);
		return "informe/InformeMesasMasUsadas";
	}
	@GetMapping(value = "/informe/CaducidadIngredientes")
	public String informeCaducidadIngredientes(Map<String, Object> model) {
		List<Ingrediente> l = this.IngredienteService.findIngredientes();
		Map<Ingrediente, LocalDate> mapa = new HashMap<Ingrediente, LocalDate>();
		for(Ingrediente i:l) {
			LocalDate aux = i.getFechaCaducidad();
			mapa.put(i, aux);
		}
		Map<Ingrediente,LocalDate> mapaOrdenado=mapa.entrySet().stream()
				.sorted((Map.Entry.<Ingrediente, LocalDate>comparingByValue()))
		        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		model.put("mapa", mapaOrdenado);
		return "informe/CaducidadIngredientes";
	}
}
