package Address_Service.controller;

import Address_Service.dto.AddressRequestDTO;
import Address_Service.dto.AddressResponseDTO;
import Address_Service.enitity.Address;
import Address_Service.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> addAddress(@Valid @RequestBody AddressRequestDTO dto){
        AddressResponseDTO address = addressService.addAddress(dto);

        return  ResponseEntity.ok(address);
    }

    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> getAllAddress(){
        List<AddressResponseDTO> addresses = addressService.getAllAddress();

        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long id){

        AddressResponseDTO address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@RequestBody AddressRequestDTO dto,@PathVariable Long id){

        AddressResponseDTO updateAddress = addressService.updateAddress(id,dto);

        return ResponseEntity.ok(updateAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){

        String deleteMessage = addressService.deleteAddress(id);

        return ResponseEntity.ok(deleteMessage);
    }


}
