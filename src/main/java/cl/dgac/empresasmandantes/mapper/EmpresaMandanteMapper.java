package cl.dgac.empresasmandantes.mapper;

import cl.dgac.empresasmandantes.dto.EmpresaMandanteRequestDTO;
import cl.dgac.empresasmandantes.dto.EmpresaMandanteResponseDTO;
import cl.dgac.empresasmandantes.model.EmpresaMandante;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMandanteMapper {

    public EmpresaMandante toEntity(EmpresaMandanteRequestDTO dto) {
        EmpresaMandante empresa = new EmpresaMandante();

        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setRut(dto.getRut());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefono(dto.getTelefono());
        empresa.setDireccion(dto.getDireccion());
        empresa.setSector(dto.getSector());
        empresa.setEstado(dto.getEstado());
        empresa.setFechaRegistro(dto.getFechaRegistro());

        return empresa;
    }

    public EmpresaMandanteResponseDTO toDTO(EmpresaMandante empresa) {
        EmpresaMandanteResponseDTO dto = new EmpresaMandanteResponseDTO();

        dto.setId(empresa.getId());
        dto.setRazonSocial(empresa.getRazonSocial());
        dto.setRut(empresa.getRut());
        dto.setEmail(empresa.getEmail());
        dto.setTelefono(empresa.getTelefono());
        dto.setDireccion(empresa.getDireccion());
        dto.setSector(empresa.getSector());
        dto.setEstado(empresa.getEstado());
        dto.setFechaRegistro(empresa.getFechaRegistro());

        return dto;
    }

    public void updateEntity(EmpresaMandante empresa, EmpresaMandanteRequestDTO dto) {
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setRut(dto.getRut());
        empresa.setEmail(dto.getEmail());
        empresa.setTelefono(dto.getTelefono());
        empresa.setDireccion(dto.getDireccion());
        empresa.setSector(dto.getSector());
        empresa.setEstado(dto.getEstado());
        empresa.setFechaRegistro(dto.getFechaRegistro());
    }
}