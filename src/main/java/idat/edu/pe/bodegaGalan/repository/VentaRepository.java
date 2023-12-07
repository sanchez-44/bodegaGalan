package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
