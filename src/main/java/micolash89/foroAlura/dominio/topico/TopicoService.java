package micolash89.foroAlura.dominio.topico;

import jakarta.persistence.EntityNotFoundException;
import micolash89.foroAlura.dominio.curso.Curso;
import micolash89.foroAlura.dominio.curso.CursoRepository;
import micolash89.foroAlura.dominio.usuario.Usuario;
import micolash89.foroAlura.dominio.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public TopicoDTO crearTopico(TopicoDTO topicoDTO) {
        if (topicoRepository.existsByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje())) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
        }

        Usuario autor = usuarioRepository.findById(topicoDTO.autorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        Curso curso = cursoRepository.findById(topicoDTO.cursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        Topico topico = new Topico(
                null,
                topicoDTO.titulo(),
                topicoDTO.mensaje(),
                LocalDateTime.now(),
                true, // activo por defecto
                autor,
                curso
        );

        Topico topicoGuardado = topicoRepository.save(topico);
        return convertirADTO(topicoGuardado);
    }


    public Page<TopicoDTO> listarTopicos(Pageable paginacion) {
        Page<Topico> topicos = topicoRepository.findAll(paginacion);
        return topicos.map(this::convertirADTO);
    }

    public TopicoDTO detallarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        return convertirADTO(topico);
    }

    @Transactional
    public TopicoDTO actualizarTopico(Long id, TopicoDTO topicoDTO) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (!topico.getTitulo().equals(topicoDTO.titulo()) || !topico.getMensaje().equals(topicoDTO.mensaje())) {
            if (topicoRepository.existsByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje())) {
                throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje.");
            }
        }

        topico.setTitulo(topicoDTO.titulo());
        topico.setMensaje(topicoDTO.mensaje());

        Topico topicoActualizado = topicoRepository.save(topico);
        return convertirADTO(topicoActualizado);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
        topico.setActivo(false);
        topicoRepository.save(topico);
    }


//    @Transactional
//    public void actualizarEstadoTopico(Long topicoId, String nuevoEstado) {
//        Topico topico = topicoRepository.findById(topicoId)
//                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));
//        topico.setStatus(nuevoEstado);
//        topicoRepository.save(topico);
//    }

    private TopicoDTO convertirADTO(Topico topico) {
        return new TopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getActivo(),
                topico.getAutor().getId(),
                topico.getCurso().getId()
        );
    }
}