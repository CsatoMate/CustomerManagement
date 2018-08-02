package customermanagement.customer.controller;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CustomerController {


    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    /**Return all data from database*/
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseBody
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/add")
    @ResponseBody
    public CustomerDTO addCustomer(@Valid @RequestBody Customer pCustomer){
        return customerService.addCustomer(pCustomer);
    }

    @PutMapping("/update/{pId}")
    @ResponseBody
    public CustomerDTO update(@RequestBody CustomerDTO pCustomer, @PathVariable Long pId){
        return customerService.updateCheck(pId, pCustomer);
    }

    @DeleteMapping("/deletebyid/{pId}")
    @ResponseBody
    public CustomerDTO deleteById(@PathVariable Long pId){
        return customerService.deleteIdCheck(pId);
    }

    @DeleteMapping("/delete/{pName}")
    @ResponseBody
    public List<CustomerDTO> deleteByName(@PathVariable String pName){
        return customerService.deleteNameCheck(pName);
    }

    @GetMapping(value = "/showall")
    public String index(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "show";
    }



}
