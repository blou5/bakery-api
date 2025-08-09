package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entity.ProductProduction;
import org.example.repositories.ProductProductionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductProductionService {

    private final ProductProductionRepository productProductionRepository;

    public List<ProductProduction> findAll() {
        return productProductionRepository.findAll();
    }

    public Optional<ProductProduction> findById(Integer id) {
        return productProductionRepository.findById(id);
    }

    public Optional<ProductProduction> findDetailById(Integer id){
        return productProductionRepository.findWithDetailsById(id);
    }
    @Transactional
    public ProductProduction save(ProductProduction entity) {
        return productProductionRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        productProductionRepository.deleteById(id);
    }
}