package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

		  List<Person> grupo = new ArrayList<>();
		  Person persona1 = new Person();
		  persona1.setFirstName("Jesús ");
		  persona1.setId(1);
		  persona1.setLastName("Roldán Cadena");
		  grupo.add(persona1);
		  Person persona2 = new Person();
		  persona2.setFirstName("Servando ");
		  persona2.setId(2);
		  persona2.setLastName("Figueroa Gómez");
		  grupo.add(persona2);
		  Person persona3 = new Person();
		  persona3.setFirstName("Lucía ");
		  persona3.setId(3);
		  persona3.setLastName("Torres Gómez");
		  grupo.add(persona3);
		  Person persona4 = new Person();
		  persona4.setFirstName("María ");
		  persona4.setId(4);
		  persona4.setLastName("García Cáceres");
		  grupo.add(persona4);
		  Person persona5 = new Person();
		  persona5.setFirstName("Raúl ");
		  persona5.setId(5);
		  persona5.setLastName("Parrado Gordón");
		  grupo.add(persona5);
		  Person persona6 = new Person();
		  persona6.setFirstName("Álvaro ");
		  persona6.setId(6);
		  persona6.setLastName("Sánchez González");
		  grupo.add(persona6);

		  model.put("persons", grupo);
		  model.put("tittle", "Pizzeria Roto's");
		 // model.put("group", "Members:");
	    return "welcome";
	  }
}
