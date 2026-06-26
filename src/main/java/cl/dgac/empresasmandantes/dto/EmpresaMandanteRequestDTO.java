package cl.dgac.empresasmandantes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Modelo de petición para el registro o actualización de una empresa mandante")
public class EmpresaMandanteRequestDTO {

    @Schema(description = "Nombre comercial o legal corporativo de la empresa", example = "Minera Los Pelambres SpA")
    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @Schema(description = "Rol Único Tributario (RUT) de la empresa", example = "76.123.456-K")
    @NotBlank(message = "El RUT es obligatorio")
    private String rut;

    @Schema(description = "Correo electrónico de contacto oficial", example = "contacto@pelambres.cl")
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Schema(description = "Número telefónico corporativo", example = "+56912345678")
    private String telefono;

    @Schema(description = "Dirección física de la casa matriz o sucursal principal", example = "Avenida Apoquindo 4501, Las Condes")
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @Schema(description = "Rubro o sector económico al que pertenece (ej. MINERIA, AGRICULTURA, CONSTRUCCION)", example = "MINERIA")
    @NotBlank(message = "El sector es obligatorio")
    private String sector;

    @Schema(description = "Estado operativo comercial actual (ej. ACTIVA, SUSPENDIDA)", example = "ACTIVA")
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}