package micolash89.foroAlura.dominio.usuario;

public record AuthUsuarioDTO(
        String message,
        String token

) {
    public AuthUsuarioDTO(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
