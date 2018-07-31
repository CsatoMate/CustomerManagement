package customermanagement.customer.service;

import customermanagement.customer.model.Customer;
import customermanagement.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long pId) {
        return customerRepository.findById(pId);
    }

    @Override
    public Customer addCustomer(Customer pCustomer) {
        customerRepository.save(pCustomer);
        return pCustomer;
    }

    @Override
    public void deleteById(Long pId) {
        customerRepository.deleteById(pId);

    }

    @Override
    public void deleteByName(Customer pCustomer) {
        customerRepository.delete(pCustomer);
    }

    @Override
    public void saveCustomer(Customer pCustomer) {
        customerRepository.save(pCustomer);
    }


    /**BUSINESS LOGIC START
     * Update Customer by Id*/
    public Customer updateCheck(Long pId, Customer pCustomer){
        Optional<Customer> updateCustomer = findById(pId);
        if(!updateCustomer.isPresent()){
            return null;
        }
        pCustomer.setId(pId);
        saveCustomer(pCustomer);
        return pCustomer;
    }

    /**Delete Customer by ID*/
    public Customer deleteIdCheck(Long pId){
        Optional<Customer> aDelete = findById(pId);
        if(!aDelete.isPresent()){
            return null;
        }
        Customer aTemp = aDelete.get();
        deleteById(pId);
        return  aTemp;
    }

    /** Delete Customer by Name*/
    public List<Customer> deleteNameCheck(String pName){
        List<Customer> all = findAll();
        ArrayList<Customer> aSorted = new ArrayList<>();
        for (Customer cus: all) {
            if (cus.getName().equals(pName)) {
                deleteByName(cus);
                aSorted.add(cus);
            }

        }
        if (aSorted.size() > 0){
            return aSorted;
        }
        else{
            return null;
        }
    }


}
