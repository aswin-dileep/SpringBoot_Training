package InventorySystem.dto;

import InventorySystem.entity.InventoryId;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDTO {

    @NotNull(message = "Product Id should not be empty")
    private Long productId;

    @NotNull(message = "Warehouse Id should not be empty")
    private Long warehouseId;

    @Min(value = 0,message = "value should be >=0")
    private Integer quantity;
}
