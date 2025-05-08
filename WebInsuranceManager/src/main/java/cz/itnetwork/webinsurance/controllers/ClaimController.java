package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.ClaimMapper;
import cz.itnetwork.webinsurance.models.services.ClaimService;
import cz.itnetwork.webinsurance.models.services.PolicyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ClaimController manages the operations related to insurance claims for specific clients and policies.
 * This includes rendering forms for creating and editing claims, viewing claim details, and deleting claims.
 * <p>
 * Each request is scoped to a specific client and policy, indicated by the path variables {@code clientId} and {@code policyId}.
 * </p>
 */
@Controller
@RequestMapping("/clients/{clientId}/policies/{policyId}/claims")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    private PolicyService policyService;

    /**
     * Renders the form for creating a new claim associated with a given policy.
     *
     * @param policyId the ID of the policy to which the claim belongs.
     * @param model    the model to pass attributes such as the claim and policy data.
     * @return the path to the claim creation page, or redirects to the policies page if the policy is not found.
     */
    @GetMapping("create")
    public String renderCreateForm(@PathVariable long policyId, Model model) {
        PolicyEntity policyEntity = policyService.getPolicyEntityById(policyId);
        if (policyEntity == null) {
            return "redirect:/policies";
        }
        ClaimDTO claim = new ClaimDTO();
        claim.setPolicyEntity(policyEntity);
        model.addAttribute("claim", claim);
        model.addAttribute("policy", policyEntity);
        model.addAttribute("clientId", policyEntity.getClientEntity().getClientId());
        model.addAttribute("policyId", policyId);
        return "pages/claims/create";
    }

    /**
     * Handles the creation of a new claim. Validates the claim input, checks for errors, and creates the claim if valid.
     *
     * @param policyId           the ID of the policy to which the claim is associated.
     * @param claim              the claim DTO containing the details of the claim.
     * @param result             binding result for validation errors.
     * @param model              the model to pass attributes such as the claim and policy data.
     * @param redirectAttributes used to pass attributes after redirecting on successful creation.
     * @return the path to the claim creation page if there are validation errors, or redirects to the policy page if the claim is successfully created.
     */
    @PostMapping("create")
    public String createClaim(
            @PathVariable long policyId,
            @Valid @ModelAttribute("claim") ClaimDTO claim,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        PolicyEntity policyEntity = policyService.getPolicyEntityById(policyId);

        if (policyEntity == null) {
            return "redirect:/policies";
        }

        claim.setPolicyEntity(policyEntity);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error.toString()));
            model.addAttribute("claim", claim);
            model.addAttribute("policy", policyEntity);
            return "pages/claims/create";
        }

        try {
            claimService.create(claim);
            redirectAttributes.addFlashAttribute("success", "Claim has been created");
            return "redirect:/clients/" + policyEntity.getClientEntity().getClientId() + "/policies/" + policyId;
        } catch (IllegalArgumentException e) {
            result.rejectValue("claimDate", "error.claimDate", e.getMessage());
            model.addAttribute("claim", claim);
            model.addAttribute("policy", policyEntity);
            return "pages/claims/create";
        }
    }

    /**
     * Renders the details page for a specific claim.
     *
     * @param clientId           the ID of the client to which the claim belongs.
     * @param policyId           the ID of the policy associated with the claim.
     * @param claimId            the ID of the claim to be viewed.
     * @param model              the model to pass attributes such as the claim and policy data.
     * @param redirectAttributes used to pass error messages in case the claim or policy is not found.
     * @return the path to the claim detail page, or redirects to an error page if the claim or policy is not found.
     */
    @GetMapping("/{claimId}/detail")
    public String renderDetail(
            @PathVariable long clientId,
            @PathVariable long policyId,
            @PathVariable long claimId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        ClaimDTO claim = claimService.getById(claimId);
        PolicyDTO policy = policyService.getById(policyId);

        if (claim == null || policy == null) {
            redirectAttributes.addFlashAttribute("error", "Claim or Policy not found.");
            return "redirect:/error";
        }

        model.addAttribute("claim", claim);
        model.addAttribute("policy", policy);
        model.addAttribute("clientId", clientId);
        return "pages/claims/detail";
    }

    /**
     * Renders the form for editing an existing claim.
     *
     * @param clientId  the ID of the client to which the claim belongs.
     * @param policyId  the ID of the policy associated with the claim.
     * @param claimId   the ID of the claim to be edited.
     * @param model     the model to pass attributes such as the claim and policy data.
     * @return the path to the claim edit page, or redirects to an error page if the claim or policy is not found.
     */
    @GetMapping("{claimId}/edit")
    public String renderEditForm(
            @PathVariable long clientId,
            @PathVariable long policyId,
            @PathVariable long claimId,
            Model model
    ) {
        ClaimDTO fetchedClaim = claimService.getById(claimId);
        PolicyDTO policy = policyService.getById(policyId);

        if (fetchedClaim == null || policy == null) {
            return "redirect:/error";
        }

        model.addAttribute("claimDTO", fetchedClaim);
        model.addAttribute("policy", policy);
        model.addAttribute("policyId", policyId);
        model.addAttribute("clientId", clientId);
        return "pages/claims/edit";
    }

    /**
     * Handles the editing of an existing claim. Validates the input and updates the claim if valid.
     *
     * @param clientId           the ID of the client to which the claim belongs.
     * @param policyId           the ID of the policy associated with the claim.
     * @param claimId            the ID of the claim to be edited.
     * @param claim              the claim DTO containing the updated claim details.
     * @param result             binding result for validation errors.
     * @param model              the model to pass attributes such as the claim and policy data.
     * @param redirectAttributes used to pass success messages after redirecting upon successful edit.
     * @return the path to the claim edit page if there are validation errors, or redirects to the policy page if the claim is successfully edited.
     */
    @PostMapping("{claimId}/edit")
    public String editClaim(
            @PathVariable long clientId,
            @PathVariable long policyId,
            @PathVariable long claimId,
            @Valid @ModelAttribute("claimDTO") ClaimDTO claim,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        PolicyDTO policy = policyService.getById(policyId);
        if (policy == null) {
            redirectAttributes.addFlashAttribute("error", "Policy not found");
            return "redirect:/error";
        }

        if (claim.getClaimDate().isBefore(policy.getPolicyStartDate()) || claim.getClaimDate().isAfter(policy.getPolicyEndDate())) {
            result.rejectValue("claimDate", "error.claimDate", "Claim date must be within the policy period.");
        }

        if (result.hasErrors()) {
            model.addAttribute("claimDTO", claim);
            model.addAttribute("policyId", policyId);
            model.addAttribute("clientId", clientId);
            return "pages/claims/edit";
        }

        PolicyEntity policyEntity = new PolicyEntity();
        policyEntity.setPolicyId(policyId);
        policyEntity.setClientEntity(new ClientEntity());
        policyEntity.getClientEntity().setClientId(clientId);

        claim.setPolicyEntity(policyEntity);
        claim.setClaimId(claimId);
        claimService.edit(claim);
        return "redirect:/clients/" + clientId + "/policies/" + policyId;
    }

    /**
     * Deletes an existing claim.
     *
     * @param clientId           the ID of the client to which the claim belongs.
     * @param policyId           the ID of the policy associated with the claim.
     * @param claimId            the ID of the claim to be deleted.
     * @param redirectAttributes used to pass success messages after redirecting upon successful deletion.
     * @return a redirection to the policy page after the claim is deleted.
     */
    @GetMapping("{claimId}/delete")
    public String deleteClaim(
            @PathVariable long clientId,
            @PathVariable long policyId,
            @PathVariable long claimId,
            RedirectAttributes redirectAttributes
    ) {
        claimService.remove(claimId);
        redirectAttributes.addFlashAttribute("success", "Claim has been deleted");
        return "redirect:/clients/" + clientId + "/policies/" + policyId;
    }
}
