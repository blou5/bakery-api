package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.entity.VariableExpenseHeader;
import org.example.repositories.VariableExpenseHeaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VariableExpenseHeaderService {

    private final VariableExpenseHeaderRepository variableExpenseHeaderRepository;

    public List<VariableExpenseHeader> findAll() {
        return variableExpenseHeaderRepository.findAll();
    }

    public Optional<VariableExpenseHeader> findById(Integer id) {
        Optional<VariableExpenseHeader> byId = variableExpenseHeaderRepository.findById(id);
        byId.ifPresent(variableExpenseHeader -> System.out.println(variableExpenseHeader.getExpenseHeaders()));
        return byId;
    }

    @Transactional
    public VariableExpenseHeader save(VariableExpenseHeader entity) {
        return variableExpenseHeaderRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        variableExpenseHeaderRepository.deleteById(id);
    }
}