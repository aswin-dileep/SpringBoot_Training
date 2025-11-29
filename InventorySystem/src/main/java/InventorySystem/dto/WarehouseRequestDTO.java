package InventorySystem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseRequestDTO {

    @NotBlank(message = "WareHouse name should not be empty")
    private String name;

    @NotBlank(message = "location should not be empty")
    private String location;
}
