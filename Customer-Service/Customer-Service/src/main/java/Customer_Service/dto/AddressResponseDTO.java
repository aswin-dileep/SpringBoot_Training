package Customer_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseDTO {
    private Long id;
    private String houseNo;
    private String street;
    private String city;
    private String pincode;
}
