package ru.vlsv.ShopSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsv.ShopSpring.entities.Purchase;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 30.10.2019
 * @link https://github.com/Centnerman
 */

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
