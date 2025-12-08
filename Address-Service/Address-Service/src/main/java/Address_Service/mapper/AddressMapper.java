package Address_Service.mapper;

import Address_Service.dto.AddressRequestDTO;
import Address_Service.dto.AddressResponseDTO;
import Address_Service.enitity.Address;
import org.springframework.stereotype.Component;


@Component
public class AddressMapper {
    public AddressResponseDTO toDto(Address address){
        return AddressResponseDTO
                .builder()
                .id(address.getId())
                .houseNo(address.getHouseNo())
                .city(address.getCity())
                .pincode(address.getPincode())
                .street(address.getStreet())
                .build();
    }

    public Address toEntity(AddressRequestDTO dto){
        return Address
                .builder()
                .houseNo(dto.getHouseNo())
                .city(dto.getCity())
                .pincode(dto.getPincode())
                .street(dto.getStreet())
                .build();
    }
}
