package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ContactForm;
import cz.itnetwork.webinsurance.models.dto.ContactFormDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T17:21:09+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ContactFormMapperImpl implements ContactFormMapper {

    @Override
    public ContactFormDTO toDTO(ContactForm contactForm) {
        if ( contactForm == null ) {
            return null;
        }

        ContactFormDTO contactFormDTO = new ContactFormDTO();

        contactFormDTO.setName( contactForm.getName() );
        contactFormDTO.setEmail( contactForm.getEmail() );
        contactFormDTO.setSubject( contactForm.getSubject() );
        contactFormDTO.setMessage( contactForm.getMessage() );

        return contactFormDTO;
    }

    @Override
    public ContactForm toEntity(ContactFormDTO contactFormDTO) {
        if ( contactFormDTO == null ) {
            return null;
        }

        ContactForm contactForm = new ContactForm();

        contactForm.setName( contactFormDTO.getName() );
        contactForm.setEmail( contactFormDTO.getEmail() );
        contactForm.setSubject( contactFormDTO.getSubject() );
        contactForm.setMessage( contactFormDTO.getMessage() );

        return contactForm;
    }
}
