package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Producto;
import idat.edu.pe.bodegaGalan.model.request.ProductoRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/producto")
public class ProductoController {
    private ProductoService productoService;

    @GetMapping("")
    public String frmMantProducto(Model model){
        model.addAttribute("listaproductos",
                productoService.listarProductos());
        return "backoffice/product/frmproduct";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarProducto(@RequestBody ProductoRequest productRequest){
        return productoService.guardarProducto(productRequest);
    }
}