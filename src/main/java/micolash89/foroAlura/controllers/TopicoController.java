package micolash89.foroAlura.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import micolash89.foroAlura.dominio.topico.TopicoDTO;
import micolash89.foroAlura.dominio.topico.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Operation(
            summary = "Crear un nuevo tópico",
            description = "Este endpoint permite la creación de un nuevo tópico en el sistema. Recibe un objeto TopicoDTO y retorna el tópico creado junto con su URI.",
            tags = {"Tópicos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tópico creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<TopicoDTO> crearTopico(@RequestBody @Valid TopicoDTO topicoDTO, UriComponentsBuilder uriBuilder) {
        TopicoDTO nuevoTopico = topicoService.crearTopico(topicoDTO);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(nuevoTopico.id()).toUri();
        return ResponseEntity.created(uri).body(nuevoTopico);
    }

    @Operation(
            summary = "Listar tópicos",
            description = "Este endpoint permite listar los tópicos con paginación.",
            tags = {"Tópicos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de tópicos exitoso"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<Page<TopicoDTO>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @Operation(
            summary = "Detallar un tópico",
            description = "Este endpoint permite obtener los detalles de un tópico específico mediante su ID.",
            tags = {"Tópicos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalles del tópico obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> detallarTopico(@PathVariable Long id) {
        TopicoDTO topico = topicoService.detallarTopico(id);
        return ResponseEntity.ok(topico);
    }

    @Operation(
            summary = "Actualizar un tópico",
            description = "Este endpoint permite actualizar la información de un tópico específico mediante su ID.",
            tags = {"Tópicos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tópico actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoDTO topicoDTO) {
        TopicoDTO topicoActualizado = topicoService.actualizarTopico(id, topicoDTO);
        return ResponseEntity.ok(topicoActualizado);
    }

    @Operation(
            summary = "Eliminar un tópico",
            description = "Este endpoint permite eliminar un tópico específico mediante su ID.",
            tags = {"Tópicos"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tópico eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Tópico no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
