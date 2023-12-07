package idat.edu.pe.bodegaGalan.service;


import idat.edu.pe.bodegaGalan.model.bd.Proveedor;
import idat.edu.pe.bodegaGalan.model.request.ProveedoresRequest;
import idat.edu.pe.bodegaGalan.model.response.ResultadoResponse;
import idat.edu.pe.bodegaGalan.repository.ProveedorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProveedorService {
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> listarProveedores(){ return proveedorRepository.findAll(); }

    public ResultadoResponse guardarProveedor(ProveedoresRequest proveedoresRequest){
        String mensaje = "Proveedor registrado correctamente";
        Boolean respuesta = true;
        try{
            Proveedor proveedor = new Proveedor();
            if(proveedoresRequest.getCod_proveedor() > 0){
                proveedor.setCodigoProveedor(proveedoresRequest.getCod_proveedor());
            }
            proveedor.setNombre(proveedoresRequest.getNombre());
            proveedor.setDireccion(proveedoresRequest.getDireccion());
            proveedor.setTelefono(proveedoresRequest.getTelefono());
            proveedorRepository.save(proveedor);
        }catch (Exception ex){
            mensaje = "Producto no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}