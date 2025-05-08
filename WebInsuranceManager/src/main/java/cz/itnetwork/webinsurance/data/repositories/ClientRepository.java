package cz.itnetwork.webinsurance.data.repositories;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link ClientEntity} objects.
 * Extends {@link CrudRepository} to provide basic CRUD operations and additional custom queries.
 */
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    /**
     * Retrieves all {@link ClientEntity} instances associated with the given user email.
     *
     * @param email the email of the user to filter clients by.
     * @return a list of {@link ClientEntity} objects associated with the specified email.
     */
    List<ClientEntity> getAllByUserEmail(String email);
}
