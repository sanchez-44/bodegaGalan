package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_PROVEEDOR")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cod_proveedor;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;
}
