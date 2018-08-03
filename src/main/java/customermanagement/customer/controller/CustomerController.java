package customermanagement.customer.controller;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @PostMapping("/addd")
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









    /**Show all Customer*/
    @GetMapping(value = "/show")
    public String index(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "show";
    }

    @GetMapping(value = "/add")
    public String addNewCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "controller";
    }

    @PostMapping("/add")
    public String addNewCustomer(@ModelAttribute("customer") Customer customer){
        customerService.addCustomer(customer);
        return "controller";
    }

/*
    @PostMapping("/add")
    public String addNewCustomer(@RequestParam("name") String name, @RequestParam("phone") String phone,
                                 @RequestParam("address") String address){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerService.addCustomer(customer);
        return "controller";
    }
*/

    /**Delete Customer by ID*/
    @GetMapping(value = "/remove")
    public String deleteId(Model model){
        model.addAttribute("remove", "Done!");
        return "controller";
    }

    @PostMapping("/remove")
    public String deleteId(@RequestParam("deletedID") Long pid){
        CustomerDTO del = customerService.deleteIdCheck(pid);
        return "controller";
    }




}
