package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.PolicyMapper;
import cz.itnetwork.webinsurance.models.services.ClaimService;
import cz.itnetwork.webinsurance.models.services.ClientService;
import cz.itnetwork.webinsurance.models.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import java.util.List;

/**
 * PolicyController handles the operations related to policies associated with a client.
 * This includes creating, viewing, editing, and deleting policies, as well as rendering the policy detail page.
 */
@Controller
@RequestMapping("/clients/{clientId}/policies")
public class PolicyController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyMapper policyMapper;

    @Autowired
    private ClientService clientService;

    /**
     * Displays the form for creating a new policy for a specific client.
     * Adds a new {@link PolicyDTO} and client information to the model.
     *
     * @param clientId the ID of the client for whom the policy is being created.
     * @param model    the model used to pass the {@link PolicyDTO} and client entity to the view.
     * @return the path to the policy creation form.
     */
    @GetMapping("create")
    public String renderCreateForm(@PathVariable long clientId, Model model) {
        ClientEntity clientEntity = clientService.getClientEntityById(clientId);
        if (clientEntity == null) {
            return "redirect:/clients";
        }
        PolicyDTO policy = new PolicyDTO();
        policy.setClientEntity(clientEntity);
        model.addAttribute("policy", policy);
        model.addAttribute("client", clientEntity);
        return "pages/policies/create";
    }

    /**
     * Handles the submission of the policy creation form. Validates the policy data and creates a new policy.
     * If validation errors occur, the form is re-rendered with error messages.
     *
     * @param clientId            the ID of the client for whom the policy is being created.
     * @param policy              the {@link PolicyDTO} containing the policy details.
     * @param result              used to bind validation errors.
     * @param model               the model used to pass form data and client information back to the view if errors occur.
     * @param redirectAttributes  used to pass a success message after redirecting.
     * @return the path to the client details page or the policy creation form if validation fails.
     */
    @PostMapping("create")
    public String createPolicy(
            @PathVariable long clientId,
            @Valid @ModelAttribute PolicyDTO policy,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (policy.getPolicyAmount() <= 0) {
            result.rejectValue("policyAmount", "error.policyAmount", "Policy amount must be greater than zero.");
        }

        if (result.hasErrors()) {
            ClientEntity clientEntity = clientService.getClientEntityById(clientId);
            policy.setClientEntity(clientEntity);
            model.addAttribute("policy", policy);
            model.addAttribute("client", clientEntity);
            return "pages/policies/create";
        }

        ClientEntity clientEntity = clientService.getClientEntityById(clientId);
        if (clientEntity == null) {
            return "redirect:/clients";
        }
        policy.setClientEntity(clientEntity);
        policyService.create(policy);
        redirectAttributes.addFlashAttribute("success", "Policy has been created");
        return "redirect:/clients/" + clientId;
    }

    /**
     * Displays the details of a specific policy and its associated claims.
     *
     * @param clientId  the ID of the client to which the policy belongs.
     * @param policyId  the ID of the policy to be displayed.
     * @param model     the model used to pass policy, client, and claims data to the view.
     * @return the path to the policy detail page.
     */
    @GetMapping("{policyId}")
    public String renderDetail(
            @PathVariable long clientId,
            @PathVariable long policyId,
            Model model
    ) {
        PolicyDTO policy = policyService.getById(policyId);
        ClientDTO client = clientService.getById(clientId);
        List<ClaimDTO> claims = claimService.getClaimByPolicyId(policyId);
        model.addAttribute("policy", policy);
        model.addAttribute("client", client);
        model.addAttribute("claims", claims);
        return "pages/policies/detail";
    }

    /**
     * Displays the form for editing an existing policy.
     *
     * @param clientId  the ID of the client to which the policy belongs.
     * @param policyId  the ID of the policy to be edited.
     * @param model     the model used to pass the existing policy data to the view.
     * @return the path to the policy edit form.
     */
    @GetMapping("edit/{policyId}")
    public String renderEditForm(
            @PathVariable long clientId,
            @PathVariable long policyId,
            Model model
    ) {
        PolicyDTO fetchedPolicy = policyService.getById(policyId);
        if (fetchedPolicy == null) {
            return "redirect:/error";
        }
        model.addAttribute("policyDTO", fetchedPolicy);
        model.addAttribute("clientId", clientId);
        return "pages/policies/edit";
    }

    /**
     * Handles the submission of the policy edit form. Validates the policy data and updates the existing policy.
     * If validation errors occur, the form is re-rendered with error messages.
     *
     * @param clientId            the ID of the client to which the policy belongs.
     * @param policyId            the ID of the policy being edited.
     * @param policy              the updated {@link PolicyDTO} containing the policy details.
     * @param result              used to bind validation errors.
     * @param model               the model used to pass form data back to the view if errors occur.
     * @param redirectAttributes  used to pass a success message after redirecting.
     * @return the path to the policy detail page or the policy edit form if validation fails.
     */
    @PostMapping("edit/{policyId}")
    public String editPolicy(
            @PathVariable long clientId,
            @PathVariable long policyId,
            @Valid @ModelAttribute("policyDTO") PolicyDTO policy,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        PolicyDTO existingPolicy = policyService.getById(policyId);

        if (existingPolicy == null) {
            redirectAttributes.addFlashAttribute("error", "Policy not found");
            return "redirect:/error";
        }

        if (policy.getPolicyAmount() <= 0) {
            result.rejectValue("policyAmount", "error.policyAmount", "Policy amount must be greater than zero.");
        }

        List<ClaimDTO> claims = claimService.getClaimByPolicyId(policyId);
        for (ClaimDTO claim : claims) {
            if (claim.getClaimDate().isBefore(policy.getPolicyStartDate()) || claim.getClaimDate().isAfter(policy.getPolicyEndDate())) {
                result.rejectValue("policyStartDate", "error.policyDateRange", "Cannot update policy. Existing claims would fall outside the new date range.");
                result.rejectValue("policyEndDate", "error.policyDateRange", "Cannot update policy. Existing claims would fall outside the new date range.");
                break;
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("policyDTO", policy);
            model.addAttribute("clientId", clientId);
            return "pages/policies/edit";
        }

        policy.setPolicyId(policyId);
        policy.setClientId(clientId);
        policyService.edit(policy);
        redirectAttributes.addFlashAttribute("success", "Policy has been edited successfully");
        return "redirect:/clients/" + clientId;
    }

    /**
     * Deletes a specific policy.
     *
     * @param clientId            the ID of the client to which the policy belongs.
     * @param policyId            the ID of the policy to be deleted.
     * @param redirectAttributes  used to pass a success message after redirecting.
     * @return a redirect to the client's detail page after the policy has been deleted.
     */
    @GetMapping("delete/{policyId}")
    public String deletePolicy(
            @PathVariable long clientId,
            @PathVariable long policyId,
            RedirectAttributes redirectAttributes
    ) {
        policyService.remove(policyId);
        redirectAttributes.addFlashAttribute("success", "Policy has been deleted");

        return "redirect:/clients/" + clientId;
    }
}
