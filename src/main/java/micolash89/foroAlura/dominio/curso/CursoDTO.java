package micolash89.foroAlura.dominio.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {}
