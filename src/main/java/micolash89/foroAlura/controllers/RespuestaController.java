package micolash89.foroAlura.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import micolash89.foroAlura.dominio.respuesta.RespuestaDTO;
import micolash89.foroAlura.dominio.respuesta.RespuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Operation(
            summary = "Crear una nueva respuesta",
            description = "Este endpoint permite la creación de una nueva respuesta en el sistema. Recibe un objeto RespuestaDTO y retorna la respuesta creada.",
            tags = {"Respuestas"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Respuesta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<RespuestaDTO> crearRespuesta(@RequestBody @Valid RespuestaDTO respuestaDTO) {
        RespuestaDTO nuevaRespuesta = respuestaService.crearRespuesta(respuestaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRespuesta);
    }
}