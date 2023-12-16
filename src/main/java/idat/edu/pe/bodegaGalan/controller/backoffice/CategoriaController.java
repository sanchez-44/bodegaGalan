package idat.edu.pe.bodegaGalan.controller.backoffice;

import idat.edu.pe.bodegaGalan.model.bd.CategoriaProducto;
import idat.edu.pe.bodegaGalan.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/category")
public class CategoriaController {
    private CategoriaService categoriaService;

    @GetMapping("/listar")
    @ResponseBody
    public List<CategoriaProducto> listarCategorias() {return categoriaService.listarCategoria();}
}
