package cz.itnetwork.webinsurance.models.exceptions;

/**
 * PasswordsDoNotEqualException is a custom exception that is thrown when the passwords
 * provided for registration or updating a user do not match. It extends {@link RuntimeException},
 * making it an unchecked exception used to indicate a mismatch in password fields.
 */
public class PasswordsDoNotEqualException extends RuntimeException {

    /**
     * Constructs a new PasswordsDoNotEqualException with no detail message.
     */
    public PasswordsDoNotEqualException() {
        super();
    }
}
