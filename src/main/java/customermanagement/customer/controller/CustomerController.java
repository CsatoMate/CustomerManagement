package customermanagement.customer.controller;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
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
