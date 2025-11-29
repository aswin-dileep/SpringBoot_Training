package InventorySystem.services;

import InventorySystem.dto.WarehouseRequestDTO;
import InventorySystem.dto.WarehouseResponseDTO;
import InventorySystem.entity.Warehouse;
import InventorySystem.mapper.WarehouseMapper;
import InventorySystem.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseMapper warehouseMapper;

    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto){
        Warehouse warehouse = warehouseMapper.toEntity(dto);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(savedWarehouse);
    }

    public WarehouseResponseDTO getWarehouseById(long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Warehouse not founded "+id));

        return warehouseMapper.toDTO(warehouse);
    }

    public WarehouseResponseDTO updateWarehouse(WarehouseRequestDTO dto,long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Warehouse with the given id not founded"));
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(updatedWarehouse);
    }

    public void deleteWarehouse(Long id){
        if(!warehouseRepository.existsById(id)){
            throw new RuntimeException("Warehouse with id: "+id+" not found");
        }

        warehouseRepository.deleteById(id);
    }

    public List<WarehouseResponseDTO> getAllWarehouse(){
        List<Warehouse> warehouses = warehouseRepository.findAll();

        return warehouses
                .stream()
                .map(warehouseMapper::toDTO)
                .toList();
    }
}
