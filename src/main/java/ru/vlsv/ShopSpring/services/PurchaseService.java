package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vlsv.ShopSpring.entities.Purchase;
import ru.vlsv.ShopSpring.repositories.PurchaseRepository;

import java.util.List;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 30.10.2019
 * @link https://github.com/Centnerman
 */

@Service
public class PurchaseService {
    private PurchaseRepository purchaseRepository;

    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
}
