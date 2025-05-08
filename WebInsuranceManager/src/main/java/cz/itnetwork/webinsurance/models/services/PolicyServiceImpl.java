package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.data.repositories.ClientRepository;
import cz.itnetwork.webinsurance.data.repositories.PolicyRepository;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.PolicyMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import cz.itnetwork.webinsurance.models.exceptions.ClientNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link PolicyService} interface for handling policies.
 * It provides methods for adding, editing, removing, and fetching policies.
 */
@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Autowired
    PolicyMapper policyMapper;

    @Autowired
    private ClientServiceImpl clientService;

    /**
     * Adds a new policy for a specific client.
     *
     * @param policyDTO the DTO representing the policy to add.
     * @return the added policy as a DTO.
     */
    @Override
    public PolicyDTO addPolicy(PolicyDTO policyDTO) {
        PolicyEntity policy = policyMapper.toEntity(policyDTO);
        ClientEntity clientEntity = clientService.getClientOrThrow(policyDTO.getClientEntity().getClientId());
        policy.setClientEntity(clientEntity);
        PolicyEntity saved = policyRepository.save(policy);
        return policyMapper.toDTO(saved);
    }

    /**
     * Creates a new policy and associates it with an existing client.
     *
     * @param policyDTO the DTO representing the policy to create.
     * @throws IllegalArgumentException if the client is not found.
     */
    public void create(PolicyDTO policyDTO) {
        ClientEntity clientEntity = clientService.getClientEntityById(policyDTO.getClientEntity().getClientId());
        if (clientEntity == null) {
            throw new IllegalArgumentException("Client not found");
        }
        policyDTO.setClientEntity(clientEntity);
        PolicyEntity policyEntity = policyMapper.toEntity(policyDTO);
        policyRepository.save(policyEntity);
    }

    /**
     * Retrieves all policies associated with a specific client ID.
     *
     * @param clientId the ID of the client.
     * @return a list of PolicyDTOs.
     */
    @Override
    public List<PolicyDTO> getPoliciesByClientId(long clientId) {
        List<PolicyEntity> policies = policyRepository.findAllByClientEntityClientId(clientId);
        return policies.stream()
                .map(policyMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all policies in the system.
     *
     * @return a list of all PolicyDTOs.
     */
    @Override
    public List<PolicyDTO> getAll() {
        return StreamSupport.stream(policyRepository.findAll().spliterator(), false)
                .map(i -> policyMapper.toDTO(i))
                .toList();
    }

    /**
     * Retrieves a policy by its ID.
     *
     * @param policyId the ID of the policy.
     * @return the PolicyDTO representing the policy.
     */
    @Override
    public PolicyDTO getById(long policyId) {
        PolicyEntity fetchedPolicy = getPolicyOrThrow(policyId);
        return policyMapper.toDTO(fetchedPolicy);
    }

    /**
     * Edits an existing policy with updated details.
     *
     * @param policyDTO the DTO representing the updated policy.
     */
    @Override
    public void edit(PolicyDTO policyDTO) {
        PolicyEntity fetchedPolicy = getPolicyOrThrow(policyDTO.getPolicyId());
        ClientEntity clientEntity = clientRepository.findById(policyDTO.getClientId())
                .orElseThrow(ClientNotFoundException::new);
        policyMapper.updatePolicyEntity(policyDTO, fetchedPolicy);
        fetchedPolicy.setClientEntity(clientEntity);
        policyRepository.save(fetchedPolicy);
    }

    /**
     * Removes a policy by its ID.
     *
     * @param policyId the ID of the policy to remove.
     */
    @Override
    public void remove(long policyId) {
        PolicyEntity fetchedPolicy = getPolicyOrThrow(policyId);
        policyRepository.delete(fetchedPolicy);
    }

    /**
     * Retrieves a PolicyEntity by its ID.
     *
     * @param policyId the ID of the policy.
     * @return the PolicyEntity object.
     */
    @Override
    public PolicyEntity getPolicyEntityById(long policyId) {
        return getPolicyOrThrow(policyId);
    }

    /**
     * Helper method to retrieve a PolicyEntity or throw an exception if not found.
     *
     * @param policyId the ID of the policy.
     * @return the PolicyEntity object.
     * @throws ClientNotFoundException if the policy is not found.
     */
    PolicyEntity getPolicyOrThrow(long policyId) {
        return policyRepository
                .findById(policyId)
                .orElseThrow(ClientNotFoundException::new);
    }
}
