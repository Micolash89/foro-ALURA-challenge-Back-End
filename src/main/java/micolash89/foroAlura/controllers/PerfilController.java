package micolash89.foroAlura.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import micolash89.foroAlura.dominio.perfil.PerfilDTO;
import micolash89.foroAlura.dominio.perfil.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @Operation(
            summary = "Crear un nuevo perfil",
            description = "Este endpoint permite la creación de un nuevo perfil en el sistema. Recibe un objeto PerfilDTO y retorna el perfil creado.",
            tags = {"Perfiles"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Perfil creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<PerfilDTO> crearPerfil(@RequestBody @Valid PerfilDTO perfilDTO) {
        PerfilDTO nuevoPerfil = perfilService.crearPerfil(perfilDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPerfil);
    }
}