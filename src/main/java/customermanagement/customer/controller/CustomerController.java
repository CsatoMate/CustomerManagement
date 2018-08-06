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


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteIdCheck(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public void updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCheck(customerDTO);
    }
}
