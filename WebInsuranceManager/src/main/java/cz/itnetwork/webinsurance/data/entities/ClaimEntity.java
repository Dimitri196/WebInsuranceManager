package cz.itnetwork.webinsurance.data.entities;

import cz.itnetwork.webinsurance.models.dto.ClaimType;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimId;

    @Column(nullable = false)
    private LocalDate claimDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClaimType claimType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String claimDescription;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private PolicyEntity policyEntity;

    //START Getters-Setters Region
    public PolicyEntity getPolicyEntity() {
        return policyEntity;
    }
    public void setPolicyEntity(PolicyEntity policyEntity) {
        this.policyEntity = policyEntity;
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

    public ClaimType getClaimType() {
        return claimType;
    }
    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public String getClaimDescription() {
        return claimDescription;
    }
    public void setClaimDescription(String claimDescription) {
        this.claimDescription = claimDescription;
    }
    //END Getters-Setters Region
}
