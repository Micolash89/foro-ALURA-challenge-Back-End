package micolash89.foroAlura.dominio.usuario;

public record UsuarioDTO(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasenia,
        String perfiles
) {
    public UsuarioDTO(Usuario usuarioGuardado) {
        this(usuarioGuardado.getId(),usuarioGuardado.getNombre(),usuarioGuardado.getCorreoElectronico(),null,usuarioGuardado.getPerfiles());
    }
}