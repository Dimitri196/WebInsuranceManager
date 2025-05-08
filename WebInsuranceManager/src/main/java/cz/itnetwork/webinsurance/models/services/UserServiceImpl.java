package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.UserEntity;
import cz.itnetwork.webinsurance.data.repositories.UserRepository;
import cz.itnetwork.webinsurance.models.dto.UserDTO;
import cz.itnetwork.webinsurance.models.exceptions.DuplicateEmailException;
import cz.itnetwork.webinsurance.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link UserService} interface that handles user creation
 * and loading user details for authentication.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Creates a new user in the system.
     * If the password and confirm password do not match, throws {@link PasswordsDoNotEqualException}.
     * If the email already exists, throws {@link DuplicateEmailException}.
     *
     * @param user the UserDTO containing user details.
     * @param isAdmin flag indicating whether the user has admin privileges.
     * @throws PasswordsDoNotEqualException if the passwords do not match.
     * @throws DuplicateEmailException if the email is already in use.
     */
    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new PasswordsDoNotEqualException();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {
            usersRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    /**
     * Loads a user by their username (in this case, email).
     * This method is used by Spring Security to authenticate users.
     *
     * @param username the email of the user to search for.
     * @return the UserDetails for the user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
}

