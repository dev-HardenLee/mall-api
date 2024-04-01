package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.mallapi.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"imageList"})
    @Query("""
        SELECT p
        FROM Product p
        WHERE 1=1
        AND p.pno = :pno
    """)
    Optional<Product> selectOne(@Param("pno") Long pno);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
        UPDATE Product p
        SET p.delFlag = :delFlag
        WHERE 1=1
        AND p.pno = :pno
    """)
    void updateToDelete(@Param("pno") Long pno, @Param("delFlag") boolean delFlag);

}
