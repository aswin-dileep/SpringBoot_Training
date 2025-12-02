package InventorySystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id")
    private Long id;

    @NotBlank(message = "WareHouse name should not be empty")
    @Column(name = "warehouse_name")
    private String name;

    @NotBlank(message = "location should not be empty")
    private String location;


}
