package micolash89.foroAlura.dominio.perfil;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Transactional
    public PerfilDTO crearPerfil(PerfilDTO perfilDTO) {
        if (perfilRepository.existsByNombre(perfilDTO.nombre())) {
            throw new IllegalArgumentException("Ya existe un perfil con ese nombre.");
        }

        Perfil perfil = new Perfil(null, perfilDTO.nombre());
        Perfil perfilGuardado = perfilRepository.save(perfil);
        return convertirADTO(perfilGuardado);
    }

    private PerfilDTO convertirADTO(Perfil perfil) {
        return new PerfilDTO(perfil.getId(), perfil.getNombre());
    }
}