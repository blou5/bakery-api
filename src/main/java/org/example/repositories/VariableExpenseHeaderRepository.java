package org.example.repositories;

import org.example.entity.VariableExpenseHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariableExpenseHeaderRepository extends JpaRepository<VariableExpenseHeader, Integer> {
}