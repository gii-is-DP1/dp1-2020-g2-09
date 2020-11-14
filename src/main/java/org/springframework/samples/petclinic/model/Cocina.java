package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Cocineros")
public class Cocina extends Empleado {

}
