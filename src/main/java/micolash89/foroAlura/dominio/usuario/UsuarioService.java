package micolash89.foroAlura.dominio.usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByCorreoElectronico(usuarioDTO.correoElectronico())) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo electrónico.");
        }

        Usuario usuario = new Usuario(
                null,
                usuarioDTO.nombre(),
                usuarioDTO.correoElectronico(),
                passwordEncoder.encode(usuarioDTO.contrasenia()),
                usuarioDTO.perfiles()
        );

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return  new UsuarioDTO(usuarioGuardado);
    }


    public boolean validacionUsernameYPassword(LoginUsuarioDTO loginUsuarioDTO) {

        //Buscamos el usuario
        var usuario = (Usuario) usuarioRepository.findBycorreoElectronico(loginUsuarioDTO.correoElectronico());

        if (usuario==null){
            throw new ValidationException("No se encontro usuario con ese username!");
        }

        if (usuario != null){

            if(!passwordEncoder.matches(loginUsuarioDTO.contrasenia(),usuario.getPassword() ) ){
                throw new ValidationException("Userbane o contraseña incorrecta!");
            }
            return true;
        }
        return false;
    }

    public String obtenerUsernameEnSesion(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return auth.getName();
        }
        return null;
    }
}