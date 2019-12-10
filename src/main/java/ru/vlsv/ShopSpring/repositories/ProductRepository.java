package ru.vlsv.ShopSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.vlsv.ShopSpring.entities.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findOneByTitle(String title);

    List<Product> findAllByIdBetween(Long minId, Long maxId);

    List<Product> findAllByCostBetweenOrderByCost(BigDecimal minCost, BigDecimal maxCost);

    Product findFirstByOrderByCostAsc();

    Product findFirstByOrderByCostDesc();

}
