package Customer_Service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {

    private Long id;
    private String name;
    private Integer age;
    private Long addressId;

    private String houseNo;
    private String city;

}
