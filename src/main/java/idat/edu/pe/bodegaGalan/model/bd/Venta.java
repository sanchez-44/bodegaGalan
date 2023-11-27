package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TBL_VENTA")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_VENTA")
    private Long codigoVenta;

    @ManyToOne
    @JoinColumn(name = "COD_EMPLEADO", referencedColumnName = "COD_EMPLEADO")
    private Empleados empleado;

    @Column(name = "DNI_CLIENTE")
    private Integer dniCliente;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "DESCRIPCION", length = 150)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "COD_TIPO_PAGO", referencedColumnName = "COD_TIPO_PAGO")
    private TipoPago tipoPago;
}
