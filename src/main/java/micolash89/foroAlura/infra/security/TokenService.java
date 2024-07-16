package micolash89.foroAlura.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import micolash89.foroAlura.dominio.usuario.Usuario;
import micolash89.foroAlura.infra.exepciones.TokenVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String firmaToken;


    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(firmaToken);
            return JWT.create()
                    .withIssuer("Foro Alura")
                    //Para quien es asignado el token
                    .withSubject(usuario.getCorreoElectronico())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            System.out.println("Error al generar el token " + exception.getMessage());
            return "";
        }
    }


    //Para a quien fue asignado el token
    public String getSubject(String token) {

        if (token == null || token.isEmpty()) {
            throw new TokenVerificationException("El token no puede estar vacío o nulo!");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(firmaToken);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("Foro Alura")
                    .build()
                    .verify(token);

            String subject = verifier.getSubject();

            if (subject == null || subject.isEmpty()) {
                throw new TokenVerificationException("El token es válido pero no contiene un sujeto válido!");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            throw new TokenVerificationException("Falló la verificación del token: " + exception.getMessage());
        }

    }


    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
