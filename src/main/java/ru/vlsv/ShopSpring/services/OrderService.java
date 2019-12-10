package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vlsv.ShopSpring.repositories.OrderRepository;
import ru.vlsv.ShopSpring.repositories.ProductRepository;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.12.2019
 * @link https://github.com/Centnerman
 */

public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
