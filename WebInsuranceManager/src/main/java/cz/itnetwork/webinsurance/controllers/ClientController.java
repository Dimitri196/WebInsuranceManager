package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.data.entities.UserEntity;
import cz.itnetwork.webinsurance.data.repositories.UserRepository;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.ClientMapper;
import cz.itnetwork.webinsurance.models.exceptions.ClientNotFoundException;
import cz.itnetwork.webinsurance.models.services.ClientService;
import cz.itnetwork.webinsurance.models.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Optional;

/**
 * ClientController handles operations related to managing client profiles.
 * This includes rendering summary pages, creating new clients, viewing client details, editing, and deleting clients.
 * It also includes handling specific exceptions such as when a client is not found.
 */
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Renders the summary page displaying a list of clients.
     * If the authenticated user is an admin, all clients are shown; otherwise, only the clients associated with the current user are displayed.
     *
     * @param model the model to which client data and userRepository are added.
     * @return the path to the client summary page.
     */
    @GetMapping
    public String renderSummary(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<UserEntity> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            boolean isAdmin = userOptional.get().isAdmin();

            List<ClientDTO> clients;

            if (isAdmin) {
                clients = clientService.getAll();
            } else {
                clients = clientService.getMyClients(userEmail);
            }

            model.addAttribute("clients", clients);
            model.addAttribute("userRepository", userRepository);
            model.addAttribute("user", userOptional.get());
        }
        return "pages/clients/summary";
    }

    /**
     * Renders the form for creating a new client.
     *
     * @param client the ClientDTO object to be used in the form.
     * @return the path to the client creation page.
     */
    @GetMapping("create")
    public String renderCreateForm(@ModelAttribute ClientDTO client) {
        return "pages/clients/create";
    }

    /**
     * Handles the creation of a new client. Validates the input, checks for errors, and saves the client if valid.
     * Associates the client with the currently authenticated user.
     *
     * @param client              the ClientDTO containing the client information.
     * @param result              binding result for validation errors.
     * @param redirectAttributes  used to pass messages after redirecting, such as success or error messages.
     * @return the path to the client creation form if there are errors, or redirects to the clients summary page upon successful creation.
     */
    @PostMapping("create")
    public String createClient(
            @Valid @ModelAttribute ClientDTO client,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        Optional<UserEntity> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "User not found");
            return "redirect:/error";
        }

        long userId = userOptional.get().getUserId();
        client.setUserId(userId);

        if (result.hasErrors()) {
            return renderCreateForm(client);
        }

        clientService.create(client);
        redirectAttributes.addFlashAttribute("success", "Client has been created");
        return "redirect:/clients";
    }

    /**
     * Renders the details page for a specific client, including the client's policies.
     *
     * @param clientId the ID of the client whose details are to be displayed.
     * @param model    the model to pass the client and policy information.
     * @return the path to the client detail page.
     */
    @GetMapping("{clientId}")
    public String renderDetail(
            @PathVariable long clientId,
            Model model
    ) {
        ClientDTO client = clientService.getById(clientId);
        List<PolicyDTO> policies = policyService.getPoliciesByClientId(clientId);
        model.addAttribute("client", client);
        model.addAttribute("policies", policies);
        return "pages/clients/detail";
    }

    /**
     * Renders the form for editing an existing client's details.
     *
     * @param clientId the ID of the client to be edited.
     * @param client   the ClientDTO object containing the updated client details.
     * @return the path to the client edit form.
     */
    @GetMapping("edit/{clientId}")
    public String renderEditForm(
            @PathVariable long clientId,
            ClientDTO client
    ) {
        ClientDTO fetchedClient = clientService.getById(clientId);
        clientMapper.updateClientDTO(fetchedClient, client);
        return "pages/clients/edit";
    }

    /**
     * Handles the editing of an existing client's details. Validates the input and updates the client if valid.
     *
     * @param clientId            the ID of the client to be edited.
     * @param client              the ClientDTO containing the updated client details.
     * @param result              binding result for validation errors.
     * @param redirectAttributes  used to pass messages after redirecting, such as success or error messages.
     * @return the path to the client edit form if there are validation errors, or redirects to the client detail page upon successful editing.
     */
    @PostMapping("edit/{clientId}")
    public String editClient(
            @PathVariable long clientId,
            @Valid ClientDTO client,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors())
            return renderEditForm(clientId, client);

        client.setClientId(clientId);
        clientService.edit(client);
        redirectAttributes.addFlashAttribute("success", "Client has been edited");
        return "redirect:/clients/" + clientId;
    }

    /**
     * Handles the deletion of a specific client.
     *
     * @param clientId            the ID of the client to be deleted.
     * @param redirectAttributes  used to pass messages after redirecting, such as success or error messages.
     * @return a redirection to the clients summary page upon successful deletion.
     */
    @GetMapping("delete/{clientId}")
    public String deleteClient(
            @PathVariable long clientId,
            RedirectAttributes redirectAttributes
    ) {
        clientService.remove(clientId);
        redirectAttributes.addFlashAttribute("success", "Client has been deleted");
        return "redirect:/clients";
    }

    /**
     * Handles the {@code ClientNotFoundException} when a client profile is not found.
     *
     * @param redirectAttributes used to pass error messages in case of an exception.
     * @return a redirection to the clients summary page with an error message.
     */
    @ExceptionHandler({ClientNotFoundException.class})
    public String handleClientNotFoundException(
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("error", "Client profile not found");
        return "redirect:/clients";
    }
}
