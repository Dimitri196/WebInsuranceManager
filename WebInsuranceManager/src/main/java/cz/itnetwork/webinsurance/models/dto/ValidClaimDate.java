package cz.itnetwork.webinsurance.models.dto;

import cz.itnetwork.webinsurance.models.validators.ClaimDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to validate that a claim date falls within the policy period.
 * This annotation is used on a class level and is validated by the {@link ClaimDateValidator}.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClaimDateValidator.class)
public @interface ValidClaimDate {

    /**
     * Error message that will be shown when the validation fails.
     *
     * @return the error message.
     */
    String message() default "Claim date must be within the policy period";

    /**
     * Allows the specification of validation groups, to which this constraint belongs.
     *
     * @return the groups.
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients of the Jakarta Bean Validation API to assign custom payload objects
     * to a constraint.
     *
     * @return the payload.
     */
    Class<? extends Payload>[] payload() default {};
}
