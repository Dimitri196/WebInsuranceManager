package cz.itnetwork.webinsurance.models.dto;

import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@ValidClaimDate(message = "Claim date must be within the policy period.")
public class ClaimDTO {

    private long policyId;
    private long claimId;

    @NotNull(message = "Select a valid date.")
    private LocalDate claimDate;

    @Enumerated(EnumType.STRING)
    private ClaimType claimType;

    @NotNull(message = "Describe your claim")
    @NotBlank(message = "Describe your claim")
    private String claimDescription;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="policyId")
    private PolicyEntity policyEntity;

    //START Getters-Setters Region
    public long getPolicyId() {
        return policyId;
    }
    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public long getClaimId() {
        return claimId;
    }
    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimDescription() {return claimDescription; }

    public void setClaimDescription(String claimDescription) {this.claimDescription = claimDescription; }

    public PolicyEntity getPolicyEntity() {
        return policyEntity;
    }
    public void setPolicyEntity(PolicyEntity policyEntity) {
        this.policyEntity = policyEntity;
    }

    public ClaimType getClaimType() {
        return claimType;
    }
    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }
    //END Getters-Setters Region
}
