package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "TBL_COMPROBANTE")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO")
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "COD_VENTA", referencedColumnName = "COD_VENTA")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "COD_TIPO_PAGO", referencedColumnName = "COD_TIPO_PAGO")
    private TipoPago tipoPago;

    @Column(name = "DESCRIPCION", length = 100)
    private String descripcion;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "IGV")
    private Double igv;

    @Column(name = "SUBTOTAL")
    private Double subtotal;
}
