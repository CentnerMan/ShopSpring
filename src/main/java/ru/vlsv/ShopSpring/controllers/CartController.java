package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.services.ProductsService;
import ru.vlsv.ShopSpring.tools.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    private ProductsService productsService;

    private Cart cart;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("")
    public String show(Model model, HttpSession session) {
//        Cart cartX = (Cart) session.getAttribute("scopedTarget.cart");
        model.addAttribute("items", cart.getItems().values());
        return "cart";
    }

    @GetMapping("/add")
    public void addProductToCart(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = productsService.findById(id);
        cart.addProduct(product);
        response.sendRedirect(request.getHeader("referer"));
    }
}