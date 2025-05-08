package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.models.dto.ContactFormDTO;

/**
 * Service interface for managing contact forms.
 * Provides methods for saving and retrieving contact forms.
 */
public interface ContactFormService {

    /**
     * Saves a new contact form.
     *
     * @param contactFormDTO the DTO containing the contact form details to be saved.
     */
    void saveContactForm(ContactFormDTO contactFormDTO);

    /**
     * Retrieves a contact form by its ID.
     *
     * @param id the ID of the contact form to retrieve.
     * @return the ContactFormDTO representing the contact form.
     */
    ContactFormDTO getContactFormById(Long id);
}
