package micolash89.foroAlura.infra.exepciones;

public class ValidacionDeIntegridad extends RuntimeException{

    public ValidacionDeIntegridad(String s) {
        super(s);
    }

}
