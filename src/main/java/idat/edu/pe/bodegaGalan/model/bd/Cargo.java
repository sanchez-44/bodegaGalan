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
@Table(name = "TBL_CARGO")
public class Cargo {
    @Id
    @Column(name = "COD_CARGO")
    private int codigoCargo;

    @Column(name = "NOMBRE")
    private String nombre;
}
