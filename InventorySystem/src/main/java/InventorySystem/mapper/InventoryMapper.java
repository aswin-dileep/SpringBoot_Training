package InventorySystem.mapper;

import InventorySystem.dto.InventoryRequestDTO;
import InventorySystem.dto.InventoryResponseDTO;
import InventorySystem.entity.Inventory;
import InventorySystem.entity.InventoryId;
import InventorySystem.entity.Product;
import InventorySystem.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public Inventory toEntity(InventoryRequestDTO dto, Product product, Warehouse warehouse){
        InventoryId id = new InventoryId(dto.getProductId(),dto.getWarehouseId());

        Inventory inventory = new Inventory();
        inventory.setId(id);
        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);
        inventory.setQuantity(dto.getQuantity());
        return inventory;
    }

    public InventoryResponseDTO toDTO(Inventory inventory){
        return InventoryResponseDTO
                .builder()
                .productId(inventory.getProduct().getId())
                .warehouseId(inventory.getWarehouse().getId())
                .quantity(inventory.getQuantity())
                .build();
    }
}
