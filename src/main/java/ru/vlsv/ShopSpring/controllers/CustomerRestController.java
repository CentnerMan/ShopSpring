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

    // В связи с наличием покупок, вывод уходит в бесконечный цикл, представленный ниже.
    // Временно решил проблему хотя бы отображения созданием внутреннего класса без покупок.
    // Не придумал, как можно решить элегантнее :(
    // По логике, нужно просто не раскрывать класс Purchase.
    //
    // {
    //    "id": 1,
    //    "name": "Арнольд",
    //    "purchases": [
    //        {
    //            "id": 1,
    //            "customer": {
    //                "id": 1,
    //                "name": "Арнольд",
    //                "purchases": [
    //                    {
    //                        "id": 1,
    //                        "customer": {
    //                            "id": 1,
    //                            "name": "Арнольд",
    //                            "purchases": [
    //                                {
    //                                    "id": 1,
    //                                    "customer": {
    //                                        "id": 1,
    //                                        "name": "Арнольд",
    //                                        "purchases": [
    //                                            {

    @Data
    @NoArgsConstructor
    private static class CustomerWithoutPurchases {
        private Long id;
        private String name;

        CustomerWithoutPurchases(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

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
    public List<CustomerWithoutPurchases> findAllWithoutPurchases() {
        List<Customer> customerList = customerService.findAll();
        List<CustomerWithoutPurchases> cwp = new ArrayList<>();
        for (Customer customer : customerList) {
            cwp.add(new CustomerWithoutPurchases(customer.getId(), customer.getName()));
        }
        return cwp;
    }

    @GetMapping("/{id}")
    public CustomerWithoutPurchases findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + id));
        return new CustomerWithoutPurchases(customer.getId(), customer.getName());
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
