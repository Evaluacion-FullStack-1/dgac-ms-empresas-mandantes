package cl.dgac.empresasmandantes.controller;

import cl.dgac.empresasmandantes.dto.EmpresaMandanteRequestDTO;
import cl.dgac.empresasmandantes.dto.EmpresaMandanteResponseDTO;
import cl.dgac.empresasmandantes.service.EmpresaMandanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas-mandantes")
@Tag(name = "Empresas Mandantes", description = "Operaciones para la gestión de las empresas e instituciones que contratan o solicitan servicios de vuelo en el ecosistema DGAC")
public class EmpresaMandanteController {

    private final EmpresaMandanteService empresaService;

    public EmpresaMandanteController(EmpresaMandanteService empresaService) {
        this.empresaService = empresaService;
    }

    @Operation(summary = "Listar todas las empresas mandantes", description = "Obtiene un registro completo de todas las empresas mandantes inscritas en la plataforma.")
    @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @Operation(summary = "Buscar empresa por ID", description = "Obtiene los detalles de una empresa mandante específica mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaMandanteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @Operation(summary = "Registrar nueva empresa mandante", description = "Ingresa una nueva entidad corporativa o institucional a la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. RUT duplicado o mal formateado)")
    })
    @PostMapping
    public ResponseEntity<EmpresaMandanteResponseDTO> crearEmpresa(
            @Valid @RequestBody EmpresaMandanteRequestDTO dto) {

        EmpresaMandanteResponseDTO empresaCreada = empresaService.crearEmpresa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaCreada);
    }

    @Operation(summary = "Actualizar información corporativa", description = "Modifica los datos comerciales, de contacto o el estado de una empresa mandante existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EmpresaMandanteResponseDTO> actualizarEmpresa(
            @PathVariable Long id,
            @Valid @RequestBody EmpresaMandanteRequestDTO dto) {

        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, dto));
    }

    @Operation(summary = "Eliminar empresa mandante", description = "Elimina el registro de una empresa mandante del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa eliminada exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar empresa por RUT", description = "Busca el registro exacto de una empresa utilizando su Rol Único Tributario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
            @ApiResponse(responseCode = "404", description = "RUT no registrado en el sistema")
    })
    @GetMapping("/buscar-rut")
    public ResponseEntity<EmpresaMandanteResponseDTO> buscarPorRut(
            @RequestParam String rut) {

        return ResponseEntity.ok(empresaService.buscarPorRut(rut));
    }

    @Operation(summary = "Filtrar empresas por estado", description = "Obtiene una lista de empresas según su estado actual (ej. ACTIVA, SUSPENDIDA, INACTIVA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(empresaService.listarPorEstado(estado));
    }

    @Operation(summary = "Filtrar por sector comercial", description = "Obtiene una lista de empresas pertenecientes a un sector o rubro específico (ej. MINERIA, AGRICULTURA, AUDIOVISUAL).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/sector")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> buscarPorSector(
            @RequestParam String sector) {

        return ResponseEntity.ok(empresaService.buscarPorSector(sector));
    }

    @Operation(summary = "Buscar por Razón Social", description = "Busca coincidencias parciales o totales en la razón social o nombre comercial de las empresas.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/razon-social")
    public ResponseEntity<List<EmpresaMandanteResponseDTO>> buscarPorRazonSocial(
            @RequestParam String razonSocial) {

        return ResponseEntity.ok(empresaService.buscarPorRazonSocial(razonSocial));
    }

    @Operation(summary = "Consultar estado de Empresas Proveedoras (WebClient)", description = "Endpoint de integración para verificar la disponibilidad del microservicio de Empresas Proveedoras.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Empresas Proveedoras")
    @GetMapping("/empresas-proveedoras")
    public ResponseEntity<String> consultarEmpresasProveedoras() {
        return ResponseEntity.ok(empresaService.consultarMicroservicioEmpresasProveedoras());
    }
}