package cl.duoc.benjamincubillos.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Solicitud {

    @Min(value = 1, message = "El id debe ser mayor a 0.")
    @NotNull(message = "El id no puede ser null.")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre_paciente;

    @NotBlank(message = "El apellido es obligatorio.")
    private String apellido_paciente;

    @Pattern(regexp = "^0*(\\d{1,3}(\\.?\\d{3}){2})-([\\dkK])$", message = "El rut debe estar en el formato XX.XXX.XXX-X o X.XXX.XXX-X")
    @NotBlank(message = "El rut es obligatorio.")
    private String rut;

    @Min(value = 0, message = "La edad debe estar entre 0 y 110.")
    @Max(value = 110, message = "La edad debe estar entre 0 y 110.")
    private int edad_paciente;

    @NotBlank(message = "La dirección es obligatoria.")
    private String direccion_paciente;

    @NotBlank(message = "El telefono es obligatorio.")
    private String telefono_paciente;

    @NotBlank(message = "El tipo de atención es obligatorio.")
    private String tipo_atencion;

    @NotBlank(message = "Indique un estado de solicitud.")
    private String estado_solicitud;

    @NotNull(message = "La fecha de registro es obligatoria.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent(message = "La fecha de registro no puede ser una fecha futura.")
    private LocalDate fecha_registro;

    @Min(value = 1, message = "El nivel de prioridad debe estar entre 1 y 5.")
    @Max(value = 5, message = "El nivel de prioridad debe estar entre 1 y 5.")
    private int nivel_prioridad;
}
