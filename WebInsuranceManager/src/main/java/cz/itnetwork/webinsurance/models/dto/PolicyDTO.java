package cz.itnetwork.webinsurance.models.dto;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PolicyDTO {

    private long policyId;
    private long clientId;

    @Positive(message = "Insurance amount must be positive")
    private int policyAmount;

    @NotBlank(message = "Define the subject of Insurance Policy")
    @Size(max = 20, message = "Description is too long")
    private String policyInsuranceSubject;

    @NotNull(message = "Select a valid date.")
    @FutureOrPresent(message = "This date is no longer available for booking.")
    private LocalDate policyStartDate;

    @NotNull(message = "Select a valid date.")
    private LocalDate policyEndDate;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="clientId")
    private ClientEntity clientEntity;

    //START Getters-Setters Region
    public long getPolicyId() {
        return policyId;
    }
    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
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
    public void setPolicyInsuranceSubject(String policyInsuranceSubject) {
        this.policyInsuranceSubject = policyInsuranceSubject;
    }

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

