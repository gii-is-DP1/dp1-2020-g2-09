package org.springframework.samples.petclinic.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Reserva;
import org.springframework.samples.petclinic.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservaService {

    private ReservaRepository reservaRepository;


    @Autowired
    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Transactional(readOnly = true)
    public List<Reserva> findReservas() throws DataAccessException {
        return reservaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Reserva findReservaById(int reservaId) throws DataAccessException {
        return reservaRepository.findReservaById(reservaId);
    }

    @Transactional
    public void saveReserva(Reserva reserva) throws DataAccessException {
        reservaRepository.save(reserva);
    }

    @Transactional
    public void deleteReserva(Reserva reserva) throws DataAccessException {
        reservaRepository.delete(reserva);
    }

}