package Customer_Service;

import Customer_Service.dto.AddressResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service",url="http://localhost:8080")
public interface AddressClient {

    @GetMapping("/address/{id}")
    AddressResponseDTO getAddress(@PathVariable("id") Long id);
}
