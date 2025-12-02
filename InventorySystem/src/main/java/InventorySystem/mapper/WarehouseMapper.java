package InventorySystem.mapper;


import InventorySystem.dto.WarehouseRequestDTO;
import InventorySystem.dto.WarehouseResponseDTO;
import InventorySystem.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {
    public Warehouse toEntity(WarehouseRequestDTO dto){
//        Warehouse warehouse = new Warehouse();
//        warehouse.setName(dto.getName());
//        warehouse.setLocation(dto.getLocation());
//
//        return warehouse;
        return Warehouse
                .builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public WarehouseResponseDTO toDTO(Warehouse warehouse){
        return  WarehouseResponseDTO
                .builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .location(warehouse.getLocation())
                .build();
    }



}
