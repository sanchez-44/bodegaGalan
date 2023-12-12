package idat.edu.pe.bodegaGalan.model.bd;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_EMPLEADO")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_EMPLEADO")
    private Long codigoEmpleado;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "COD_CARGO")
    private Cargo cargo;

}
