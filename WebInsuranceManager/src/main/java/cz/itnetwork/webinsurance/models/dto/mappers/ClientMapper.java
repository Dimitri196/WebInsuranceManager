package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between ClientDTO and ClientEntity.
 * This interface defines methods for mapping between DTOs and entities,
 * and for updating existing instances.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    /**
     * Converts a ClientDTO to a ClientEntity.
     *
     * @param source the ClientDTO to convert.
     * @return the converted ClientEntity.
     */
    ClientEntity toEntity(ClientDTO source);

    /**
     * Converts a ClientEntity to a ClientDTO.
     * Maps the {@code userId} field from the associated user entity in ClientEntity to the corresponding field in ClientDTO.
     *
     * @param source the ClientEntity to convert.
     * @return the converted ClientDTO.
     */
    @Mapping(target = "userId", source = "user.userId")
    ClientDTO toDTO(ClientEntity source);

    /**
     * Updates an existing ClientDTO with data from another ClientDTO.
     *
     * @param source the ClientDTO containing updated information.
     * @param target the ClientDTO to be updated.
     */
    void updateClientDTO(ClientDTO source, @MappingTarget ClientDTO target);

    /**
     * Updates an existing ClientEntity with data from a ClientDTO.
     *
     * @param source the ClientDTO containing updated information.
     * @param target the ClientEntity to be updated.
     */
    void updateClientEntity(ClientDTO source, @MappingTarget ClientEntity target);
}
