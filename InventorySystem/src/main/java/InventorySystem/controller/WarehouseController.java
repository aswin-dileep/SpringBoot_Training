package InventorySystem.controller;

import InventorySystem.dto.WarehouseRequestDTO;
import InventorySystem.dto.WarehouseResponseDTO;
import InventorySystem.services.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService){
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseResponseDTO>> getAllWarehouse() {
        List<WarehouseResponseDTO> warehouses = warehouseService.getAllWarehouse();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> getWarehouse(
            @PathVariable long id){
        WarehouseResponseDTO warehouse = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(warehouse);
    }

    @PostMapping
    public ResponseEntity<WarehouseResponseDTO> addWarehouse(
            @Valid @RequestBody WarehouseRequestDTO dto
            ){
        WarehouseResponseDTO warehouse = warehouseService.createWarehouse(dto);

        return ResponseEntity.ok(warehouse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> updateWarehouse(
            @PathVariable long id,
            @Valid @RequestBody WarehouseRequestDTO dto
    ){
        WarehouseResponseDTO updatedWarehouse = warehouseService.updateWarehouse(dto,id);

        return ResponseEntity.ok(updatedWarehouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWarehouse(@PathVariable long id){
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok("Warehouse Deleted Successfully");
    }

}
