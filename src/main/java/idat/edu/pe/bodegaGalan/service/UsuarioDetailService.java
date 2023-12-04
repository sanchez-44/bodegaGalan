package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.Usuario;
import idat.edu.pe.bodegaGalan.model.security.UsuarioSecurity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class UsuarioDetailService implements UserDetailsService {
    private UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarUsuarioPorNombreUsuario(username);
        if (usuario == null){
            throw  new UsernameNotFoundException("Usuario no encontrado");
        }
        return autenticacionUsuario(usuario);
    }
    private UsuarioSecurity autenticacionUsuario(Usuario usuario){
        return new UsuarioSecurity(
                usuario.getUsername(),
                usuario.getPassword(),
                true
        );
    }


}
