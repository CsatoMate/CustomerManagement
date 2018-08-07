package customermanagement.customer.mapper;

import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    /**
     * Convert Customer Entity to DTO
     * @param customer - customer type
     * @return - return customerDTO type
     */
    public static CustomerDTO convertToDTO(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(),customer.getPhone(),customer.getAddress());
    }

    /**
     * Convert Customer Entity List to DTO List
     * @param customerList - customer list
     * @return - customerDTO list
     */
    public static List<CustomerDTO> convertToDTOList(List<Customer> customerList){
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer cus: customerList) {
            dtoList.add(convertToDTO(cus));
        }
        return dtoList;
    }
}
