package org.example.repositories;

import org.example.entity.UnsoldProduct;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnsoldProductRepository extends JpaRepository<UnsoldProduct, Integer> {
    @Query("""
       SELECT u FROM UnsoldProduct u
       JOIN FETCH u.product
       JOIN FETCH u.log
       WHERE u.unsoldId = :id
       """)
    Optional<UnsoldProduct> findWithDetailsById(@Param("id") Integer id);
    @EntityGraph(attributePaths = {"product"})
    List<UnsoldProduct> findByLog_LogId(Integer logId);
}