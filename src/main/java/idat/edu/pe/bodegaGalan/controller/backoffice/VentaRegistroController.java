package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.service.VentaRegistroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/registroVentas")
public class VentaRegistroController {
    private VentaRegistroService ventaRegistroService;

    @GetMapping("/registroVentas")
    public String frmMantVenta(Model model){
        model.addAttribute("listaventas",
                ventaRegistroService.listarVentas());
        return "backoffice/frmRegistroVentas";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Venta> listarVentas(){
        return ventaRegistroService.listarVentas();
    }

}
