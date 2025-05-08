package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.ContactForm;
import cz.itnetwork.webinsurance.data.repositories.ContactFormRepository;
import cz.itnetwork.webinsurance.models.dto.ContactFormDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.ContactFormMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ContactFormService} interface for managing contact forms.
 * Provides methods for saving and retrieving contact forms.
 */
@Service
public class ContactFormServiceImpl implements ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Autowired
    private ContactFormMapper contactFormMapper;

    /**
     * Saves a new contact form.
     * The method converts the provided {@link ContactFormDTO} to a {@link ContactForm} entity,
     * saves it to the repository, and then converts it back to a DTO.
     *
     * @param contactFormDTO the DTO containing the contact form details to be saved.
     */
    @Transactional
    @Override
    public void saveContactForm(ContactFormDTO contactFormDTO) {
        ContactForm contactForm = contactFormMapper.toEntity(contactFormDTO);
        ContactForm savedContactForm = contactFormRepository.save(contactForm);
        contactFormMapper.toDTO(savedContactForm);  // Note: This line is redundant if the DTO is not used.
    }

    /**
     * Retrieves a contact form by its ID.
     * If no form is found, a {@link RuntimeException} is thrown.
     *
     * @param id the ID of the contact form to retrieve.
     * @return the ContactFormDTO representing the contact form.
     * @throws RuntimeException if the form is not found.
     */
    @Override
    public ContactFormDTO getContactFormById(Long id) {
        ContactForm contactForm = contactFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
        return contactFormMapper.toDTO(contactForm);
    }
}

