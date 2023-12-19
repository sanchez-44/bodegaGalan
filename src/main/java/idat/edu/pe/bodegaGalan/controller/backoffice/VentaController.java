package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.model.request.VentaRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.service.ProductoService;
import idat.edu.pe.bodegaGalan.service.VentaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/ventas")
public class VentaController {
    private VentaService ventaService;
    private ProductoService productoService;

    @GetMapping("")
    public String frmMantVenta(Model model) {
        return "backoffice/frmventa";
    }

    @PostMapping("/registrarVenta")
    @ResponseBody
    public ResultadoResponse registrarVenta(@RequestBody List<VentaRequest> ventas) {
        ResultadoResponse resultadoResponse = ventaService.registrarVentas(ventas);

        return resultadoResponse;
    }
    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}
