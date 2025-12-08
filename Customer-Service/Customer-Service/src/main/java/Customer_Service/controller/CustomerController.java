package Customer_Service.controller;

import Customer_Service.dto.CustomerRequestDTO;
import Customer_Service.dto.CustomerResponseDTO;
import Customer_Service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerWithAddress(@PathVariable Long id){
        CustomerResponseDTO response = customerService.getCustomerWithAddress(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO response = customerService.addCustomer(dto);
        return ResponseEntity.ok(response);
    }

}
