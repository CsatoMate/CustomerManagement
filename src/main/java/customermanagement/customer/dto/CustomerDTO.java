package customermanagement.customer.dto;

import customermanagement.customer.model.Customer;

public class CustomerDTO {

    private Long id ;

    private String name;

    private String phone;

    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public CustomerDTO(Customer customer) {
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.address = customer.getAddress();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
