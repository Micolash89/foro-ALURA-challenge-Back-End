package micolash89.foroAlura.dominio.curso;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public CursoDTO crearCurso(CursoDTO cursoDTO) {
        if (cursoRepository.existsByNombre(cursoDTO.nombre())) {
            throw new IllegalArgumentException("Ya existe un curso con ese nombre.");
        }

        Curso curso = new Curso(
                null,
                cursoDTO.nombre(),
                cursoDTO.categoria()
        );

        Curso cursoGuardado = cursoRepository.save(curso);
        return convertirADTO(cursoGuardado);
    }

    private CursoDTO convertirADTO(Curso curso) {
        return new CursoDTO(
                curso.getId(),
                curso.getNombre(),
                curso.getCategoria()
        );
    }
}