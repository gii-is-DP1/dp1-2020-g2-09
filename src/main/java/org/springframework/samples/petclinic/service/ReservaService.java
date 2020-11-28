package org.springframework.samples.petclinic.service;


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

}