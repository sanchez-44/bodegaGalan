package idat.edu.pe.bodegaGalan.model.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResultadoResponse {
    private Boolean respuesta;
    private String mensaje;
}
