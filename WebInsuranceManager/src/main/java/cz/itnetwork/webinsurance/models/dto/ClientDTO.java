package cz.itnetwork.webinsurance.models.dto;

import jakarta.validation.constraints.*;

public class ClientDTO {

    private long clientId;
    private long userId;

    @NotBlank(message = "Insert Name")
    @Size(max = 20, message = "Name is too long")
    private String clientName;

    @NotBlank(message = "Insert Surname")
    @Size(max = 20, message = "Surname is too long")
    private String clientSurname;

    @Email(message = "Insert a valid client email")
    @NotBlank(message = "Insert client email")
    @NotNull(message = "Insert client email")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "Insert a valid client email")
    private String clientEmail;

    @NotBlank(message = "Insert Phone Number")
    @Size(max = 9, message = "Surname is too long")
    @Pattern(regexp = "^\\d{9}$", message = "Insert a valid phone number with exactly 9 digits")
    @Pattern(regexp = "^\\+?\\d{1,4}?[-.\\s]?\\(?(\\d{1,3})?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$", message = "Insert a valid phone number")
    private String clientPhoneNumber;

    @NotBlank(message = "Insert Street Address")
    @Size(max = 30, message = "Street address is too long")
    private String clientStreetAddress;

    @NotBlank(message = "Insert City")
    @Size(max = 30, message = "City name is too long")
    private String clientCity;

    @NotBlank(message = "Insert Zip Code")
    @Size(max = 6, message = "Zip Code is too long")
    private String clientZipCode;

    @NotNull(message = "Gender is required")
    private ClientGender clientGender;

    public @NotNull(message = "Gender is required") ClientGender getClientGender() {
        return clientGender;
    }

    public void setClientGender(@NotNull(message = "Gender is required") ClientGender clientGender) {
        this.clientGender = clientGender;
    }

    //START Getters-Setters Region
    public long getClientId() {
        return clientId;
    }
    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getClientName() {return clientName;}
    public void setClientName(String clientName) {this.clientName = clientName;}

    public String getClientSurname() {return clientSurname;}
    public void setClientSurname(String clientSurname) {this.clientSurname = clientSurname;}

    public String getClientEmail() {return clientEmail;}
    public void setClientEmail(String clientEmail) {this.clientEmail = clientEmail;}

    public String getClientPhoneNumber() {return clientPhoneNumber;}
    public void setClientPhoneNumber(String clientPhoneNumber) {this.clientPhoneNumber = clientPhoneNumber;}

    public String getClientStreetAddress() {return clientStreetAddress;}
    public void setClientStreetAddress(String clientStreetAddress) {this.clientStreetAddress = clientStreetAddress;}

    public String getClientCity() {return clientCity;}
    public void setClientCity(String clientCity) {this.clientCity = clientCity;}

    public String getClientZipCode() {return clientZipCode;}
    public void setClientZipCode(String clientZipCode) {this.clientZipCode = clientZipCode;}
    //END Getter-Setter Region

    //FOR TABLE PURPOSE ONLY//
    public String getFormattedPhoneNumber() {
        if (clientPhoneNumber != null && clientPhoneNumber.length() == 9) {
            return clientPhoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1 $2 $3");
        }
        return clientPhoneNumber;
    }
}


