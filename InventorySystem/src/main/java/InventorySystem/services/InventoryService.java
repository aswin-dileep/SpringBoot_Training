package InventorySystem.services;

import InventorySystem.dto.InventoryRequestDTO;
import InventorySystem.dto.InventoryResponseDTO;
import InventorySystem.entity.Inventory;
import InventorySystem.entity.InventoryId;
import InventorySystem.entity.Product;
import InventorySystem.entity.Warehouse;
import InventorySystem.mapper.InventoryMapper;
import InventorySystem.repositories.InventoryRepository;
import InventorySystem.repositories.ProductRepository;
import InventorySystem.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public InventoryService(InventoryRepository inventoryRepository
                            ,InventoryMapper inventoryMapper
                            ,ProductRepository productRepository
                            ,WarehouseRepository warehouseRepository){
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.productRepository = productRepository;
        this.warehouseRepository= warehouseRepository;
    }


    public InventoryResponseDTO createInventory(InventoryRequestDTO dto){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not founded"));
        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(()-> new RuntimeException("Warehouse not founded "));

        Inventory inventory = inventoryMapper.toEntity(dto,product,warehouse);

        Inventory savedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toDTO(savedInventory);
    }

    public InventoryResponseDTO updateInventory(InventoryRequestDTO dto){
        InventoryId id = new InventoryId(dto.getProductId(), dto.getWarehouseId());

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Inventory not founded with given id"));

        inventory.setQuantity(dto.getQuantity());

        Inventory updatedInventory = inventoryRepository.save(inventory);

        return inventoryMapper.toDTO(updatedInventory);
    }

    public InventoryResponseDTO getInventoryById(InventoryRequestDTO dto){
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("product not founded"));

        Warehouse warehouse = warehouseRepository.findById(dto.getWarehouseId())
                .orElseThrow(()-> new RuntimeException("Warehouse not founded"));

        InventoryId id = new InventoryId(product.getId(),warehouse.getId());

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory does not exist"));

        return inventoryMapper.toDTO(inventory);
    }

    public void deleteInventory(Long productId,long warehouseId){
        InventoryId id = new InventoryId(productId,warehouseId);

        if(!inventoryRepository.existsById(id)){
            throw new RuntimeException("Inventory not founded ");
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
