package cz.itnetwork.webinsurance.models.exceptions;

/**
 * PolicyNotFoundException is a custom exception that is thrown when a requested policy
 * cannot be found in the system. It extends {@link RuntimeException}, making it an unchecked
 * exception used to handle scenarios where a policy lookup fails.
 */
public class PolicyNotFoundException extends RuntimeException {

    /**
     * Constructs a new PolicyNotFoundException with no detail message.
     */
    public PolicyNotFoundException() {
        super();
    }
}
