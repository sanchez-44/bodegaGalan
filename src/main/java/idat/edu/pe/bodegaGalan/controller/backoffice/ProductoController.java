package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.Producto;
import idat.edu.pe.bodegaGalan.model.request.ProductoRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/buscar/{codigo}")
    @ResponseBody
    public ResponseEntity<Producto> buscarProductoPorCodigo(@PathVariable Integer codigo) {
        Optional<Producto> productoOptional = productoService.obtenerProductoPorCodigo(codigo);

        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}