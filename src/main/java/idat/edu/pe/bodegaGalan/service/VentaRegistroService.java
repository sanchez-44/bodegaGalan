package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class VentaRegistroService {
    private VentaRepository ventaRepository;
    public List<Venta> listarVentas() { return ventaRepository.findAll();}
}
