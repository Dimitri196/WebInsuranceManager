package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ClaimEntity;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper interface for converting between ClaimDTO and ClaimEntity.
 * This interface defines the methods to map between DTOs and entities,
 * and also to update existing instances.
 */
@Mapper(componentModel = "spring")
public interface ClaimMapper {

    /**
     * Converts a ClaimEntity to a ClaimDTO.
     *
     * @param source the ClaimEntity to convert.
     * @return the converted ClaimDTO.
     */
    ClaimDTO toDTO(ClaimEntity source);

    /**
     * Converts a ClaimDTO to a ClaimEntity.
     *
     * @param source the ClaimDTO to convert.
     * @return the converted ClaimEntity.
     */
    ClaimEntity toEntity(ClaimDTO source);

    /**
     * Updates an existing ClaimEntity with data from a ClaimDTO.
     *
     * @param source the ClaimDTO containing updated information.
     * @param target the ClaimEntity to be updated.
     */
    void updateClaimEntity(ClaimDTO source, @MappingTarget ClaimEntity target);

    /**
     * Updates an existing ClaimDTO with data from another ClaimDTO.
     *
     * @param source the ClaimDTO containing updated information.
     * @param target the ClaimDTO to be updated.
     */
    void updateClaimDTO(ClaimDTO source, @MappingTarget ClaimDTO target);
}

