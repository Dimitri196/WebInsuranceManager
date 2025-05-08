package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service interface for managing users in the application.
 * Extends the {@link UserDetailsService} interface provided by Spring Security
 * to handle user authentication and retrieval of user details.
 */
public interface UserService extends UserDetailsService {

    /**
     * Creates a new user in the system.
     * The user can either be a regular user or an admin, based on the {@code isAdmin} flag.
     *
     * @param user the UserDTO containing the user's details.
     * @param isAdmin flag indicating whether the user should have admin privileges.
     */
    void create(UserDTO user, boolean isAdmin);

    /**
     * Loads a user based on the provided username. This method is used
     * for authentication purposes by Spring Security.
     *
     * @param username the username to search for.
     * @return the UserDetails associated with the given username.
     */
    @Override
    UserDetails loadUserByUsername(String username);
}
