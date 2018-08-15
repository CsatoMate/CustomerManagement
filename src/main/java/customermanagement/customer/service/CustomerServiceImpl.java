package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static customermanagement.customer.mapper.Mapper.convertToDTO;
import static customermanagement.customer.mapper.Mapper.convertToDTOList;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        return convertToDTOList(customerList);
    }


    @Override
    public CustomerDTO findById(Long pId) {
        Optional<Customer> cus = customerRepository.findById(pId);
        if(!cus.isPresent()){
            return null;
        }
        Customer customer = cus.get();
        return convertToDTO(customer);

    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        customerRepository.save(customer);
        //return customerDTO;
    }

    @Override
    public void deleteById(Long pId) {
        customerRepository.deleteById(pId);

    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        customerRepository.save(customer);
    }


    /**
     * Check updating customer exist or not
     * @param customerDTO - customerDTO type
     */
    public void updateCheck(CustomerDTO customerDTO){
        if(customerRepository.existsById(customerDTO.getId())){
            saveCustomer(customerDTO);
        }
    }

    /**
     * Check deleted customer by id is exist or not
     * @param id - customer ID
     */
    public void deleteIdCheck(Long id){
        CustomerDTO aDelete = findById(id);
        deleteById(aDelete.getId());
    }
}
