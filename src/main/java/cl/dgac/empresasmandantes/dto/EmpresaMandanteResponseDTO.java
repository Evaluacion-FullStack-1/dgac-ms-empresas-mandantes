package cl.dgac.empresasmandantes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de respuesta con la información detallada de la empresa mandante")
public class EmpresaMandanteResponseDTO {

    @Schema(description = "Identificador único de la empresa en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Nombre comercial o legal corporativo de la empresa", example = "Minera Los Pelambres SpA")
    private String razonSocial;

    @Schema(description = "Rol Único Tributario (RUT) de la empresa", example = "76.123.456-K")
    private String rut;

    @Schema(description = "Correo electrónico de contacto oficial", example = "contacto@pelambres.cl")
    private String email;

    @Schema(description = "Número telefónico corporativo", example = "+56912345678")
    private String telefono;

    @Schema(description = "Dirección física de la casa matriz o sucursal principal", example = "Avenida Apoquindo 4501, Las Condes")
    private String direccion;

    @Schema(description = "Rubro o sector económico al que pertenece", example = "MINERIA")
    private String sector;

    @Schema(description = "Estado operativo comercial actual", example = "ACTIVA")
    private String estado;

    @Schema(description = "Fecha en la que la empresa fue registrada en el sistema", example = "2026-06-25")
    private LocalDate fechaRegistro;
}