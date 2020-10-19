package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.samples.petclinic.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {	    

		  List<Person> grupo = new ArrayList<>();
		  Person persona1 = new Person();
		  persona1.setFirstName("Jesus ");
		  persona1.setId(1);
		  persona1.setLastName("Roldan Cadena");
		  grupo.add(persona1);
		  Person persona2 = new Person();
		  persona2.setFirstName("Servando ");
		  persona2.setId(2);
		  persona2.setLastName("Figueroa Gomez");
		  grupo.add(persona2);
		  Person persona3 = new Person();
		  persona3.setFirstName("Lucía ");
		  persona3.setId(3);
		  persona3.setLastName("Torres Gómez");
		  grupo.add(persona3);
		  
		  //asi con todos
		  model.put("persons", grupo);
		  model.put("tittle", "My project");
		  model.put("group", "Members:");
	    return "welcome";
	  }
}
