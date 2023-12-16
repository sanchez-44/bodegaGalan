package idat.edu.pe.bodegaGalan.model.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class UsuarioSecurity extends User {

    private String nombres;



    public UsuarioSecurity(String username, String password, boolean enabled) {
        super(username, password, enabled, true, true, true, Collections.emptyList());
    }

}
