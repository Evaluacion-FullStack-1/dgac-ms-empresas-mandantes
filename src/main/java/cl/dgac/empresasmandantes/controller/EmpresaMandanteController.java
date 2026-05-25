package cl.dgac.empresasmandantes.controller;

import cl.dgac.empresasmandantes.dto.EmpresaMandanteRequestDTO;
import cl.dgac.empresasmandantes.dto.EmpresaMandanteResponseDTO;
import cl.dgac.empresasmandantes.service.EmpresaMandanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas-mandantes")
public class EmpresaMandanteController {

    private final EmpresaMandanteService empresaService;

    public EmpresaMandanteController(EmpresaMandanteService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaMandanteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaMandanteResponseDTO> crearEmpresa(
            @Valid @RequestBody EmpresaMandanteRequestDTO dto) {

        EmpresaMandanteResponseDTO empresaCreada = empresaService.crearEmpresa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaMandanteResponseDTO> actualizarEmpresa(
            @PathVariable Long id,
            @Valid @RequestBody EmpresaMandanteRequestDTO dto) {

        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-rut")
    public ResponseEntity<EmpresaMandanteResponseDTO> buscarPorRut(
            @RequestParam String rut) {

        return ResponseEntity.ok(empresaService.buscarPorRut(rut));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(empresaService.listarPorEstado(estado));
    }

    @GetMapping("/sector")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> buscarPorSector(
            @RequestParam String sector) {

        return ResponseEntity.ok(empresaService.buscarPorSector(sector));
    }

    @GetMapping("/razon-social")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> buscarPorRazonSocial(
            @RequestParam String razonSocial) {

        return ResponseEntity.ok(empresaService.buscarPorRazonSocial(razonSocial));
    }

    @GetMapping("/empresas-proveedoras")
    public ResponseEntity<String> consultarEmpresasProveedoras() {
        return ResponseEntity.ok(empresaService.consultarMicroservicioEmpresasProveedoras());
    }
}