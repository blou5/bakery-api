package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.ProductMapper;
import org.example.dto.request.create.ProductCreateDTO;
import org.example.dto.response.ProductResponseDTO;
import org.example.entity.Product;
import org.example.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/findAll")
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        List<Product> products = productService.findAll();
        if (products == null || products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products.stream().map(ProductResponseDTO::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Integer id) {
        return productService.findById(id)
                .map(ProductResponseDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductCreateDTO product) {
        Product entity = productMapper.toEntity(product);
        Product saved = productService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.from(saved));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductResponseDTO> update(@RequestBody Product product) {
         Product saved = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDTO.from(saved));
    }

// TODO see the best practices for deleting an item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
