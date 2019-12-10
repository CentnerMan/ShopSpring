package ru.vlsv.ShopSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllByIdBetween(Long minId, Long maxID) {
        return productRepository.findAllByIdBetween(minId, maxID);
    }

    public List<Product> findAllByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAllByPriceBetweenOrderByPrice(minPrice, maxPrice);
    }

    public Product minPrice() {
        return productRepository.findFirstByOrderByPriceAsc();
    }

    public Product maxPrice() {
        return productRepository.findFirstByOrderByPriceDesc();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> findAllByPagingAndFiltering(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }
}
