package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_DETALLE_VENTA")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long item;

    @ManyToOne
    @JoinColumn(name = "COD_VENTA", referencedColumnName = "COD_VENTA")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "COD_PRODUCTO", referencedColumnName = "CODIGO")
    private Producto producto;

    @Column(name = "CANTIDAD")
    private Integer cantidad;
}
