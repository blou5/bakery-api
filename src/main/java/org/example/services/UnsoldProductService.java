package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entity.UnsoldProduct;
import org.example.repositories.UnsoldProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UnsoldProductService {

    private final UnsoldProductRepository unsoldProductRepository;

    public List<UnsoldProduct> findAll() {
        return unsoldProductRepository.findAll();
    }

    public Optional<UnsoldProduct> findById(Integer id) {
        return unsoldProductRepository.findById(id);
    }


    public Optional<UnsoldProduct> findWithDetailsById(Integer id) {
        return unsoldProductRepository.findWithDetailsById(id);
    }

    @Transactional
    public UnsoldProduct save(UnsoldProduct entity) {
        return unsoldProductRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        unsoldProductRepository.deleteById(id);
    }
}
