package micolash89.foroAlura.dominio.usuario;

import jakarta.validation.constraints.NotNull;

public record LoginUsuarioDTO(
        @NotNull
        String correoElectronico,
        @NotNull
        String contrasenia
) {

}
