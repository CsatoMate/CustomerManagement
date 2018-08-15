package customermanagement.customer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.model.Customer;
import customermanagement.customer.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest()
@AutoConfigureMockMvc(secure = false)
public class CustomerApplicationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerService customerTestService;

    @Test
    public void contextLoads(){
        assertThat(customerTestService).isNotNull();
    }

    @Test
    public void FindAllTest() throws Exception{
        Mockito.when(customerTestService.findAll()).thenReturn(
                Collections.emptyList());

        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/")
                        .accept(MediaType.APPLICATION_JSON))
                    .andReturn();

        System.out.println(mvcResult.getResponse());

        Mockito.verify(customerTestService).findAll();
    }

    @Test
    public void whenFindId(){
        CustomerDTO exampleJozsi = new CustomerDTO((long) 18, "Józsi", "1201212", "4345, erteg");

        Mockito.when(customerTestService.findById(exampleJozsi.getId())).thenReturn(exampleJozsi);
    }

    @Test
    public void findIdTest() throws Exception{
        CustomerDTO example1 = new CustomerDTO((long) 18, "Józsi", "1201212", "4345, erteg");

        mockMvc.perform(get("/api/customer/" + example1.getId()))
                .andExpect(status().isOk());
    }


    @Test
    public void addUpdateDeleteTest() throws Exception{

        CustomerDTO example1 = new CustomerDTO((long) 10, "Józsi", "1201212", "4345, erteg");

        //byte[] exampleJson = toJson(example1);
        String eJson = mapToJson(example1);

        //add Test
        mockMvc.perform(post("/api/customer/")
                    .content(eJson)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        CustomerDTO example2 = new CustomerDTO((long) 10, "Béla", "98473223", "67563, fsdfghgfhgfdh");
        //Update Test
        mockMvc.perform(put("/api/customer/")
                    .content(toJson(example2))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Delete Test
        mockMvc.perform(delete("/api/customer/" + 12))
                .andExpect(status().isOk());
    }


    @Test
    public void getTest() throws Exception{

        CustomerDTO exampleJozsi = new CustomerDTO((long) 10, "Józsi", "1201212", "4345, erteg");

        mockMvc.perform(get("/api/customer/" + exampleJozsi.getId())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private byte[] toJson(CustomerDTO r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    private String mapToJson(CustomerDTO cus) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cus);
    }

    private <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
}
