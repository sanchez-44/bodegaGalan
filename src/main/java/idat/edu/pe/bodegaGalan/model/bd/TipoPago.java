package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
@Table(name = "TBL_TIPO_PAGO")
public class TipoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_TIPO_PAGO")
    private Integer codigoTipoPago;

    @Column(name = "NOMBRE")
    private String nombre;
}
