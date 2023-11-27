package idat.edu.pe.bodegaGalan.controller.frontoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/frontoffice")
public class frontofficeController {
    @GetMapping("/registro-Venta")
    public String mostrarRegistroVenta() {
        return "frontoffice/registroVenta";
    }

    @GetMapping("/registrar-Ventas")
    public String mostrarRegistroDeVentas() {
        return "frontoffice/registrarVentas";
    }

    @GetMapping("/faq")
    public String mostrarFaq() {
        return "frontoffice/faq";
    }

    @GetMapping("/registro-Usuario")
    public String mostrarRegistroUsuario() {
        return "frontoffice/registroUsuario";
    }

    @GetMapping("/registro-Proveedores")
    public String mostrarRegistroProveedores() {
        return "frontoffice/registroProveedores";
    }

    @GetMapping("/registro-Producto")
    public String mostrarRegistroProducto() {
        return "frontoffice/registroProducto";
    }
}
