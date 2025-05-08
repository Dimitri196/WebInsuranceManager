package cz.itnetwork.webinsurance.data.repositories;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link PolicyEntity} objects.
 * Extends {@link CrudRepository} to provide basic CRUD operations and additional custom queries for {@link PolicyEntity} entities.
 */
public interface PolicyRepository extends CrudRepository<PolicyEntity, Long> {

    /**
     * Retrieves all {@link PolicyEntity} instances associated with the given {@link ClientEntity}.
     *
     * @param clientEntity the {@link ClientEntity} to filter policies by.
     * @return a list of {@link PolicyEntity} objects associated with the specified client.
     */
    List<PolicyEntity> getAllByClientEntity(ClientEntity clientEntity);

    /**
     * Deletes all {@link PolicyEntity} instances associated with the given {@link ClientEntity}.
     *
     * @param clientEntity the {@link ClientEntity} whose associated policies are to be deleted.
     */
    void deleteAllByClientEntity(ClientEntity clientEntity);

    /**
     * Retrieves all {@link PolicyEntity} instances associated with the policy specified by the given client ID.
     *
     * @param clientId the ID of the client to filter policies by.
     * @return a list of {@link PolicyEntity} objects associated with the specified client ID.
     */
    List<PolicyEntity> findAllByClientEntityClientId(Long clientId);

    /**
     * Finds the count of policies grouped by their type.
     *
     * @return a list of object arrays, each containing a policy type and its associated count.
     *         The result format is: [{@code policyType1, count1}, {@code policyType2, count2}, ...].
     */
    @Query("SELECT p.policyType, COUNT(p) FROM PolicyEntity p GROUP BY p.policyType")
    List<Object[]> findPoliciesGroupedByType();
}
