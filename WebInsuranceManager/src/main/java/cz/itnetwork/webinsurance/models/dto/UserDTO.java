package cz.itnetwork.webinsurance.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "Insert user name")
    private String firstName;

    @NotBlank(message = "Insert user surname")
    private String lastName;

    @Email(message = "Insert a valid user email")
    @NotBlank(message = "Insert a valid user email")
    private String email;

    @NotBlank(message = "Enter your user password")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String password;

    @NotBlank(message = "Re-Enter your user password")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String confirmPassword;

    //START Getters-Setters Region
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    //END Getters-Setters Region
}
