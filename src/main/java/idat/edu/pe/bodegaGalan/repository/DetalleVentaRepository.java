package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVenta> {
}
