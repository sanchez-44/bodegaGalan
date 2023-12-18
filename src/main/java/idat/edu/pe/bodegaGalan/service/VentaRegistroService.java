package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class VentaRegistroService {
    private VentaRepository ventaRepository;
    public List<Venta> listarVentas() { return ventaRepository.findAll();}

    public List<Venta> buscarVentas(Integer codigoVenta, Integer dniCliente) {
        if (codigoVenta != null) {
            return ventaRepository.findByCodigoVenta(codigoVenta);

        } else if (dniCliente != null) {
            return ventaRepository.findByDniCliente(dniCliente);

        } else {

            return ventaRepository.findAll();
        }
    }
}
