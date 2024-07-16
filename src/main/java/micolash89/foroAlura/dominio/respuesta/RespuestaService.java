package micolash89.foroAlura.dominio.respuesta;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import micolash89.foroAlura.dominio.topico.Topico;
import micolash89.foroAlura.dominio.topico.TopicoRepository;
import micolash89.foroAlura.dominio.topico.TopicoService;
import micolash89.foroAlura.dominio.usuario.Usuario;
import micolash89.foroAlura.dominio.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoService topicoService;

    @Transactional
    public RespuestaDTO crearRespuesta(RespuestaDTO respuestaDTO) {
        Topico topico = topicoRepository.findById(respuestaDTO.topicoId())
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        Usuario autor = usuarioRepository.findById(respuestaDTO.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        Respuesta respuesta = new Respuesta(
                null,
                respuestaDTO.mensaje(),
                topico,
                LocalDateTime.now(),
                autor,
                respuestaDTO.solucion()
        );

        Respuesta respuestaGuardada = respuestaRepository.save(respuesta);

        // Ya no necesitamos actualizar el estado del tópico aquí,
        // ya que el campo 'activo' solo representa si está dado de baja o no

        return convertirADTO(respuestaGuardada);
    }

    private RespuestaDTO convertirADTO(Respuesta respuesta) {
        return new RespuestaDTO(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getSolucion()
        );
    }
}