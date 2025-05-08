package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.models.dto.ClaimDTO;

import java.util.List;

/**
 * Service interface for managing {@link ClaimDTO} objects.
 * Provides methods for creating, retrieving, updating, and deleting claims.
 */
public interface ClaimService {

    /**
     * Creates a new claim.
     *
     * @param claim the {@link ClaimDTO} object to be created.
     */
    void create(ClaimDTO claim);

    /**
     * Retrieves all claims.
     *
     * @return a list of {@link ClaimDTO} objects representing all claims.
     */
    List<ClaimDTO> getAll();

    /**
     * Adds a new claim.
     *
     * @param claimDTO the {@link ClaimDTO} object to be added.
     */
    void addClaim(ClaimDTO claimDTO);

    /**
     * Retrieves a claim by its ID.
     *
     * @param claimId the ID of the claim to retrieve.
     * @return the {@link ClaimDTO} object representing the claim, or {@code null} if not found.
     */
    ClaimDTO getById(long claimId);

    /**
     * Retrieves all claims associated with a specific policy.
     *
     * @param policyId the ID of the policy to filter claims by.
     * @return a list of {@link ClaimDTO} objects associated with the specified policy ID.
     */
    List<ClaimDTO> getClaimByPolicyId(long policyId);

    /**
     * Updates an existing claim.
     *
     * @param claim the {@link ClaimDTO} object with updated information.
     */
    void edit(ClaimDTO claim);

    /**
     * Removes a claim by its ID.
     *
     * @param claimId the ID of the claim to be removed.
     */
    void remove(long claimId);
}
