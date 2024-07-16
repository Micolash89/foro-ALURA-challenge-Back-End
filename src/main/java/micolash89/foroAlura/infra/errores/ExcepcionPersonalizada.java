package micolash89.foroAlura.infra.errores;

public class ExcepcionPersonalizada extends RuntimeException {
    public ExcepcionPersonalizada(String mensaje) {
        super(mensaje);
    }
}