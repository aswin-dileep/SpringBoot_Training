package InventorySystem.services;

import InventorySystem.dto.InventoryRequestDTO;
import InventorySystem.dto.InventoryResponseDTO;
import InventorySystem.entity.Inventory;
import InventorySystem.entity.InventoryId;
import InventorySystem.entity.Product;
import InventorySystem.entity.Warehouse;
import InventorySystem.exceptions.BadRequestException;
import InventorySystem.exceptions.InventoryAlreadyExistsException;
import InventorySystem.exceptions.ResourceNotFoundException;
import InventorySystem.mapper.InventoryMapper;
import InventorySystem.repositories.InventoryRepository;
import InventorySystem.repositories.ProductRepository;
import InventorySystem.repositories.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryService {


    private final InventoryRepository inventoryRepository;

    private final InventoryMapper inventoryMapper;

    private final ProductRepository productRepository;

    private final WarehouseRepository warehouseRepository;


    @Transactional
    public InventoryResponseDTO createInventory(InventoryRequestDTO dto){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product not founded"));
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(()-> new ResourceNotFoundException("Warehouse not founded "));

        InventoryId id = new InventoryId(product.getId(), warehouse.getId());

        if(inventoryRepository.existsById(id)){
            throw new InventoryAlreadyExistsException("Inventory with product id "+product.getId()+" and warehouse id "+warehouse.getId()+" Already Exist");
        }

        Inventory inventory = inventoryMapper.toEntity(dto,product,warehouse);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toDTO(savedInventory);
    }

    @Transactional
    public InventoryResponseDTO updateInventory(Long productId, Long warehouseId, InventoryRequestDTO dto) {

        InventoryId id = new InventoryId(productId, warehouseId);

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));

        if (dto.getQuantity() < 0) {
            throw new BadRequestException("Quantity cannot be negative");
        }

        inventory.setQuantity(dto.getQuantity());

        Inventory updated = inventoryRepository.save(inventory);

        return inventoryMapper.toDTO(updated);
    }

    public InventoryResponseDTO getInventoryById(Long productId, Long warehouseId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        InventoryId id = new InventoryId(productId, warehouseId);

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory does not exist"));

        return inventoryMapper.toDTO(inventory);
    }

    public List<InventoryResponseDTO> getAllInventory(){
        List<Inventory> inventories= inventoryRepository.findAll();

        return inventories
                .stream()
                .map(inventory -> inventoryMapper.toDTO(inventory))
                .toList();
    }

    @Transactional
    public void deleteInventory(Long productId,long warehouseId){
        InventoryId id = new InventoryId(productId,warehouseId);

        if(!inventoryRepository.existsById(id)){
            throw new ResourceNotFoundException("Inventory not founded ");
        }
        inventoryRepository.deleteById(id);
    }

    public Integer totalProductQuantity(Long productId){

        productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not founded"));

        List<Inventory> list = inventoryRepository.findAll();

        int total = list
                .stream()
                .filter(inv->inv.getProduct().getId().equals(productId))
                .mapToInt(Inventory::getQuantity)
                .sum();

        return total;

    }



}
