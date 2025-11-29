package InventorySystem.repositories;

import InventorySystem.entity.Inventory;
import InventorySystem.entity.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
