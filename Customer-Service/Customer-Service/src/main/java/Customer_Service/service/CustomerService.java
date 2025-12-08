package Customer_Service.service;

import Customer_Service.AddressClient;
import Customer_Service.dto.AddressResponseDTO;
import Customer_Service.dto.CustomerRequestDTO;
import Customer_Service.dto.CustomerResponseDTO;
import Customer_Service.entity.Customer;
import Customer_Service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressClient addressClient;

    public CustomerResponseDTO addCustomer(CustomerRequestDTO dto) {

        Customer customer = Customer.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .addressId(dto.getAddressId())
                .build();

        Customer saved = customerRepository.save(customer);

        return CustomerResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .age(saved.getAge())
                .addressId(saved.getAddressId())
                .build();
    }

    public CustomerResponseDTO getCustomerWithAddress(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Customer with id "+id+" not founded"));

        AddressResponseDTO address = addressClient.getAddress(customer.getAddressId());

        return CustomerResponseDTO
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .addressId(customer.getAddressId())
                .houseNo(address.getHouseNo())
                .city(address.getCity())
                .build();
    }

}
