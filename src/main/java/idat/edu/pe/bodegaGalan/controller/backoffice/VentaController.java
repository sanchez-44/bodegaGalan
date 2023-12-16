package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.service.VentaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/backoffice/ventas")
public class VentaController {
    @GetMapping("")
    public String frmMantVenta(Model model) {
        return "backoffice/frmventa";
    }

}
