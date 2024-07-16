package micolash89.foroAlura.dominio.perfil;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record PerfilDTO(Long id, String nombre) {}

