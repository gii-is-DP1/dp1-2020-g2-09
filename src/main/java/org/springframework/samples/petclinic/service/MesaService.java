package org.springframework.samples.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Mesa;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.MesaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MesaService {

	private MesaRepository mesaRepository;

	@Autowired
	public MesaService(MesaRepository mesaRepository) {
		this.mesaRepository = mesaRepository;
	}		

	@Transactional(readOnly = true)	
	public List<Mesa> findMesas() throws DataAccessException {
		return mesaRepository.findAll();
	}	
	
	@Transactional(readOnly = true)
	public Mesa findById(int mesaId) throws DataAccessException {
		return mesaRepository.findById(mesaId);
	}
	@Transactional(readOnly = true)
	public List<Mesa> findByReserva(int reserva) throws DataAccessException {
		return mesaRepository.findByReserva(reserva);
	}
	@Transactional
	public void saveMesa(Mesa mesa) throws DataAccessException {
		mesaRepository.save(mesa);		
	}	
	
	@Transactional
	public void deleteMesa(Mesa mesa) throws DataAccessException {
		mesaRepository.delete(mesa);		
	}

	public Integer CountMesa(Integer id) throws DataAccessException{
		List<Integer> l = this.mesaRepository.CountMesa(id);
		Integer sum=0;
		for(Integer i=0;i<l.size();i++) {
			sum +=l.get(i);
		}
		return sum;
	}
	
	
}
