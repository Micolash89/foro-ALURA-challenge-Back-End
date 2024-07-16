package micolash89.foroAlura.dominio.respuesta;

import jakarta.validation.constraints.NotBlank;
import micolash89.foroAlura.dominio.topico.TopicoDTO;
import micolash89.foroAlura.dominio.usuario.UsuarioDTO;

import java.time.LocalDateTime;

public record RespuestaDTO(
        Long id,
        String mensaje,
        Long topicoId,
        LocalDateTime fechaCreacion,
        Long autorId,
        Boolean solucion
) {}