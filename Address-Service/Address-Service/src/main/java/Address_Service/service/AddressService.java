package Address_Service.service;

import Address_Service.dto.AddressRequestDTO;
import Address_Service.dto.AddressResponseDTO;
import Address_Service.enitity.Address;
import Address_Service.exception.ResourceNotAvailableException;
import Address_Service.mapper.AddressMapper;
import Address_Service.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    public final AddressRepository addressRepository;
    public final AddressMapper addressMapper;

    public AddressResponseDTO addAddress(AddressRequestDTO dto){

        Address address = addressMapper.toEntity(dto);
        Address savedAddress = addressRepository.save(address);

        return addressMapper.toDto(savedAddress);
    }

    public List<AddressResponseDTO> getAllAddress(){
        List<Address> addresses = addressRepository.findAll();

        return addresses
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public AddressResponseDTO getAddressById(Long id){

        Address address = addressRepository.findById(id)
                .orElseThrow(()->new ResourceNotAvailableException("Address not founded with id "+id));

        return addressMapper.toDto(address);

    }

    public AddressResponseDTO updateAddress(Long id,AddressRequestDTO dto){

        Address address = addressRepository.findById(id)
                .orElseThrow(()->new ResourceNotAvailableException("Address not founded with id "+id));

        address.setCity(dto.getCity());
        address.setHouseNo(dto.getHouseNo());
        address.setStreet(dto.getStreet());
        address.setPincode(dto.getPincode());

        Address updatedAddress = addressRepository.save(address);

        return addressMapper.toDto(updatedAddress);
    }

    public String deleteAddress(Long id){
        addressRepository.deleteById(id);

        return "Address removed Successfully";
    }
}
