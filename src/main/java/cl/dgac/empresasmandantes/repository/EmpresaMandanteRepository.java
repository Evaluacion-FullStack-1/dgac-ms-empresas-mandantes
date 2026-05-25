package cl.dgac.empresasmandantes.repository;

import cl.dgac.empresasmandantes.model.EmpresaMandante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaMandanteRepository extends JpaRepository<EmpresaMandante, Long> {

    Optional<EmpresaMandante> findByRut(String rut);

    List<EmpresaMandante> findByEstado(String estado);

    @Query("SELECT e FROM EmpresaMandante e WHERE LOWER(e.sector) LIKE LOWER(CONCAT('%', :sector, '%'))")
    List<EmpresaMandante> buscarPorSector(String sector);

    @Query("SELECT e FROM EmpresaMandante e WHERE LOWER(e.razonSocial) LIKE LOWER(CONCAT('%', :razonSocial, '%'))")
    List<EmpresaMandante> buscarPorRazonSocial(String razonSocial);
}