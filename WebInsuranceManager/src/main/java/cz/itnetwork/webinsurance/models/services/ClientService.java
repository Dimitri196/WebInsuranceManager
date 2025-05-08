package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;

import java.util.List;

/**
 * Service interface for managing clients.
 * Provides methods for creating, retrieving, updating, and deleting clients.
 */
public interface ClientService {

    /**
     * Creates a new client.
     *
     * @param client the DTO containing the client details to be created.
     */
    void create(ClientDTO client);

    /**
     * Retrieves all clients in the system.
     *
     * @return a list of ClientDTOs representing all clients.
     */
    List<ClientDTO> getAll();

    /**
     * Retrieves a client by its ID.
     *
     * @param clientId the ID of the client.
     * @return the ClientDTO representing the client.
     */
    ClientDTO getById(long clientId);

    /**
     * Edits an existing client with updated details.
     *
     * @param client the DTO containing the updated client information.
     */
    void edit(ClientDTO client);

    /**
     * Removes a client by its ID.
     *
     * @param clientId the ID of the client to be removed.
     */
    void remove(long clientId);

    /**
     * Retrieves a ClientEntity by its ID.
     *
     * @param clientId the ID of the client.
     * @return the ClientEntity object representing the client.
     */
    ClientEntity getClientEntityById(long clientId);

    /**
     * Retrieves all clients associated with a specific user email.
     *
     * @param email the email of the user whose clients are to be retrieved.
     * @return a list of ClientDTOs representing the clients.
     */
    List<ClientDTO> getMyClients(String email);
}

