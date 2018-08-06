package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    /**
     * Return all customers from database
     * @return
     */
    List<CustomerDTO> findAll();

    /**
     * Return customer by ID from database
     * @param pId
     * @return
     */
    CustomerDTO findById(Long pId);

    /**
     * Check ID is Exist or Not
     * @param id
     * @return
     */
    boolean existsById(Long id);

    /**
     * Add new customer to database
     * @param pCustomer
     */
    void addCustomer(CustomerDTO pCustomer);

    /**
     * Delete customer by ID from database
     * @param pId
     */
    void deleteById(Long pId);

    /**
     * Update customer to database
     * @param pCustomer
     */
    void saveCustomer(CustomerDTO pCustomer);

    /**
     * Check updating customer is exist
     * @param pCustomer
     */
    void updateCheck(CustomerDTO pCustomer);

    /**
     * Check deleting customer is exist
     * @param pId
     */
    void deleteIdCheck(Long pId);
}
