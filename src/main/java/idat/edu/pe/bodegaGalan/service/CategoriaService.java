package idat.edu.pe.bodegaGalan.service;

import idat.edu.pe.bodegaGalan.model.bd.CategoriaProducto;
import idat.edu.pe.bodegaGalan.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;
    public List<CategoriaProducto> listarCategoria() { return categoriaRepository.findAll();}
}
