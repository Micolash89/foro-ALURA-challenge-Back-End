package micolash89.foroAlura.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import micolash89.foroAlura.dominio.curso.CursoDTO;
import micolash89.foroAlura.dominio.curso.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(
            summary = "Crear un nuevo curso",
            description = "Este endpoint permite la creación de un nuevo curso en el sistema. Recibe un objeto CursoDTO y retorna el curso creado.",
            tags = {"Cursos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<CursoDTO> crearCurso(@RequestBody @Valid CursoDTO cursoDTO) {
        CursoDTO nuevoCurso = cursoService.crearCurso(cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
    }
}