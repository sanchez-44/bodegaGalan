package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {
}
