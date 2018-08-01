package customermanagement.customer.controller;

import customermanagement.customer.model.Customer;
import customermanagement.customer.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
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
    @ResponseBody
    public Customer update(@RequestBody Customer pCustomer, @PathVariable Long pId){
        return customerService.updateCheck(pId, pCustomer);
    }

    @DeleteMapping("/deletebyid/{pId}")
    @ResponseBody
    public Customer deleteById(@PathVariable Long pId){
        return customerService.deleteIdCheck(pId);
    }

    @DeleteMapping("/delete/{pName}")
    @ResponseBody
    public List<Customer> deleteByName(@PathVariable String pName){
        return customerService.deleteNameCheck(pName);
    }

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "index";
    }



}
