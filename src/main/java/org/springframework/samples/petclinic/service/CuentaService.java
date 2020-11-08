package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cuenta;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CuentaService {

	private CuentaRepository cuentaRepository;


	@Autowired
	public CuentaService(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Cuenta> findCuentas() throws DataAccessException {
		return cuentaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Cuenta findCuentaById(int cuentaId) throws DataAccessException {
		return cuentaRepository.findCuentaById(cuentaId);
	}
	
	@Transactional
	public void saveCuenta(Cuenta cuenta) throws DataAccessException {
		cuentaRepository.save(cuenta);		
	}	
	
	
}
