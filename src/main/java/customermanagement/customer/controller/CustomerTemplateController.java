package customermanagement.customer.controller;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class CustomerTemplateController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerTemplateController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    /**Return all customers from database*/
    @GetMapping("/api/customer")
    @ResponseBody
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    /**Show all Customer*/
    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("customers", findAll());
        return "index";
    }

    /**Return customer by ID from database*/
    @GetMapping(value = "/api/customer/{id}")
    @ResponseBody
    public CustomerDTO findById(@PathVariable Long id){
        return customerService.findById(id);
    }

}
