package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_PRODUCTO")
public class Producto {
    @Id
    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "NOMBRE", length = 150)
    private String nombre;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "COD_PROVEEDOR", referencedColumnName = "COD_PROVEEDOR")
    private Proveedor proveedor;
}
