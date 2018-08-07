package customermanagement.customer.controller;

import customermanagement.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerTemplateController {

    private final CustomerService customerService;

    @Autowired
    public CustomerTemplateController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Show all Customer
     * @param model - model
     * @return - return template
     */
    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "index";
    }

}
