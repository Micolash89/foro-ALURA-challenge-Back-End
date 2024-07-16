package micolash89.foroAlura.dominio.topico;

import micolash89.foroAlura.dominio.curso.CursoDTO;
import micolash89.foroAlura.dominio.usuario.UsuarioDTO;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        boolean activo,
        Long autorId,
        Long cursoId
) {}