package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.services.ProductService;
import ru.vlsv.ShopSpring.tools.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 17.11.2019
 * @link https://github.com/Centnerman
 */

@Controller
@RequestMapping("/cart")
public class CartController {
    private ProductService productService;

    private Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", cart.getProducts());
        return "cart";
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        productService.findById(id).ifPresent(product -> cart.addProduct(product));
        response.sendRedirect(request.getHeader("referer"));
    }

}