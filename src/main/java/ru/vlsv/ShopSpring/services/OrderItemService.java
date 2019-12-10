package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vlsv.ShopSpring.repositories.OrderItemRepository;
import ru.vlsv.ShopSpring.repositories.OrderRepository;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.12.2019
 * @link https://github.com/Centnerman
 */

public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
}
