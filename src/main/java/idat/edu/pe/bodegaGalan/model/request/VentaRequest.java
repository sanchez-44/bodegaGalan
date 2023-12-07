package idat.edu.pe.bodegaGalan.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class VentaRequest {
    private Integer cod_venta;
    private Long cod_empleado;
    private Integer dni_cliente;
    private Date fecha;
    private String descripcion;
    private Integer cod_tipo_pago;

}
