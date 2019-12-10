package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsv.ShopSpring.entities.Order;
import ru.vlsv.ShopSpring.entities.User;
import ru.vlsv.ShopSpring.repositories.OrderRepository;
import ru.vlsv.ShopSpring.tools.Cart;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.12.2019
 * @link https://github.com/Centnerman
 */

@Service
public class OrderService {
    private OrderRepository orderRepository;

    private Cart cart;

    @Autowired
    private UserService userService;

    @Autowired
    public OrderService(OrderRepository orderRepository, Cart cart) {
        this.orderRepository = orderRepository;
        this.cart = cart;
    }

    public Order createOrder(User user) {
        Order order = new Order(user);
        cart.getItems().values().forEach(order::addItem);
        cart.clear();
        return orderRepository.save(order);
    }
}
