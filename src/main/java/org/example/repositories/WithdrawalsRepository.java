package org.example.repositories;

import org.example.entity.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WithdrawalsRepository extends JpaRepository<Withdrawals, Integer> {


    List<Withdrawals> findByLog_LogId(Integer logId);

    List<Withdrawals> findByDate(LocalDate localDate);
}