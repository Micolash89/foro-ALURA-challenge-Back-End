package micolash89.foroAlura.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import micolash89.foroAlura.dominio.usuario.LoginUsuarioDTO;
import micolash89.foroAlura.dominio.usuario.UsuarioDTO;
import micolash89.foroAlura.dominio.usuario.UsuarioService;
import micolash89.foroAlura.infra.security.AutenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AutenticacionRegister {

    @Autowired
    private AuthenticationManager authM;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutenticationService autenticationService;

    @Operation(
            summary = "Autentica un usuario",
            description = "Este endpoint verifica si el usuario existe y compara las contraseñas. Si la autenticación es exitosa, se genera y retorna un token.",
            tags = {"Login Autenticacion"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/login")
    @Transactional
    public ResponseEntity loginUsuario(@RequestBody @Valid LoginUsuarioDTO loginUsuarioDTO ) {
        // Va a checkear que el usuario exista comparar las contraseñas
        var usuario = usuarioService.validacionUsernameYPassword(loginUsuarioDTO);

        var authUsuario = autenticationService.autenticarYGenerarToken(loginUsuarioDTO.correoElectronico(), loginUsuarioDTO.contrasenia(), authM );

        return  ResponseEntity.status(HttpStatus.OK).body(authUsuario);

    }

    @Operation(
            summary = "Registra un nuevo usuario",
            description = "Este endpoint registra un nuevo usuario y genera un token de autenticación.",
            tags = {"Login Autenticacion"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO ) {

        var response = usuarioService.crearUsuario(usuarioDTO);

        var authUsuario = autenticationService.autenticarYGenerarToken(usuarioDTO.correoElectronico(),usuarioDTO.contrasenia(), authM );

        return  ResponseEntity.status(HttpStatus.CREATED).body(authUsuario);
    }

}
