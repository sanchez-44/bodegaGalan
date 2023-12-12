package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "TBL_CATEGORIA_P")
public class CategoriaProducto {
    @Id
    @Column(name = "COD_CATEGORIA_P")
    private int cod_categoria_p;

    @Column(name= "NOMBRE_CATEGORIA")
    private String nombre_catergoria;
}
