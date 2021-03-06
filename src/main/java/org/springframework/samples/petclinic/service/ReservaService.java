package org.springframework.samples.petclinic.service;


import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.model.tipoReserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.samples.petclinic.repository.TipoReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;
    private TipoReservaRepository tipoReservaRepository;

    @Autowired
    public ReservaService(ReservaRepository reservaRepository, TipoReservaRepository tipoReservaRepository) {
        this.reservaRepository = reservaRepository;
        this.tipoReservaRepository = tipoReservaRepository;

    }

    @Transactional(readOnly = true)
    public List<Reserva> findReservas() throws DataAccessException {
        return reservaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reserva findById(int reservaId) throws DataAccessException {
        return reservaRepository.findById(reservaId);
    }

    @Transactional
    public void saveReserva(Reserva reserva) throws DataAccessException {
        reservaRepository.save(reserva);
        tipoReservaRepository.save(reserva.getTipoReserva());        
    }

    @Transactional
    public void deleteReserva(Reserva reserva) throws DataAccessException {
        reservaRepository.delete(reserva);
    }

    @Transactional(readOnly = true)
    public Collection<tipoReserva> findTipoReserva() throws DataAccessException {
        return reservaRepository.findTipoReserva();
    }
    
    @Transactional
	 public void anadirMesaAReserva(int reservaId, int mesaId) throws DataAccessException {
		 reservaRepository.anadirMesaAReserva(reservaId, mesaId);		
	}
    
    @Transactional
    public List<Reserva> findReservasByCliente(int userId) throws DataAccessException {
    	return reservaRepository.findReservasByCliente(userId);
    }
    
    @Transactional
    public List<Integer> findReservasIdByMesaId(int mesaId) throws DataAccessException{
    	return reservaRepository.findReservasIdByMesaId(mesaId);
    }
    
    public List<Reserva> calcularReservasAPartirIds(List<Integer> listaId) throws DataAccessException {
    	List<Reserva> reservas = new ArrayList<Reserva>();
    	for(Integer i: listaId) {
    		Reserva r = findById(i);
    		reservas.add(r);
    	}
    	return reservas;
    	
    	
    }
    
    public Boolean unaHoraEntreReservas(LocalTime horaMiReserva, LocalTime horaReservaMesa) {
		Boolean res = false;
		Integer diferencia = (int) Math.abs(MINUTES.between(horaMiReserva, horaReservaMesa));
		
		if(diferencia>60) {
			res = true;
		}
		return res;
	}

}