package cz.itnetwork.webinsurance.models.dto.mappers;

import cz.itnetwork.webinsurance.data.entities.ClientEntity;
import cz.itnetwork.webinsurance.data.entities.UserEntity;
import cz.itnetwork.webinsurance.models.dto.ClientDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T17:21:08+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientEntity toEntity(ClientDTO source) {
        if ( source == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setClientId( source.getClientId() );
        clientEntity.setClientName( source.getClientName() );
        clientEntity.setClientSurname( source.getClientSurname() );
        clientEntity.setClientEmail( source.getClientEmail() );
        clientEntity.setClientPhoneNumber( source.getClientPhoneNumber() );
        clientEntity.setClientStreetAddress( source.getClientStreetAddress() );
        clientEntity.setClientCity( source.getClientCity() );
        clientEntity.setClientZipCode( source.getClientZipCode() );
        clientEntity.setClientGender( source.getClientGender() );

        return clientEntity;
    }

    @Override
    public ClientDTO toDTO(ClientEntity source) {
        if ( source == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setUserId( sourceUserUserId( source ) );
        clientDTO.setClientGender( source.getClientGender() );
        clientDTO.setClientId( source.getClientId() );
        clientDTO.setClientName( source.getClientName() );
        clientDTO.setClientSurname( source.getClientSurname() );
        clientDTO.setClientEmail( source.getClientEmail() );
        clientDTO.setClientPhoneNumber( source.getClientPhoneNumber() );
        clientDTO.setClientStreetAddress( source.getClientStreetAddress() );
        clientDTO.setClientCity( source.getClientCity() );
        clientDTO.setClientZipCode( source.getClientZipCode() );

        return clientDTO;
    }

    @Override
    public void updateClientDTO(ClientDTO source, ClientDTO target) {
        if ( source == null ) {
            return;
        }

        target.setClientGender( source.getClientGender() );
        target.setClientId( source.getClientId() );
        target.setUserId( source.getUserId() );
        target.setClientName( source.getClientName() );
        target.setClientSurname( source.getClientSurname() );
        target.setClientEmail( source.getClientEmail() );
        target.setClientPhoneNumber( source.getClientPhoneNumber() );
        target.setClientStreetAddress( source.getClientStreetAddress() );
        target.setClientCity( source.getClientCity() );
        target.setClientZipCode( source.getClientZipCode() );
    }

    @Override
    public void updateClientEntity(ClientDTO source, ClientEntity target) {
        if ( source == null ) {
            return;
        }

        target.setClientId( source.getClientId() );
        target.setClientName( source.getClientName() );
        target.setClientSurname( source.getClientSurname() );
        target.setClientEmail( source.getClientEmail() );
        target.setClientPhoneNumber( source.getClientPhoneNumber() );
        target.setClientStreetAddress( source.getClientStreetAddress() );
        target.setClientCity( source.getClientCity() );
        target.setClientZipCode( source.getClientZipCode() );
        target.setClientGender( source.getClientGender() );
    }

    private long sourceUserUserId(ClientEntity clientEntity) {
        if ( clientEntity == null ) {
            return 0L;
        }
        UserEntity user = clientEntity.getUser();
        if ( user == null ) {
            return 0L;
        }
        long userId = user.getUserId();
        return userId;
    }
}
