package idat.edu.pe.bodegaGalan.service;


import idat.edu.pe.bodegaGalan.model.bd.CategoriaProducto;
import idat.edu.pe.bodegaGalan.model.bd.Producto;
import idat.edu.pe.bodegaGalan.model.bd.Proveedor;
import idat.edu.pe.bodegaGalan.model.request.ProductoRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){ return productoRepository.findAll(); }

    public ResultadoResponse guardarProducto(ProductoRequest productoRequest){
        String mensaje = "Producto registrado correctamente";
        Boolean respuesta = true;
        try{
            Producto producto = new Producto();
            if(productoRequest.getCodigo() > 0){
                producto.setCodigo(productoRequest.getCodigo());
            }
            producto.setNombre(productoRequest.getNombre());
            producto.setCantidad(productoRequest.getCantidad());
            producto.setPrecio(productoRequest.getPrecio());
            CategoriaProducto categoriaProducto = new CategoriaProducto();
            categoriaProducto.setCodCategoriaP(productoRequest.getCodCategoriaP());
            Proveedor proveedor = new Proveedor();
            proveedor.setCodProveedor(productoRequest.getCodProveedor());
            producto.setCategoriaProducto(categoriaProducto);
            producto.setProveedor(proveedor);
            productoRepository.save(producto);
        }catch (Exception ex){
            mensaje = "Producto no registrado";
            respuesta = false;
        }
            return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
        }
    }