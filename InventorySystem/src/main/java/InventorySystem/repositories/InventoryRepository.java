package InventorySystem.repositories;

import InventorySystem.entity.Inventory;
import InventorySystem.entity.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
}
