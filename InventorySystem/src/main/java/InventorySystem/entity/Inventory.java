package InventorySystem.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product_warehouse")
public class Inventory {

    @EmbeddedId
    private InventoryId id;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name ="product_id")
    private Product product;

    @MapsId("warehouseId")
    @ManyToOne
    @JoinColumn(name ="warehouse_id")
    private Warehouse warehouse;

    @Min(0)
    private Integer quantity;

}
