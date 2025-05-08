package cz.itnetwork.webinsurance.models.services;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.data.entities.UserEntity;
import cz.itnetwork.webinsurance.data.repositories.ClientRepository;
import cz.itnetwork.webinsurance.data.repositories.PolicyRepository;
import cz.itnetwork.webinsurance.data.repositories.UserRepository;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;
import cz.itnetwork.webinsurance.models.dto.mappers.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.itnetwork.webinsurance.models.exceptions.ClientNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Implementation of the {@link ClientService} interface for managing clients.
 * Provides methods for creating, retrieving, updating, and deleting clients.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new client and associates it with an existing user.
     *
     * @param client the DTO containing the client details to be created.
     */
    @Override
    public void create(ClientDTO client) {
        ClientEntity newClient = clientMapper.toEntity(client);

        UserEntity user = userRepository.findById(client.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        newClient.setUser(user);

        ClientEntity saved = clientRepository.save(newClient);
        clientMapper.toDTO(saved); // Note: This line is redundant if the DTO is not used.
    }

    /**
     * Retrieves all clients in the system.
     *
     * @return a list of ClientDTOs representing all clients.
     */
    @Override
    public List<ClientDTO> getAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(clientMapper::toDTO)
                .toList();
    }

    /**
     * Retrieves a client by its ID.
     *
     * @param clientId the ID of the client.
     * @return the ClientDTO representing the client.
     */
    @Override
    public ClientDTO getById(long clientId) {
        ClientEntity fetchedClient = getClientOrThrow(clientId);
        return clientMapper.toDTO(fetchedClient);
    }

    /**
     * Edits an existing client with updated details.
     *
     * @param client the DTO containing the updated client information.
     */
    @Override
    public void edit(ClientDTO client) {
        ClientEntity fetchedClient = getClientOrThrow(client.getClientId());
        clientMapper.updateClientEntity(client, fetchedClient);
        clientRepository.save(fetchedClient);
    }

    /**
     * Removes a client by its ID and deletes all associated policies.
     *
     * @param clientId the ID of the client to be removed.
     */
    @Override
    @Transactional
    public void remove(long clientId) {
        ClientEntity fetchedClient = getClientOrThrow(clientId);
        policyRepository.deleteAllByClientEntity(fetchedClient);
        clientRepository.delete(fetchedClient);
    }

    /**
     * Retrieves a ClientEntity by its ID.
     *
     * @param clientId the ID of the client.
     * @return the ClientEntity object representing the client.
     */
    @Override
    public ClientEntity getClientEntityById(long clientId) {
        return getClientOrThrow(clientId);
    }

    /**
     * Helper method to retrieve a ClientEntity or throw an exception if not found.
     *
     * @param clientId the ID of the client.
     * @return the ClientEntity object representing the client.
     * @throws ClientNotFoundException if the client is not found.
     */
    ClientEntity getClientOrThrow(long clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    /**
     * Retrieves all clients associated with a specific user email.
     *
     * @param email the email of the user whose clients are to be retrieved.
     * @return a list of ClientDTOs representing the clients.
     */
    @Override
    public List<ClientDTO> getMyClients(String email) {
        return clientRepository.getAllByUserEmail(email).stream()
                .map(clientMapper::toDTO)
                .toList();
    }
}
