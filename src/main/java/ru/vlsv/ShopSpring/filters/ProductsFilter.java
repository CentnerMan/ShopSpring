package ru.vlsv.ShopSpring.filters;

import org.springframework.data.jpa.domain.Specification;
import ru.vlsv.ShopSpring.entities.Product;
import ru.vlsv.ShopSpring.repositories.specifications.ProductSpecifications;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.11.2019
 * @link https://github.com/Centnerman
 */

public class ProductsFilter {

    private Specification<Product> specification;
    private StringBuilder filtersString;

    public Specification<Product> getSpecification() {
        return specification;
    }

    public StringBuilder getFiltersString() {
        return filtersString;
    }

    public ProductsFilter(HttpServletRequest request) {
        filtersString = new StringBuilder();
        specification = Specification.where(null);

        if (request.getParameter("word") != null && !request.getParameter("word").isEmpty()) {
            specification = specification.and(ProductSpecifications.titleContains(request.getParameter("word")));
            filtersString.append("&word=" + request.getParameter("word"));
        }

        if (request.getParameter("min") != null && !request.getParameter("min").isEmpty()) {
            specification = specification.and(ProductSpecifications.priceGreaterThanOrEq(BigDecimal.valueOf(Double.parseDouble(request.getParameter("min")))));
            filtersString.append("&min=" + request.getParameter("min"));
        }

        if (request.getParameter("max") != null && !request.getParameter("max").isEmpty()) {
            specification = specification.and(ProductSpecifications.priceLesserThanOrEq(BigDecimal.valueOf(Double.parseDouble(request.getParameter("max")))));
            filtersString.append("&max=" + request.getParameter("max"));
        }

        if (request.getParameter("pageSize") != null && !request.getParameter("pageSize").isEmpty()) {
            filtersString.append("&pageSize=" + request.getParameter("pageSize"));
        }
    }
}