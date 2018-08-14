/*
package customermanagement.customer;


import customermanagement.customer.dto.CustomerDTO;
import customermanagement.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:properties.yml")
@ActiveProfiles("test")
public class CustomerTest {

    @Autowired
    private CustomerService customerService;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void contextLoads() throws Exception {
        assertThat(customerService).isNotNull();
    }

    @Test
    public void  addCustomerTest(){
        CustomerDTO customerDTO = new CustomerDTO((long) 99, "Magdi", "06309876655", "5432. Emőd");

        customerService.addCustomer(customerDTO);
    }

    @Test
    public void findByIdTest(){
        customerService.findById((long) 99);

    }










*/
/*    @Test
    public void addTest(){
        CustomerDTO customerDTO = new CustomerDTO((long) 99, "Magdi", "06309876655", "5432. Emőd");

        HttpEntity<CustomerDTO> entity = new HttpEntity<CustomerDTO>(customerDTO, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURL("/api/customer/"),
                HttpMethod.POST, entity , String.class);

        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertTrue(actual.contains("/api/customer/99"));


    }

    private String createURL(String uri) {
        return "http://localhost:8080" + uri;
    }*//*


}
*/
/*
    Long id = new Long(18);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/customer/{id}", id))
            .andReturn();

    String content = result.getResponse().getContentAsString();
    int status = result.getResponse().getStatus();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
                Assert.assertTrue("failure - HTTP response body to be empty", content.trim().length() == 0);


                CustomerDTO deletingCustomer = customerTestService.findById(id);

                Assert.assertNull("failure - Customer to be null", deletingCustomer);
*/
