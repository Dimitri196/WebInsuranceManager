package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.models.dto.UserDTO;
import cz.itnetwork.webinsurance.models.exceptions.DuplicateEmailException;
import cz.itnetwork.webinsurance.models.exceptions.PasswordsDoNotEqualException;
import cz.itnetwork.webinsurance.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * AccountController handles user-related account operations such as login, registration, and password recovery.
 * It serves as a front-facing controller for account management pages and actions.
 * <p>
 * Mappings include rendering login, registration, and forgot-password pages, as well as handling the registration process.
 * </p>
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    /**
     * Renders the login page for users to enter their credentials.
     *
     * @return the path to the login HTML page.
     */
    @GetMapping("login")
    public String renderLogin() {
        return "/pages/account/login.html";
    }

    /**
     * Renders the registration page where users can sign up for an account.
     *
     * @param userDTO the data transfer object that holds user information for the registration form.
     * @return the path to the registration page.
     */
    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO) {
        return "/pages/account/register";
    }

    /**
     * Handles the user registration process. Validates the input, checks for errors such as duplicate email or
     * password mismatch, and creates a new user account if successful.
     *
     * @param userDTO            the data transfer object that contains user input for registration.
     * @param result             holds the results of input validation and any binding errors.
     * @param redirectAttributes used to pass attributes after redirecting to the login page on successful registration.
     * @return the path to the registration page if there are errors, or a redirection to the login page upon success.
     */
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute UserDTO userDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            return "/pages/account/register";
        }

        try {
            System.out.println("First Name: " + userDTO.getFirstName());
            System.out.println("Last Name: " + userDTO.getLastName());

            userService.create(userDTO, false);
        } catch (DuplicateEmailException e) {
            result.rejectValue("email", "error", "Email is already in use.");
            return "/pages/account/register";
        } catch (PasswordsDoNotEqualException e) {
            result.rejectValue("password", "error", "The passwords do not match.");
            result.rejectValue("confirmPassword", "error", "The passwords do not match.");
            return "/pages/account/register";
        }

        redirectAttributes.addFlashAttribute("success", "User registered.");
        return "redirect:/account/login";
    }

    /**
     * Renders the forgot password page, where users can initiate the password recovery process.
     *
     * @return the path to the forgot-password page.
     */
    @GetMapping("/forgot-password")
    public String renderForgotPassword() {
        return "/pages/account/forgot-password";
    }
}
