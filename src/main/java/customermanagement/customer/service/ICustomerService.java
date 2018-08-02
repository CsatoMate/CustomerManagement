package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> findAll();

    CustomerDTO findById(Long pId);

    CustomerDTO addCustomer(Customer pCustomer);

    void deleteById(Long pId);

    void saveCustomer(Customer pCustomer);

    CustomerDTO updateCheck(Long pId, CustomerDTO pCustomer);

    CustomerDTO deleteIdCheck(Long pId);

    List<CustomerDTO> deleteNameCheck(String pName);
}
