package idat.edu.pe.bodegaGalan.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class VentaRegistroRequest {

    private Integer codigoVenta;
    private Integer codigoEmpleado;
    private Integer dniCliente;
    private Date fecha;
    private String descripcion;
    private Integer tipoPago;
}
