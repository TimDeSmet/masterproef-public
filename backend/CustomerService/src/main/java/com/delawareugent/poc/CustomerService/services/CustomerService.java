package com.delawareugent.poc.CustomerService.services;

import com.delawareugent.poc.CustomerService.adapters.messaging.MessageGateway;
import com.delawareugent.poc.CustomerService.domain.Customer;
import com.delawareugent.poc.CustomerService.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MessageGateway messageGateway;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, MessageGateway messageGateway){
        this.customerRepository = customerRepository;
        this.messageGateway = messageGateway;
    }

    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String customerId){
        return customerRepository.findById(UUID.fromString(customerId)).get();
    }

    public void addCustomer(Customer customer) {
        customer.setCustomerId(UUID.randomUUID());
        customerRepository.save(customer);
    }

    public void deleteCustomer(String customerId){
        customerRepository.deleteById(UUID.fromString((customerId)));
        messageGateway.customerRemoved(UUID.fromString((customerId)));
    }
}
