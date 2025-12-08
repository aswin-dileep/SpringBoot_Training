package Address_Service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequestDTO {

    @NotNull(message = "House No should not be empty")
    private String houseNo;

    @NotNull(message = "Street should not be empty")
    private String street;

    @NotNull(message = "City should not be empty")
    private String city;

    @NotBlank(message = "Pincode should not be empty")
    private String pincode;
}
