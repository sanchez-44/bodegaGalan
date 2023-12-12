package idat.edu.pe.bodegaGalan.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backoffice")
public class MenuController {
    @GetMapping("/principal")
    public String principal(){
        return "backoffice/principal";
    }

    @GetMapping("/registrarProveedores")
    public String registrarProveedores(){
        return  "backoffice/registrarProveedores";
    }

    @GetMapping("/registroProducto")
    public String registroProducto(){
        return "backoffice/registroProducto";
    }
}
