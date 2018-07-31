package customermanagement.customer.controller;

import customermanagement.customer.model.Customer;
import customermanagement.customer.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    /**Return all data from database*/
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer pCustomer){
        return customerService.addCustomer(pCustomer);
    }

    @PutMapping("/update/{pId}")
    public Customer update(@RequestBody Customer pCustomer, @PathVariable Long pId){
        return customerService.updateCheck(pId, pCustomer);
    }

    @DeleteMapping("/deletebyid/{pId}")
    public Customer deleteById(@PathVariable Long pId){
        return customerService.deleteIdCheck(pId);
    }

    @DeleteMapping("/delete/{pName}")
    public List<Customer> deleteByName(@PathVariable String pName){
        return customerService.deleteNameCheck(pName);
    }
}
