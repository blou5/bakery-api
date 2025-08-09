package org.example.repositories;

import org.example.entity.ProductProduction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductProductionRepository extends JpaRepository<ProductProduction, Integer> {

    @Query("SELECT p FROM ProductProduction p " +
            "JOIN FETCH p.product " +
            "JOIN FETCH p.log " +
            "WHERE p.productionId = :id")
    Optional<ProductProduction> findWithDetailsById(@Param("id") Integer id);
    @EntityGraph(attributePaths = {"product"})
    List<ProductProduction> findByLog_LogId(Integer logId);

}