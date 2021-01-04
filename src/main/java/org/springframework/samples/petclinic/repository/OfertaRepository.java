package org.springframework.samples.petclinic.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.NivelSocio;
import org.springframework.samples.petclinic.model.Oferta;
import org.springframework.samples.petclinic.model.TamanoOferta;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaRepository  extends CrudRepository<Oferta, Integer> {
	
	List<Oferta> findAll() throws DataAccessException;
	
	Oferta findOfertaById(int ofertaId) throws DataAccessException;
	
	@Query("SELECT tamofert FROM TamanoOferta tamofert")
	List<TamanoOferta> findTamanoOferta() throws DataAccessException;

	@Query("SELECT nsocio FROM NivelSocio nsocio")
	List<NivelSocio> findNivelSocio() throws DataAccessException;

//	@Modifying
//	  @Query(value = "INSERT INTO OFERTA_PRODUCTO(OFERTA_ID, PRODUCTO_EN_OFERTA_ID) VALUES (?1, ?2)",	nativeQuery = true)
//		void asociarOfertaAProducto(int ofertaId, int productoEnOfertaId);
}
