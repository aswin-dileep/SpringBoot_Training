package InventorySystem.services;

import InventorySystem.dto.WarehouseRequestDTO;
import InventorySystem.dto.WarehouseResponseDTO;
import InventorySystem.entity.Warehouse;
import InventorySystem.exceptions.ResourceNotFoundException;
import InventorySystem.mapper.WarehouseMapper;
import InventorySystem.repositories.WarehouseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private WarehouseMapper warehouseMapper;

    @Transactional
    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto){
        Warehouse warehouse = warehouseMapper.toEntity(dto);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(savedWarehouse);
    }

    public WarehouseResponseDTO getWarehouseById(long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Warehouse not founded "+id));

        return warehouseMapper.toDTO(warehouse);
    }


    @Transactional
    public WarehouseResponseDTO updateWarehouse(WarehouseRequestDTO dto,long id){
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Warehouse with the given id not founded"));
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);

        return warehouseMapper.toDTO(updatedWarehouse);
    }

    @Transactional
    public void deleteWarehouse(Long id){
        if(!warehouseRepository.existsById(id)){
            throw new ResourceNotFoundException("Warehouse with id: "+id+" not found");
        }

        warehouseRepository.deleteById(id);
    }

    public List<WarehouseResponseDTO> getAllWarehouse() {
        return warehouseRepository
                .findAll()
                .stream()
                .map(warehouseMapper::toDTO)
                .toList();
    }
}
