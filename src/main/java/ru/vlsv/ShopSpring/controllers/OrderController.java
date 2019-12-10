package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlsv.ShopSpring.entities.Order;
import ru.vlsv.ShopSpring.entities.User;
import ru.vlsv.ShopSpring.services.MailService;
import ru.vlsv.ShopSpring.services.OrderService;
import ru.vlsv.ShopSpring.services.UserService;

import java.security.Principal;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.12.2019
 * @link https://github.com/Centnerman
 */

@Controller
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/create")
    public String createOrder(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.createOrder(user);
        mailService.sendOrderMail(order);
        return "redirect:/shop";
    }
}

