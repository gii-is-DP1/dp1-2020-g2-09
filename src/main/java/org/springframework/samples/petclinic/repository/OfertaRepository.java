package org.springframework.samples.petclinic.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.Pedido;
import org.springframework.samples.petclinic.model.TamanoProducto;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository  extends CrudRepository<Oferta, Integer> {
	
	List<Oferta> findAll() throws DataAccessException;
	
	Oferta findOfertaById(int ofertaId) throws DataAccessException;
	

	
	@Query("SELECT tamprod FROM TamanoProducto tamprod")
	List<TamanoProducto> findTamanoProducto() throws DataAccessException;

	@Query("SELECT nsocio FROM NivelSocio nsocio")
	List<NivelSocio> findNivelSocio() throws DataAccessException;

}
