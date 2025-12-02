package InventorySystem.mapper;

import InventorySystem.dto.ProductRequestDTO;
import InventorySystem.dto.ProductResponseDTO;
import InventorySystem.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequestDTO dto){


//        Product product = new Product();
//        product.setName(dto.getName());
//        product.setCategory(dto.getCategory());
//        product.setPrice(dto.getPrice());
//
//        return product;
          return Product.builder()
                  .name(dto.getName())
                  .category(dto.getCategory())
                  .price(dto.getPrice())
                  .build();
    }

    public ProductResponseDTO toDTO(Product product){

        return ProductResponseDTO
                .builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }


}
