package org.springframework.samples.petclinic.web;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.service.IngredienteService;
import org.springframework.samples.petclinic.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;



@Controller
public class InformeController {

	
	private final IngredienteService IngredienteService;
	private final PizzaService PizzaService;

	@Autowired
	public InformeController(IngredienteService IngredienteService, PizzaService PizzaService) {
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
	
}
