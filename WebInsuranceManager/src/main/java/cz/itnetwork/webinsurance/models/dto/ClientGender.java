package cz.itnetwork.webinsurance.models.dto;

public enum ClientGender {

    MALE("Male"),
    FEMALE("Female");

    private final String clientGenderName;

    ClientGender(String clientGenderName) {
        this.clientGenderName = clientGenderName;
    }

    public String getClientGenderName() {
        return clientGenderName;
    }
}