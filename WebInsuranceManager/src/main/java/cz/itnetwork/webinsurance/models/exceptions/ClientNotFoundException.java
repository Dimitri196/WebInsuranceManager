package cz.itnetwork.webinsurance.models.exceptions;

/**
 * ClientNotFoundException is a custom exception that is thrown when a requested client
 * cannot be found in the system. It extends {@link RuntimeException}, allowing it to be
 * used for unchecked exceptions in scenarios where a client lookup fails.
 */
public class ClientNotFoundException extends RuntimeException {

    /**
     * Constructs a new ClientNotFoundException with no detail message.
     */
    public ClientNotFoundException() {
        super();
    }
}
