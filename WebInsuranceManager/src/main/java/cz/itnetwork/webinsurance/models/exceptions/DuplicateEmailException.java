package cz.itnetwork.webinsurance.models.exceptions;

/**
 * DuplicateEmailException is a custom exception that is thrown when an attempt is made
 * to register or update a user with an email that already exists in the system.
 * It extends {@link RuntimeException}, making it an unchecked exception.
 */
public class DuplicateEmailException extends RuntimeException {

    /**
     * Constructs a new DuplicateEmailException with no detail message.
     */
    public DuplicateEmailException() {
        super();
    }
}
