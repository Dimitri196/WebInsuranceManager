package cz.itnetwork.webinsurance.models.validators;

import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import cz.itnetwork.webinsurance.models.dto.ValidClaimDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * ClaimDateValidator is a custom constraint validator that ensures a claim's date falls
 * within the range of its associated policy's start and end dates. This validator implements
 * {@link ConstraintValidator} and works in conjunction with the {@link ValidClaimDate} annotation
 * to enforce validation rules on {@link ClaimDTO} objects.
 */
public class ClaimDateValidator implements ConstraintValidator<ValidClaimDate, ClaimDTO> {

    /**
     * Validates whether the claim date of the given {@link ClaimDTO} falls within the period
     * specified by the policy's start and end dates.
     *
     * @param claimDTO the {@link ClaimDTO} object to be validated.
     * @param context the {@link ConstraintValidatorContext} used to build custom error messages.
     * @return true if the claim date is within the policy period; false otherwise.
     */
    @Override
    public boolean isValid(ClaimDTO claimDTO, ConstraintValidatorContext context) {
        if (claimDTO == null || claimDTO.getClaimDate() == null || claimDTO.getPolicyEntity() == null) {
            return true; // Consider null values as valid (to be handled by other validations if needed).
        }

        PolicyEntity policyEntity = claimDTO.getPolicyEntity();
        LocalDate claimDate = claimDTO.getClaimDate();
        LocalDate policyStartDate = policyEntity.getPolicyStartDate();
        LocalDate policyEndDate = policyEntity.getPolicyEndDate();
        boolean isValid = !claimDate.isBefore(policyStartDate) && !claimDate.isAfter(policyEndDate);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Claim date must be within the policy period.")
                    .addPropertyNode("claimDate")
                    .addConstraintViolation();
        }
        return isValid;
    }
}
