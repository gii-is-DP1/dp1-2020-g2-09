package org.springframework.samples.petclinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class MesaServiceTest {

    @Autowired
    protected MesaService mesaService;
    
    @Autowired
    protected ReservaService reservaService;
    
    @Test
	@Transactional(readOnly=true)
    public void FindAllTest() {
    	List<Mesa> mesas= mesaService.findMesas();
    	assertEquals(mesas.size(),7);
    }
    

}
