package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_TIPO_PAGO")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TIPO_PAGO")
    private Long codigoTipoPago;

    @Column(name = "NOMBRE")
    private String nombre;
}
