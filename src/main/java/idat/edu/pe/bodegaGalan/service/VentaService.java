package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.*;
import idat.edu.pe.bodegaGalan.model.request.DetalleVentaRequest;
import idat.edu.pe.bodegaGalan.model.request.VentaRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.repository.ComprobanteRepository;
import idat.edu.pe.bodegaGalan.repository.DetalleVentaRepository;
import idat.edu.pe.bodegaGalan.repository.VentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class VentaService {
    private VentaRepository ventaRepository;
    private DetalleVentaRepository detalleVentaRepository;
    private ComprobanteRepository comprobanteRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarVentas(List<VentaRequest> ventas){
        try{
           for (VentaRequest ventaRequest : ventas){
               Venta nuevaVenta = new Venta();
               Empleados empleados = new Empleados();
               empleados.setCodigoEmpleado(ventaRequest.getCodEmpleado());
               nuevaVenta.setDniCliente(ventaRequest.getDniCliente());
               nuevaVenta.setFecha(ventaRequest.getFecha());
               nuevaVenta.setDescripcion(ventaRequest.getDescripcion());
               TipoPago tipoPago = new TipoPago();
               tipoPago.setCodigoTipoPago(ventaRequest.getCodigoTipoPago());
               Venta ventaGuardada = ventaRepository.save(nuevaVenta);

               DetalleVenta nuevoDetalleVenta = new DetalleVenta();
               nuevoDetalleVenta.setVenta(ventaGuardada);
               Producto producto = new Producto();
               producto.setCodigo(ventaRequest.getCodProducto());
               nuevoDetalleVenta.setCantidad(ventaRequest.getCantidad());
               detalleVentaRepository.save(nuevoDetalleVenta);

               double subtotalVenta =ventaRequest.getPrecio();
               double totalConIGV = subtotalVenta + (subtotalVenta * 0.18);

               Comprobante nuevoComprobante = new Comprobante();
               nuevoComprobante.setVenta(ventaGuardada);
               nuevoComprobante.setFecha(ventaRequest.getFecha());
               nuevoComprobante.setTotal(totalConIGV);
               nuevoComprobante.setIgv(subtotalVenta * 0.18);
               nuevoComprobante.setSubtotal(subtotalVenta);
               comprobanteRepository.save(nuevoComprobante);
           }
            return new ResultadoResponse(true,"Ventas registradas con éxito");
        }catch (Exception ex){
            return new ResultadoResponse(false,"Error al registrar ventas");
        }
    }

}
