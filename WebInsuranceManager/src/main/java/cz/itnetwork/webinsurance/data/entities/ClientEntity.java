package cz.itnetwork.webinsurance.data.entities;

import cz.itnetwork.webinsurance.models.dto.ClientGender;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false)
    private String clientSurname;

    @Column(nullable = false)
    private String clientEmail;

    @Column(nullable = false)
    private String clientPhoneNumber;

    @Column(nullable = false)
    private String clientStreetAddress;

    @Column(nullable = false)
    private String clientCity;

    @Column(nullable = false)
    private String clientZipCode;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PolicyEntity> policyEntity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientGender clientGender;

    //START Getters-Setters Region
    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }
    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientEmail() {
        return clientEmail;
    }
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientStreetAddress() {
        return clientStreetAddress;
    }
    public void setClientStreetAddress(String clientStreetAddress) {
        this.clientStreetAddress = clientStreetAddress;
    }

    public String getClientCity() {
        return clientCity;
    }
    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientZipCode() {
        return clientZipCode;
    }
    public void setClientZipCode(String clientZipCode) {
        this.clientZipCode = clientZipCode;
    }

    public List<PolicyEntity> getPolicyEntity() {
        return policyEntity;
    }
    public void setPolicyEntity(List<PolicyEntity> policyEntity) {
        this.policyEntity = policyEntity;
    }

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ClientGender getClientGender() { return clientGender; }
    public void setClientGender(ClientGender clientGender) { this.clientGender = clientGender; }
    //END Getters-Setters Region
}

