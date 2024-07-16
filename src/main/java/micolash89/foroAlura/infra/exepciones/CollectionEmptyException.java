package micolash89.foroAlura.infra.exepciones;

public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException(String message) {
        super(message);
    }
}
