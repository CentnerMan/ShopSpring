package ru.vlsv.ShopSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.filters.ProductsFilter;
import ru.vlsv.ShopSpring.repositories.specifications.ProductSpecifications;
import ru.vlsv.ShopSpring.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Controller
@RequestMapping("products")
public class ProductController {
    private ProductService productService;
    private static final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showProducts(Model model, HttpServletRequest request,
                               @RequestParam(name = "word", required = false) String word,
                               @RequestParam(name = "min", required = false) BigDecimal min,
                               @RequestParam(name = "max", required = false) BigDecimal max,
                               @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                               @RequestParam(name = "pageSize", required = false) Integer pageSize
    ) {
        ProductsFilter productsFilter = new ProductsFilter(request);

        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        model.addAttribute("word", word);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("filters", productsFilter.getFiltersString());

        Page<Product> page = productService.findAllByPagingAndFiltering(productsFilter.getSpecification(),
                PageRequest.of(pageNumber - 1, pageSize, Sort.Direction.ASC, "id"));
        model.addAttribute("page", page);
        return "products";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createCategoryFrom(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("action", "create");
        return "product";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editForm(@RequestParam("id") Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalStateException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("action", "edit");
        return "product";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

}
