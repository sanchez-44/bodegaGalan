package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByCodigoVenta(Integer codigoVenta);

    List<Venta> findByDniCliente(Integer dniCliente);

    List<Venta> findByCodigoVentaAndDniCliente(Integer codigoVenta, Integer dniCliente);


}
