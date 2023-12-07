package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.model.request.DetalleVentaRequest;
import idat.edu.pe.bodegaGalan.model.request.VentaRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.repository.DetalleVentaRepository;
import idat.edu.pe.bodegaGalan.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class VentaService {
    private VentaRepository ventaRepository;
    private DetalleVentaRepository detalleVentaRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarVenta(VentaRequest venta, List<DetalleVentaRequest> detalleVentaRequests){
        try{
            Venta nuevaVenta = new Venta();
            nuevaVenta.setCodigoVenta(venta.getCod_venta());

        }catch (Exception ex){

        }return null;
    }

}
