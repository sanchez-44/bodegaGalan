package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_CATEGORIA_P")
public class CategoriaProducto {
    @Id
    @Column(name = "COD_CATEGORIA_P")
    private int codCategoria;

    @Column(name= "NOMBRE_CATEGORIA")
    private String nombreCategoria;
}
