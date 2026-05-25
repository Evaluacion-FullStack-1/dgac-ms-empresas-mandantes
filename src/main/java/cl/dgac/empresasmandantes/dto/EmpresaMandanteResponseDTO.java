package cl.dgac.empresasmandantes.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpresaMandanteResponseDTO {

    private Long id;
    private String razonSocial;
    private String rut;
    private String email;
    private String telefono;
    private String direccion;
    private String sector;
    private String estado;
    private LocalDate fechaRegistro;
}