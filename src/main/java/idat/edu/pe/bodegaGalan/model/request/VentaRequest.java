package idat.edu.pe.bodegaGalan.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class VentaRequest {
    private Integer codEmpleado;
    private Integer dniCliente;
    private Date fecha;
    private String descripcion;
    private Integer codProducto;
    private Integer cantidad;
    private Double precio;  // Asegúrate de enviar el precio si es necesario en el backend
    private Integer codigoTipoPago;  // Agrega este campo

    // Getters y setters

    // Constructor
    public VentaRequest(Integer codEmpleado, Integer dniCliente, Date fecha, String descripcion,
                        Integer codProducto, Integer cantidad, Double precio, Integer codigoTipoPago) {
        this.codEmpleado = codEmpleado;
        this.dniCliente = dniCliente;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.codProducto = codProducto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.codigoTipoPago = codigoTipoPago;
    }
}
