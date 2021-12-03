package br.com.productms.repository;

import br.com.productms.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice AND (lower(p.name) LIKE lower(concat('%', :nameOrDescription, '%')) OR lower(p.description) LIKE lower(concat('%', :nameOrDescription, '%')))")
    List<Product> search(@Param("nameOrDescription") String nameOrDescription, @Param("minPrice") BigDecimal minPrice,@Param("maxPrice") BigDecimal maxPrice);
}
