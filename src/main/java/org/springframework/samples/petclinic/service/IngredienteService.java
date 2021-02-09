package org.springframework.samples.petclinic.service;


import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Alergenos;
import org.springframework.samples.petclinic.model.Ingrediente;
import org.springframework.samples.petclinic.repository.IngredienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngredienteService {

	private IngredienteRepository IngredienteRepository;


	@Autowired
	public IngredienteService(IngredienteRepository IngredienteRepository) {
		this.IngredienteRepository = IngredienteRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Ingrediente> findIngredientes() throws DataAccessException {
		return IngredienteRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Ingrediente findIngredienteById(int IngredienteId) throws DataAccessException {
		return IngredienteRepository.findIngredienteById(IngredienteId);
	}
	
	@Transactional
	public void saveIngrediente(Ingrediente Ingrediente) throws DataAccessException {
		IngredienteRepository.save(Ingrediente);		
	}	
	
	@Transactional(readOnly = true)
    public Collection<Alergenos> findAlergenos() throws DataAccessException {
        return IngredienteRepository.findAlergenos();
    }
	
	@Transactional
	public List<Integer> findIngredienteIdByOtrosId(int otroId) throws DataAccessException{
		return this.IngredienteRepository.findIngredienteIdByOtrosId(otroId);
	}
	
	@Transactional
	public Integer CountIngrediente(int ingredienteId) throws DataAccessException{
		List<Integer> l = this.IngredienteRepository.CountIngrediente(ingredienteId);
		Integer sum=0;
		for(Integer i=0;i<l.size();i++) {
			sum +=l.get(i);
		}
		return sum;
	}

	public Date CaducidadIngrediente(int ingredienteId) throws DataAccessException{
		List<Date> l = this.IngredienteRepository.CaducidadIngrediente(ingredienteId);
		return l.get(ingredienteId);
	}
	
}
