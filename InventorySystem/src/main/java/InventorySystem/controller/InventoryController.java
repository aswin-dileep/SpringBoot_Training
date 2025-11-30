package InventorySystem.controller;

import InventorySystem.dto.InventoryRequestDTO;
import InventorySystem.dto.InventoryResponseDTO;
import InventorySystem.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @PostMapping("/")
    public ResponseEntity<InventoryResponseDTO> createInventory(
            @Valid @RequestBody InventoryRequestDTO dto){
        InventoryResponseDTO response = inventoryService.createInventory(dto);

        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}/{warehouseId}")
    public ResponseEntity<InventoryResponseDTO> getInventory(
            @PathVariable Long productId,
            @PathVariable Long warehouseId){

        InventoryResponseDTO response = inventoryService.getInventoryById(productId, warehouseId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{productId}/{warehouseId}")
    public ResponseEntity<InventoryResponseDTO> updateInventory(
            @PathVariable Long productId,
            @PathVariable Long warehouseId,
            @Valid @RequestBody InventoryRequestDTO dto) {

        InventoryResponseDTO response =
                inventoryService.updateInventory(productId, warehouseId, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}/{warehouseId}")
    public ResponseEntity<String> deleteInventory(
            @PathVariable Long productId,
            @PathVariable Long warehouseId
    ){
        inventoryService.deleteInventory(productId,warehouseId);
        return ResponseEntity.ok("Inventory deleted successfully");
    }
}
