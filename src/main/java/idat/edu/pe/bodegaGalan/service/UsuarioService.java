package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.Usuario;
import idat.edu.pe.bodegaGalan.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class UsuarioService {
    private  UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();

    public Usuario buscarUsuarioPorNombreUsuario(String username){
        return usuarioRepository.findByUsername(username);
    }

    public Usuario guardarUsuario(Usuario usuario){
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }
}
