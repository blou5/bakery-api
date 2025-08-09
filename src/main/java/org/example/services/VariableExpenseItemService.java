package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entity.VariableExpenseItem;
import org.example.repositories.VariableExpenseItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VariableExpenseItemService {

    private final VariableExpenseItemRepository variableExpenseItemRepository;

    public List<VariableExpenseItem> findAll() {
        return variableExpenseItemRepository.findAll();
    }

    public Optional<VariableExpenseItem> findById(Integer id) {
        return variableExpenseItemRepository.findById(id);
    }

    @Transactional
    public VariableExpenseItem save(VariableExpenseItem entity) {
        return variableExpenseItemRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        variableExpenseItemRepository.deleteById(id);
    }
}
