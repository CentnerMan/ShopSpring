package ru.vlsv.ShopSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsv.ShopSpring.entities.OrderItem;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.12.2019
 * @link https://github.com/Centnerman
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
