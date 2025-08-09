package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entity.Product;
import org.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}