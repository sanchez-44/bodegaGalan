package idat.edu.pe.bodegaGalan.model.request;

import lombok.Data;

@Data
public class ProductoRequest {
    private Integer codigo;
    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Integer cod_categoria_p;
    private Integer cod_proveedor;
}
