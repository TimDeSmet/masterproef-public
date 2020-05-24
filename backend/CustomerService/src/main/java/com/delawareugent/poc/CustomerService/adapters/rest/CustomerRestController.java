package com.delawareugent.poc.CustomerService.adapters.rest;

import com.delawareugent.poc.CustomerService.CustomerServiceApplication;
import com.delawareugent.poc.CustomerService.domain.Customer;
import com.delawareugent.poc.CustomerService.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("customers")
public class CustomerRestController {

    private final CustomerService customerService;
    private static Logger logger = LoggerFactory.getLogger(CustomerServiceApplication.class);

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers(){
        logger.info("Customer Service received GET request for all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") String customerId){
        logger.info("Customer Service received GET request for customer with id: " + customerId);
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public Iterable<Customer> addCustomer(@RequestBody Customer customer){
        logger.info("Customer Service received POST request to create a customer...");
        customerService.addCustomer(customer);
        logger.info("...Creation was successful");
        return customerService.getAllCustomers();
    }

    //TODO should only be able do delete himself
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String customerId){
        logger.info("Customer Service received DELETE request to delete the customer with ID: " + customerId);
        customerService.deleteCustomer(customerId);
        logger.info("...Deletion was successful");
    }
}
