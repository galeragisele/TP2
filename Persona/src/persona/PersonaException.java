package persona;

/**
 *
 * @author gisele.galera
 */
public class PersonaException extends Exception {

    /**
     * instancia una clase exception llamando al padre Exception
     * @param mensaje
     */
    public PersonaException(String mensaje) {
        super(mensaje); // super hace referencia al padre - es el puntero a la clase padre
    }
    
}
