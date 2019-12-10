package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.repositories.ProductsRepository;

import java.util.List;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification, Pageable pageable) {
        return productsRepository.findAll(specification, pageable);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    public List<Product> findAll() {
        return (List<Product>)productsRepository.findAll();
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).get();
    }
}