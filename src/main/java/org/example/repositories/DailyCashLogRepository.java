package org.example.repositories;


import org.example.entity.DailyCashLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DailyCashLogRepository extends JpaRepository<DailyCashLog, Integer> {
    List<DailyCashLog> findByLogDate(LocalDate logDate);
}