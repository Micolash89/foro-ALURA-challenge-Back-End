package micolash89.foroAlura.dominio.perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    boolean existsByNombre(String nombre);
}

