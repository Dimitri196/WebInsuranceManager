package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.ClaimEntity;
import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.data.repositories.ClaimRepository;
import cz.itnetwork.webinsurance.data.repositories.PolicyRepository;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.ClaimMapper;
import cz.itnetwork.webinsurance.models.exceptions.PolicyNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link ClaimService} interface for managing claims.
 * Provides methods for adding, retrieving, updating, and removing claims.
 */
@Service
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private PolicyServiceImpl policyService;

    /**
     * Adds a new claim and associates it with an existing policy.
     *
     * @param claimDTO the DTO containing the claim details to be added.
     */
    @Override
    public void addClaim(ClaimDTO claimDTO) {
        PolicyEntity policyEntity = policyService.getPolicyOrThrow(claimDTO.getPolicyId());

        if (!isClaimDateValid(claimDTO.getClaimDate(), policyEntity)) {
            throw new IllegalArgumentException("Claim date must be within the policy period.");
        }

        ClaimEntity claim = claimMapper.toEntity(claimDTO);
        claim.setPolicyEntity(policyEntity);

        ClaimEntity savedClaim = claimRepository.save(claim);
        claimMapper.toDTO(savedClaim); // Note: This line is redundant if the DTO is not used.
    }

    /**
     * Creates a new claim (alias for addClaim).
     *
     * @param claimDTO the DTO containing the claim details to be created.
     */
    public void create(ClaimDTO claimDTO) {
        addClaim(claimDTO);
    }

    /**
     * Checks if the claim date is within the policy period.
     *
     * @param claimDate the date of the claim.
     * @param policyEntity the policy associated with the claim.
     * @return true if the claim date is valid, false otherwise.
     */
    private boolean isClaimDateValid(LocalDate claimDate, PolicyEntity policyEntity) {
        return !claimDate.isBefore(policyEntity.getPolicyStartDate()) && !claimDate.isAfter(policyEntity.getPolicyEndDate());
    }

    /**
     * Retrieves claims associated with a specific policy ID.
     *
     * @param policyId the ID of the policy.
     * @return a list of ClaimDTOs representing the claims.
     */
    @Override
    public List<ClaimDTO> getClaimByPolicyId(long policyId) {
        List<ClaimEntity> claims = claimRepository.findAllByPolicyEntityPolicyId(policyId);
        return claims.stream()
                .map(claimMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all claims in the system.
     *
     * @return a list of ClaimDTOs representing all claims.
     */
    @Override
    public List<ClaimDTO> getAll() {
        return StreamSupport.stream(claimRepository.findAll().spliterator(), false)
                .map(claimMapper::toDTO)
                .toList();
    }

    /**
     * Retrieves a claim by its ID.
     *
     * @param claimId the ID of the claim.
     * @return the ClaimDTO representing the claim.
     */
    @Override
    public ClaimDTO getById(long claimId) {
        return claimRepository.findById(claimId)
                .map(claimMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Claim not found"));
    }

    /**
     * Edits an existing claim with updated details.
     *
     * @param claimDTO the DTO containing the updated claim information.
     */
    public void edit(ClaimDTO claimDTO) {
        ClaimEntity fetchedClaim = getClaimOrThrow(claimDTO.getClaimId());
        PolicyEntity policyEntity = policyRepository.findById(claimDTO.getPolicyId())
                .orElseThrow(PolicyNotFoundException::new);

        if (!isClaimDateWithinPolicyPeriod(claimDTO.getClaimDate(), policyEntity)) {
            throw new IllegalArgumentException("Claim date must be within the policy period.");
        }

        claimMapper.updateClaimEntity(claimDTO, fetchedClaim);
        fetchedClaim.setPolicyEntity(policyEntity);

        claimRepository.save(fetchedClaim);
    }

    /**
     * Checks if the claim date is within the policy period.
     *
     * @param claimDate the date of the claim.
     * @param policyEntity the policy associated with the claim.
     * @return true if the claim date is within the policy period, false otherwise.
     */
    private boolean isClaimDateWithinPolicyPeriod(LocalDate claimDate, PolicyEntity policyEntity) {
        return !claimDate.isBefore(policyEntity.getPolicyStartDate()) && !claimDate.isAfter(policyEntity.getPolicyEndDate());
    }

    /**
     * Removes a claim by its ID.
     *
     * @param claimId the ID of the claim to be removed.
     */
    @Override
    public void remove(long claimId) {
        ClaimEntity fetchedClaim = getClaimOrThrow(claimId);
        claimRepository.delete(fetchedClaim);
    }

    /**
     * Retrieves a ClaimEntity by its ID or throws an exception if not found.
     *
     * @param claimId the ID of the claim.
     * @return the ClaimEntity object representing the claim.
     * @throws PolicyNotFoundException if the claim is not found.
     */
    private ClaimEntity getClaimOrThrow(long claimId) {
        return claimRepository
                .findById(claimId)
                .orElseThrow(PolicyNotFoundException::new);
    }
}
