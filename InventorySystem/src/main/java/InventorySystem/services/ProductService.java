package InventorySystem.services;

import InventorySystem.dto.ProductRequestDTO;
import InventorySystem.dto.ProductResponseDTO;
import InventorySystem.entity.Product;
import InventorySystem.mapper.ProductMapper;
import InventorySystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponseDTO createProduct(ProductRequestDTO dto){
        Product product =productMapper.toEntity(dto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductResponseDTO getById(long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id: " + id));
        return productMapper.toDTO(product);
    }

    public ProductResponseDTO updateProduct(long id,ProductRequestDTO dto){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not founded with id: "+id));

        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);
    }

    public void deleteProduct(long id){
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Product not founded with id: "+id);
        }
        productRepository.deleteById(id);
    }
}
