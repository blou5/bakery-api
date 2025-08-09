package org.example.repositories;

import org.example.entity.ChangeReserveLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ChangeReserveLogRepository extends JpaRepository<ChangeReserveLog, Integer> {

    @Query("SELECT c FROM ChangeReserveLog c WHERE c.status = :status AND c.denomination = :denomination " +
            "ORDER BY c.createdAt ASC")
    List<ChangeReserveLog> findByStatusAndDenominationOrdered(ChangeReserveLog.Status status, Integer denomination);

    List<ChangeReserveLog> findByDenominationAndStatusIn(Integer denomination, List<ChangeReserveLog.Status> statuses);

    List<ChangeReserveLog> findByStatus(ChangeReserveLog.Status status);

    Page<ChangeReserveLog> findAll(Pageable pageable);

}