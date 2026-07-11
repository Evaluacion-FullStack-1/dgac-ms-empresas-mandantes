package cl.dgac.empresasmandantes.service;

import cl.dgac.empresasmandantes.dto.EmpresaMandanteRequestDTO;
import cl.dgac.empresasmandantes.dto.EmpresaMandanteResponseDTO;
import cl.dgac.empresasmandantes.exception.ResourceNotFoundException;
import cl.dgac.empresasmandantes.mapper.EmpresaMandanteMapper;
import cl.dgac.empresasmandantes.model.EmpresaMandante;
import cl.dgac.empresasmandantes.repository.EmpresaMandanteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaMandanteService {

    private final EmpresaMandanteRepository empresaRepository;
    private final EmpresaMandanteMapper empresaMapper;
    
    // Inyectamos RestTemplate en lugar de WebClient
    private final RestTemplate restTemplate;

    // Leemos la URL base desde el application.yml
    @Value("${empresas-proveedoras.base-url}")
    private String empresasProveedorasBaseUrl;

    public EmpresaMandanteService(EmpresaMandanteRepository empresaRepository,
                                  EmpresaMandanteMapper empresaMapper,
                                  RestTemplate restTemplate) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.restTemplate = restTemplate;
    }

    public List<EmpresaMandanteResponseDTO> listarEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmpresaMandanteResponseDTO buscarPorId(Long id) {
        EmpresaMandante empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa mandante no encontrada con ID: " + id));

        return empresaMapper.toDTO(empresa);
    }

    public EmpresaMandanteResponseDTO crearEmpresa(EmpresaMandanteRequestDTO dto) {
        EmpresaMandante empresa = empresaMapper.toEntity(dto);
        empresa.setFechaRegistro(java.time.LocalDate.now());
        EmpresaMandante empresaGuardada = empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresaGuardada);
    }

    public EmpresaMandanteResponseDTO actualizarEmpresa(Long id, EmpresaMandanteRequestDTO dto) {
        EmpresaMandante empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa mandante no encontrada con ID: " + id));

        empresaMapper.updateEntity(empresa, dto);
        EmpresaMandante empresaActualizada = empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresaActualizada);
    }

    public void eliminarEmpresa(Long id) {
        EmpresaMandante empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa mandante no encontrada con ID: " + id));

        empresaRepository.delete(empresa);
    }

    public EmpresaMandanteResponseDTO buscarPorRut(String rut) {
        EmpresaMandante empresa = empresaRepository.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa mandante no encontrada con RUT: " + rut));

        return empresaMapper.toDTO(empresa);
    }

    public List<EmpresaMandanteResponseDTO> listarPorEstado(String estado) {
        return empresaRepository.findByEstado(estado)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmpresaMandanteResponseDTO> buscarPorSector(String sector) {
        return empresaRepository.buscarPorSector(sector)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<EmpresaMandanteResponseDTO> buscarPorRazonSocial(String razonSocial) {
        return empresaRepository.buscarPorRazonSocial(razonSocial)
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODO CORREGIDO ---
    public String consultarMicroservicioEmpresasProveedoras() {
        // Construimos la URL completa para llamar al otro servicio vía Eureka
        String urlFinal = empresasProveedorasBaseUrl + "/api/empresas-proveedoras";
        
        // Hacemos la petición GET de forma síncrona
        return restTemplate.getForObject(urlFinal, String.class);
    }
}