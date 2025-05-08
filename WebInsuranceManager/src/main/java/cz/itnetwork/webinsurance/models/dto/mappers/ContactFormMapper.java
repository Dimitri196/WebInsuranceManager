package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ContactForm;
import cz.itnetwork.webinsurance.models.dto.ContactFormDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper interface for converting between ContactForm and ContactFormDTO.
 * This interface defines the methods to map between DTOs and entities.
 */
@Mapper(componentModel = "spring")
public interface ContactFormMapper {

    /**
     * Converts a ContactForm entity to a ContactFormDTO.
     *
     * @param contactForm the ContactForm entity to convert.
     * @return the converted ContactFormDTO.
     */
    ContactFormDTO toDTO(ContactForm contactForm);

    /**
     * Converts a ContactFormDTO to a ContactForm entity.
     * The {@code id} field is ignored during the mapping.
     *
     * @param contactFormDTO the ContactFormDTO to convert.
     * @return the converted ContactForm entity.
     */
    @Mapping(target = "id", ignore = true)
    ContactForm toEntity(ContactFormDTO contactFormDTO);
}
