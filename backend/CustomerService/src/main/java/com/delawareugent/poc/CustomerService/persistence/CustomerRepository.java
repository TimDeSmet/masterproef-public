package com.delawareugent.poc.CustomerService.persistence;

import com.delawareugent.poc.CustomerService.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}
