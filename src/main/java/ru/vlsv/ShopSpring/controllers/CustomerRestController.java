package ru.vlsv.ShopSpring.controllers;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vlsv.ShopSpring.entities.Customer;
import ru.vlsv.ShopSpring.errors_handlers.ResourceNotFoundException;
import ru.vlsv.ShopSpring.services.CustomerService;

import java.util.ArrayList;
import java.util.List;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 10.11.2019
 * @link https://github.com/Centnerman
 */

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


//    @GetMapping("/")
//    public List<Customer> findAll() {
//        return customerService.findAll();
//    }

//    @GetMapping("/{id}")
//    public Customer findById(@PathVariable Long id) {
//        return customerService.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
//    }

    @GetMapping("/")
    public List<Customer> findAllWithoutPurchases() {
        return customerService.findAllWithoutPurchases();
    }

    @GetMapping("/{id}")
    public Customer findByIdWithoutPurchases(@PathVariable Long id) {
        return customerService.findByIdWithoutPurchases(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer saveOrUpdate(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerService.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    return customerService.save(customer);
                }).orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerService.save(newCustomer);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}
