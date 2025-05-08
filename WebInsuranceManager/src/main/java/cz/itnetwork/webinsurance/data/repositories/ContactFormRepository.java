package cz.itnetwork.webinsurance.data.repositories;

import cz.itnetwork.webinsurance.data.entities.ContactForm;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for performing CRUD operations on {@link ContactForm} entities.
 * Extends {@link CrudRepository} to provide basic CRUD operations for {@link ContactForm} objects.
 */
public interface ContactFormRepository extends CrudRepository<ContactForm, Long> {
}
