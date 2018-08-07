package customermanagement.customer.controller;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Return customer by ID from database
     * @param id - customer ID
     * @return - return customer by ID
     */
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CustomerDTO findById(@PathVariable Long id){
        return customerService.findById(id);
    }

    /**
     * Return all customers from database
     * @return - return with all customer
     */
    @GetMapping
    @ResponseBody
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    /**
     * Add new customer
     * @param customerDTO - customerDTO
     */
    @PostMapping
    @ResponseBody
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
    }

    /**
     * Delete customer
     * @param id - customer ID
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteIdCheck(id);
    }

    /**
     * Update customer
     * @param customerDTO - customer DTO
     */
    @PutMapping
    @ResponseBody
    public void updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCheck(customerDTO);
    }
}
