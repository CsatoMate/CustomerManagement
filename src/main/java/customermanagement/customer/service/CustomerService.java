package customermanagement.customer.service;

import customermanagement.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> findAll();

    Optional<Customer> findById(Long pId);

    Customer addCustomer(Customer pCustomer);

    void deleteById(Long pId);

    void deleteByName(Customer pCustomer);

    void saveCustomer(Customer pCustomer);
}
