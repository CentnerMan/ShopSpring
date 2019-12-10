package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.repositories.ProductsRepository;
import ru.vlsv.ShopSpring.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }
}
