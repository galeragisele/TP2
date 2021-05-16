package persona;

/**
 *
 * @author gisele.galera
 */
public class AlumnoException extends PersonaException {

    /**
     * instancia una clase exception llamando al padre Exception
     * @param mensaje
     */
    public AlumnoException(String mensaje) {
        super(mensaje); // super hace referencia al padre - es el puntero a la clase padre
    }
    
}
