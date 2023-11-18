package idat.edu.pe.bodegaGalan.repository;

import idat.edu.pe.bodegaGalan.model.bd.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol,
        Integer> {

    Rol findByNomrol(String nomrol);

}