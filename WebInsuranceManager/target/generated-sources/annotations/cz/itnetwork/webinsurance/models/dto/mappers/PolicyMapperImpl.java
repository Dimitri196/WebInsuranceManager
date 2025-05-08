package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.PolicyEntity;
import cz.itnetwork.webinsurance.models.dto.PolicyDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T17:21:09+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class PolicyMapperImpl implements PolicyMapper {

    @Override
    public PolicyDTO toDTO(PolicyEntity source) {
        if ( source == null ) {
            return null;
        }

        PolicyDTO policyDTO = new PolicyDTO();

        policyDTO.setPolicyId( source.getPolicyId() );
        policyDTO.setPolicyAmount( source.getPolicyAmount() );
        policyDTO.setPolicyInsuranceSubject( source.getPolicyInsuranceSubject() );
        policyDTO.setPolicyStartDate( source.getPolicyStartDate() );
        policyDTO.setPolicyEndDate( source.getPolicyEndDate() );
        policyDTO.setPolicyType( source.getPolicyType() );
        policyDTO.setClientEntity( source.getClientEntity() );

        return policyDTO;
    }

    @Override
    public PolicyEntity toEntity(PolicyDTO source) {
        if ( source == null ) {
            return null;
        }

        PolicyEntity policyEntity = new PolicyEntity();

        policyEntity.setPolicyId( source.getPolicyId() );
        policyEntity.setPolicyAmount( source.getPolicyAmount() );
        policyEntity.setPolicyInsuranceSubject( source.getPolicyInsuranceSubject() );
        policyEntity.setPolicyStartDate( source.getPolicyStartDate() );
        policyEntity.setPolicyEndDate( source.getPolicyEndDate() );
        policyEntity.setPolicyType( source.getPolicyType() );
        policyEntity.setClientEntity( source.getClientEntity() );

        return policyEntity;
    }

    @Override
    public void updatePolicyEntity(PolicyDTO source, PolicyEntity target) {
        if ( source == null ) {
            return;
        }

        target.setPolicyId( source.getPolicyId() );
        target.setPolicyAmount( source.getPolicyAmount() );
        target.setPolicyInsuranceSubject( source.getPolicyInsuranceSubject() );
        target.setPolicyStartDate( source.getPolicyStartDate() );
        target.setPolicyEndDate( source.getPolicyEndDate() );
        target.setPolicyType( source.getPolicyType() );
        target.setClientEntity( source.getClientEntity() );
    }

    @Override
    public void updatePolicyDTO(PolicyDTO source, PolicyDTO target) {
        if ( source == null ) {
            return;
        }

        target.setPolicyId( source.getPolicyId() );
        target.setClientId( source.getClientId() );
        target.setPolicyAmount( source.getPolicyAmount() );
        target.setPolicyInsuranceSubject( source.getPolicyInsuranceSubject() );
        target.setPolicyStartDate( source.getPolicyStartDate() );
        target.setPolicyEndDate( source.getPolicyEndDate() );
        target.setPolicyType( source.getPolicyType() );
        target.setClientEntity( source.getClientEntity() );
    }
}
