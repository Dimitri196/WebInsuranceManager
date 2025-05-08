package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;

import java.util.List;

/**
 * Service interface for managing policies.
 * Provides methods for creating, retrieving, updating, and deleting policies.
 */
public interface PolicyService {

    /**
     * Creates a new policy.
     *
     * @param policy the PolicyDTO containing the details of the policy to be created.
     */
    void create(PolicyDTO policy);

    /**
     * Retrieves all policies in the system.
     *
     * @return a list of PolicyDTOs representing all policies.
     */
    List<PolicyDTO> getAll();

    /**
     * Adds a new policy and associates it with an existing client.
     *
     * @param policyDTO the DTO containing the policy details to add.
     * @return the added policy as a PolicyDTO.
     */
    PolicyDTO addPolicy(PolicyDTO policyDTO);

    /**
     * Retrieves a policy by its ID.
     *
     * @param policyId the ID of the policy.
     * @return the PolicyDTO representing the policy.
     */
    PolicyDTO getById(long policyId);

    /**
     * Retrieves all policies associated with a specific client ID.
     *
     * @param clientId the ID of the client.
     * @return a list of PolicyDTOs for the specified client.
     */
    List<PolicyDTO> getPoliciesByClientId(long clientId);

    /**
     * Edits an existing policy with updated details.
     *
     * @param policy the PolicyDTO containing the updated policy information.
     */
    void edit(PolicyDTO policy);

    /**
     * Removes a policy by its ID.
     *
     * @param policyId the ID of the policy to be removed.
     */
    void remove(long policyId);

    /**
     * Retrieves a PolicyEntity by its ID.
     *
     * @param policyId the ID of the policy.
     * @return the PolicyEntity object representing the policy.
     */
    PolicyEntity getPolicyEntityById(long policyId);
}
