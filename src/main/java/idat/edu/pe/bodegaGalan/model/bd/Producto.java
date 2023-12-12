package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_PRODUCTO")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CANTIDAD")
    private Integer cantidad;

    @Column(name = "PRECIO")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "COD_PROVEEDOR")
    private Proveedor cod_proveedor;

    @ManyToOne
    @JoinColumn(name = "COD_CATEGORIA_P")
    private CategoriaProducto cod_categoria_p;
}
