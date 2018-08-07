package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Convert Customer Entity to DTO
     * @param customer - customer type
     * @return - return customerDTO type
     */
    private static CustomerDTO convertToDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(),customer.getPhone(),customer.getAddress());
    }

    /**
     * Convert Customer Entity List to DTO List
     * @param customerList - customer list
     * @return - customerDTO list
     */
    private static List<CustomerDTO> convertToDTOList(List<Customer> customerList){
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer cus: customerList) {
            dtoList.add(convertToDTO(cus));
        }
        return dtoList;
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
