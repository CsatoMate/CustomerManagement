package customermanagement.customer.service;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**Convert Customer Entity to DTO*/
    private static CustomerDTO convertToDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        dto.id = customer.getId();
        dto.name = customer.getName();
        dto.address = customer.getAddress();
        dto.phone = customer.getPhone();
        return dto;
    }

    private static List<CustomerDTO> convertToDTOList(List<Customer> customerList){
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer cus: customerList) {
            dtoList.add(convertToDTO(cus));
        }
        return dtoList;
    }

    private static Customer convertToEntity(CustomerDTO customerDTO){
        Customer cus = new Customer();
        cus.setId(customerDTO.id);
        cus.setName(customerDTO.name);
        cus.setPhone(customerDTO.phone);
        cus.setAddress(customerDTO.address);

        return cus;
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
    public CustomerDTO addCustomer(Customer customer) {
        customerRepository.save(customer);
        return convertToDTO(customer);
    }

    @Override
    public void deleteById(Long pId) {
        customerRepository.deleteById(pId);

    }

    @Override
    public void saveCustomer(Customer pCustomer) {
        customerRepository.save(pCustomer);
    }


    /**BUSINESS LOGIC START
     * Update Customer by Id*/
    public CustomerDTO updateCheck(Long pId, CustomerDTO pCustomer){
        CustomerDTO updateCustomer = findById(pId);
        if (updateCustomer == null){
            return null;
        }
        saveCustomer(convertToEntity(pCustomer));
        return updateCustomer;
    }

    /**Delete Customer by ID*/
    public CustomerDTO deleteIdCheck(Long pId){
        CustomerDTO aDelete = findById(pId);
        if (aDelete == null){
            return null;
        }
        deleteById(pId);
        return  aDelete;
    }

    /** Delete Customer by Name*/
    public List<CustomerDTO> deleteNameCheck(String pName){
        List<CustomerDTO> all = findAll();
        ArrayList<CustomerDTO> aSorted = new ArrayList<>();
        for (CustomerDTO cus: all) {
            deleteById(cus.id);
            aSorted.add(cus);
        }
        if (aSorted.size() > 0){
            return aSorted;
        }
        else{
            return null;
        }
    }


}
