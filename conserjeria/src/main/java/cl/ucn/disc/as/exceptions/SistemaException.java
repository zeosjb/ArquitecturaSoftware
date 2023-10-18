package cl.ucn.disc.as.exceptions;

import javax.persistence.PersistenceException;

/**
 *
 */
public class SistemaException extends RuntimeException {
    /**
     * The constructor
     * @param message the reason
     * @param ex bubble exception
     */
    public SistemaException(String message, PersistenceException ex) {
        super(message, ex);
    }
}
