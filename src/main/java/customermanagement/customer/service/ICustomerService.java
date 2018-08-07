package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    /**
     * Return all customers from database
     * @return - customerDTO list
     */
    List<CustomerDTO> findAll();

    /**
     * Return customer by ID from database
     * @param id - customer id
     * @return - return with customerDTO
     */
    CustomerDTO findById(Long id);

    /**
     * Check ID is Exist or Not
     * @param id - customer ID
     * @return - true or false
     */
    boolean existsById(Long id);

    /**
     * Add new customer to database
     * @param customer - customerDTO
     */
    void addCustomer(CustomerDTO customer);

    /**
     * Delete customer by ID from database
     * @param id - customer ID
     */
    void deleteById(Long id);

    /**
     * Update customer to database
     * @param customer - customerDTO
     */
    void saveCustomer(CustomerDTO customer);

    /**
     * Check updating customer is exist
     * @param customer - customerDTO
     */
    void updateCheck(CustomerDTO customer);

    /**
     * Check deleting customer is exist
     * @param id - customer ID
     */
    void deleteIdCheck(Long id);
}
