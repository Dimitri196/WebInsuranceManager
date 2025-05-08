package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ClaimEntity;
import cz.itnetwork.webinsurance.models.dto.ClaimDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T17:21:09+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ClaimMapperImpl implements ClaimMapper {

    @Override
    public ClaimDTO toDTO(ClaimEntity source) {
        if ( source == null ) {
            return null;
        }

        ClaimDTO claimDTO = new ClaimDTO();

        claimDTO.setClaimId( source.getClaimId() );
        claimDTO.setClaimDate( source.getClaimDate() );
        claimDTO.setClaimDescription( source.getClaimDescription() );
        claimDTO.setPolicyEntity( source.getPolicyEntity() );
        claimDTO.setClaimType( source.getClaimType() );

        return claimDTO;
    }

    @Override
    public ClaimEntity toEntity(ClaimDTO source) {
        if ( source == null ) {
            return null;
        }

        ClaimEntity claimEntity = new ClaimEntity();

        claimEntity.setPolicyEntity( source.getPolicyEntity() );
        claimEntity.setClaimId( source.getClaimId() );
        claimEntity.setClaimDate( source.getClaimDate() );
        claimEntity.setClaimType( source.getClaimType() );
        claimEntity.setClaimDescription( source.getClaimDescription() );

        return claimEntity;
    }

    @Override
    public void updateClaimEntity(ClaimDTO source, ClaimEntity target) {
        if ( source == null ) {
            return;
        }

        target.setPolicyEntity( source.getPolicyEntity() );
        target.setClaimId( source.getClaimId() );
        target.setClaimDate( source.getClaimDate() );
        target.setClaimType( source.getClaimType() );
        target.setClaimDescription( source.getClaimDescription() );
    }

    @Override
    public void updateClaimDTO(ClaimDTO source, ClaimDTO target) {
        if ( source == null ) {
            return;
        }

        target.setPolicyId( source.getPolicyId() );
        target.setClaimId( source.getClaimId() );
        target.setClaimDate( source.getClaimDate() );
        target.setClaimDescription( source.getClaimDescription() );
        target.setPolicyEntity( source.getPolicyEntity() );
        target.setClaimType( source.getClaimType() );
    }
}
