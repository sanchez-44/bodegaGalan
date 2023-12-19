package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Venta;
import idat.edu.pe.bodegaGalan.service.VentaRegistroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/registroVentas")
public class VentaRegistroController {
    private VentaRegistroService ventaRegistroService;


    @GetMapping("/frmMantRegistroVenta")
    public String frmMantVenta(Model model){
        List<Venta> listaVentas = ventaRegistroService.listarVentas();
        System.out.println("Número de ventas: " + listaVentas.size());

        model.addAttribute("listaventas", listaVentas);
        return "backoffice/frmRegistroVentas";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Venta> listarVentas(){
        return ventaRegistroService.listarVentas();
    }

    @GetMapping("/buscar")
    @ResponseBody
    public List<Venta> buscarVentas(@RequestParam(required = false) Integer codigoVenta,
                                    @RequestParam(required = false) Integer dniCliente) {
        return ventaRegistroService.buscarVentas(codigoVenta, dniCliente);
    }

    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}
