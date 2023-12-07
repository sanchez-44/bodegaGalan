package idat.edu.pe.bodegaGalan.model.request;

import lombok.Data;

@Data
public class DetalleVentaRequest {
    private Integer item;
    private Integer cod_venta;
    private Integer cod_producto;
    private Integer cantidad;
}
