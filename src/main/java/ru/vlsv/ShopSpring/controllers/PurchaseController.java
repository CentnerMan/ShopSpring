package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vlsv.ShopSpring.entities.Purchase;
import ru.vlsv.ShopSpring.services.PurchaseService;

import java.util.List;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 30.10.2019
 * @link https://github.com/Centnerman
 */

@Controller
public class PurchaseController {
    private PurchaseService purchaseService;

    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchases")
    public String showProductsPage(Model model) {
        List<Purchase> purchasesList = purchaseService.findAll();
        model.addAttribute("purchases", purchasesList);
        return "purchases";
    }
}
