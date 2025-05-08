package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.repositories.ClaimRepository;
import cz.itnetwork.webinsurance.data.repositories.ClientRepository;
import cz.itnetwork.webinsurance.data.repositories.PolicyRepository;
import cz.itnetwork.webinsurance.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving various statistics related to clients, policies, users, and claims.
 */
@Service
public class StatisticsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClaimRepository claimRepository;

    /**
     * Retrieves the total number of clients in the system.
     *
     * @return the total count of clients.
     */
    public long getTotalClients() {
        return clientRepository.count();
    }

    /**
     * Retrieves the total number of policies in the system.
     *
     * @return the total count of policies.
     */
    public long getTotalPolicies() {
        return policyRepository.count();
    }

    /**
     * Retrieves the total number of users in the system.
     *
     * @return the total count of users.
     */
    public long getTotalUsers() {
        return userRepository.count();
    }

    /**
     * Retrieves the total number of claims in the system.
     *
     * @return the total count of claims.
     */
    public long getTotalClaims() {
        return claimRepository.count();
    }

    /**
     * Retrieves the policies grouped by their type.
     *
     * @return a list of policy types and their counts.
     */
    public List<Object[]> getPoliciesByType() {
        return policyRepository.findPoliciesGroupedByType();
    }

    /**
     * Retrieves the claims grouped by their type.
     *
     * @return a list of claim types and their counts.
     */
    public List<Object[]> getClaimsByType() {
        return claimRepository.findClaimsGroupedByType();
    }

    /**
     * Placeholder method for retrieving policies grouped by user name.
     * The implementation is currently not provided.
     *
     * @return null as no implementation is provided.
     */
    public List<Object[]> getPoliciesByUserName() {
        return null;
    }

    /**
     * Placeholder method for retrieving claims grouped by user name.
     * The implementation is currently not provided.
     *
     * @return null as no implementation is provided.
     */
    public List<Object[]> getClaimsByUserName() {
        return null;
    }
}
