package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.TipoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Integer> {

}
