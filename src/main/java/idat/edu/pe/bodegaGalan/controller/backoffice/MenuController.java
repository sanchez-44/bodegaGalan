package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Usuario;
import idat.edu.pe.bodegaGalan.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice")
public class MenuController {

    private UsuarioService usuarioService;

    @GetMapping("/principal")
    public String principal(){
        return "backoffice/principal";
    }

    @GetMapping("/registrarProveedores")
    public String registrarProveedores(){
        return  "backoffice/product/frmproveedor";
    }

    @GetMapping("/registroProducto")
    public String registroProducto(){
        return "backoffice/product/frmproducto";
    }

    @GetMapping("/faq")
    public String faq() {return "backoffice/faq";}

    @GetMapping("/nosotros")
    public String nosotros() {return "backoffice/nosotros";}

    @PostMapping("/guardarusuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuario(usuario);
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/comprobante")
    public String comprobante() {return "backoffice/comprobantePago";}

    @GetMapping("/login")
    public String login() {
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/ventasMenu")
    public String ventas() {
        return "backoffice/frmventas";
    }
    @GetMapping("/registroVentas")
    public String registroVentas() {
        return "backoffice/frmRegistroVentas";
    }
}
