package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between PolicyDTO and PolicyEntity.
 * This interface defines methods for mapping between DTOs and entities,
 * and for updating existing instances.
 */
@Mapper(componentModel = "spring")
public interface PolicyMapper {

    /**
     * Converts a PolicyEntity to a PolicyDTO.
     *
     * @param source the PolicyEntity to convert.
     * @return the converted PolicyDTO.
     */
    PolicyDTO toDTO(PolicyEntity source);

    /**
     * Converts a PolicyDTO to a PolicyEntity.
     *
     * @param source the PolicyDTO to convert.
     * @return the converted PolicyEntity.
     */
    PolicyEntity toEntity(PolicyDTO source);

    /**
     * Updates an existing PolicyEntity with data from a PolicyDTO.
     *
     * @param source the PolicyDTO containing updated information.
     * @param target the PolicyEntity to be updated.
     */
    void updatePolicyEntity(PolicyDTO source, @MappingTarget PolicyEntity target);

    /**
     * Updates an existing PolicyDTO with data from another PolicyDTO.
     *
     * @param source the PolicyDTO containing updated information.
     * @param target the PolicyDTO to be updated.
     */
    void updatePolicyDTO(PolicyDTO source, @MappingTarget PolicyDTO target);
}
