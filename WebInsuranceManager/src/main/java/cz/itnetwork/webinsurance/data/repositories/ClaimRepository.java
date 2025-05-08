package cz.itnetwork.webinsurance.data.repositories;

import cz.itnetwork.webinsurance.data.entities.ClaimEntity;
import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Repository interface for performing CRUD operations on {@link ClaimEntity} objects.
 * Extends {@link CrudRepository} to provide basic CRUD operations and additional custom queries.
 */
public interface ClaimRepository extends CrudRepository<ClaimEntity, Long> {

    /**
     * Retrieves all {@link ClaimEntity} instances associated with the given {@link PolicyEntity}.
     *
     * @param policyEntity the {@link PolicyEntity} to filter claims by.
     * @return a list of {@link ClaimEntity} objects associated with the specified policy.
     */
    List<ClaimEntity> getAllByPolicyEntity(PolicyEntity policyEntity);

    /**
     * Deletes all {@link ClaimEntity} instances associated with the given {@link PolicyEntity}.
     *
     * @param policyEntity the {@link PolicyEntity} whose associated claims are to be deleted.
     */
    void deleteAllByPolicyEntity(PolicyEntity policyEntity);

    /**
     * Retrieves all {@link ClaimEntity} instances associated with the policy specified by the given policy ID.
     *
     * @param policyId the ID of the policy to filter claims by.
     * @return a list of {@link ClaimEntity} objects associated with the specified policy ID.
     */
    List<ClaimEntity> findAllByPolicyEntityPolicyId(Long policyId);

    /**
     * Finds the count of claims grouped by their type.
     *
     * @return a list of object arrays, each containing a claim type and its associated count.
     *         The result format is: [{@code claimType1, count1}, {@code claimType2, count2}, ...].
     */
    @Query("SELECT c.claimType, COUNT(c) FROM ClaimEntity c GROUP BY c.claimType")
    List<Object[]> findClaimsGroupedByType();
}
