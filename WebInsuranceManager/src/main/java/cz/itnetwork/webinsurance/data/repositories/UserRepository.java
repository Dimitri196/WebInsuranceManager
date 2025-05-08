package cz.itnetwork.webinsurance.data.repositories;

import cz.itnetwork.webinsurance.data.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link UserEntity} objects.
 * Extends {@link CrudRepository} to provide basic CRUD operations and custom queries for {@link UserEntity} entities.
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Retrieves a {@link UserEntity} by its email.
     *
     * @param email the email of the user to retrieve.
     * @return an {@link Optional} containing the {@link UserEntity} if found, or an empty {@link Optional} if not.
     */
    Optional<UserEntity> findByEmail(String email);
}
