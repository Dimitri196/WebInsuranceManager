package cz.itnetwork.webinsurance.data.entities;

import cz.itnetwork.webinsurance.models.dto.PolicyType;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PolicyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long policyId;

    @Column(nullable = false)
    private int policyAmount;

    @Column(nullable = false)
    private String policyInsuranceSubject;

    @Column(nullable = false)
    private LocalDate policyStartDate;

    @Column(nullable = false)
    private LocalDate policyEndDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    @OneToMany(mappedBy = "policyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClaimEntity> claimEntities;

    //START Getters-Setters Region
    public long getPolicyId() {
        return policyId;
    }
    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public int getPolicyAmount() {
        return policyAmount;
    }
    public void setPolicyAmount(int policyAmount) {
        this.policyAmount = policyAmount;
    }

    public String getPolicyInsuranceSubject() {
        return policyInsuranceSubject;
    }
    public void setPolicyInsuranceSubject(String policyInsuranceSubject) {this.policyInsuranceSubject = policyInsuranceSubject; }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }
    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return policyEndDate;
    }
    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }
    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }
    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
    //END Getters-Setters Region
}