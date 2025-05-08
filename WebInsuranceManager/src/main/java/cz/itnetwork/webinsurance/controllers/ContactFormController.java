package cz.itnetwork.webinsurance.controllers;

import cz.itnetwork.webinsurance.models.dto.ContactFormDTO;
import cz.itnetwork.webinsurance.models.services.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * ContactFormController handles the display and submission of the contact form.
 * It allows users to access the contact page and submit their contact information or inquiries.
 */
@Controller
@RequestMapping("/contact")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    /**
     * Displays the contact page where users can fill out the contact form.
     * A new instance of {@link ContactFormDTO} is added to the model to be used in the form.
     *
     * @param model the model used to pass the {@link ContactFormDTO} object to the view.
     * @return the path to the contact page template.
     */
    @GetMapping
    public String getContactPage(Model model) {
        model.addAttribute("contactFormDTO", new ContactFormDTO());
        return "pages/home/contact";
    }

    /**
     * Handles the submission of the contact form.
     * The submitted contact form data is saved and a success message is added to the redirect attributes.
     *
     * @param contactFormDTO       the data transfer object containing the submitted contact form details.
     * @param redirectAttributes   used to pass a success message after redirecting to the contact page.
     * @return a redirect to the contact page after successful form submission.
     */
    @PostMapping("/submit")
    public String submitContactForm(@ModelAttribute ContactFormDTO contactFormDTO, RedirectAttributes redirectAttributes) {
        contactFormService.saveContactForm(contactFormDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Your message has been sent. Thank you!");
        return "redirect:/contact";
    }
}
