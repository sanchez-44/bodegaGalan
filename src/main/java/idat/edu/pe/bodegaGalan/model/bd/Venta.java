package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_VENTA")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_VENTA")
    private Integer codigoVenta;

    @ManyToOne
    @JoinColumn(name = "COD_EMPLEADO")
    private Empleados codigoEmpleado;

    @Column(name = "DNI_CLIENTE")
    private Integer dniCliente;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "COD_TIPO_PAGO")
    private TipoPago tipoPago;
}
