package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Producto;
import idat.edu.pe.bodegaGalan.model.bd.Proveedor;
import idat.edu.pe.bodegaGalan.model.request.ProductoRequest;
import idat.edu.pe.bodegaGalan.model.request.ProveedoresRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.service.ProveedorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/proveedor")
public class ProveedorController {
    private ProveedorService proveedorService;

    @GetMapping("")
    public String frmMantProveedor(Model model){
        model.addAttribute("listaproveedores",
                proveedorService.listarProveedores());
        return "backoffice/product/frmproveedor";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Proveedor> listarProveedores(){
        return proveedorService.listarProveedores();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarProveedores(@RequestBody ProveedoresRequest proveedoresRequest){
        return proveedorService.guardarProveedor(proveedoresRequest);
    }
    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}
