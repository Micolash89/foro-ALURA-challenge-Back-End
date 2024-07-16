package micolash89.foroAlura.dominio.curso;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String categoria;

    // Getters y setters

    public Curso(CursoDTO registroCursoDTO) {
        this.nombre= registroCursoDTO.nombre();
        this.categoria=registroCursoDTO.categoria();
    }
}