package InventorySystem.repositories;

import InventorySystem.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

}
