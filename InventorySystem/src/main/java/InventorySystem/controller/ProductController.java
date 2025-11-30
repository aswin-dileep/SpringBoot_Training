package InventorySystem.controller;

import InventorySystem.dto.ProductRequestDTO;
import InventorySystem.dto.ProductResponseDTO;
import InventorySystem.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(
            @PathVariable Long id
    ){
        ProductResponseDTO product = productService.getById(id);

        return ResponseEntity.ok(product);
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> addProduct(
            @Valid @RequestBody ProductRequestDTO dto){
        ProductResponseDTO response = productService.createProduct(dto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable long id,
            @Valid @RequestBody ProductRequestDTO dto){

        ProductResponseDTO updatedProduct = productService.updateProduct(id,dto);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id
    ){
         productService.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted successfully");
    }

}
