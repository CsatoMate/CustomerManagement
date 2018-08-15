package customermanagement.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import customermanagement.customer.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerApplication.class, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerRestTest {

    @LocalServerPort
    private int port = 8080;

    @Autowired
    private TestRestTemplate testRestTemplate;
    private HttpHeaders headers = new HttpHeaders();

    @Before
    @Test
    public void setUp() throws Exception {
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        CustomerDTO cus1 = new CustomerDTO(1L, "Géza", "06309876655", "5432. Emőd");
        CustomerDTO cus2 = new CustomerDTO(2L, "Elemér", "06111111111", "9999. Alma");
        customerDTOList.add(cus1);
        customerDTOList.add(cus2);


        String uri = "/api/customer";

        for (CustomerDTO cus: customerDTOList) {
            String inputJson = this.mapToJson(cus);

            HttpEntity<CustomerDTO> entity = new HttpEntity<>(cus, headers);
            ResponseEntity<String> response = testRestTemplate.exchange(formURL(uri), HttpMethod.POST,entity,String.class);

            String bodyJsonResponse = response.getBody();//testRestTemplate.getForObject(formURL(uri), String.class);
            assertThat(bodyJsonResponse).isEqualTo(inputJson);
        }


    }


    @Test
    public void testCreate() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO( 1L, "Endre", "06309876655", "5432. Nem kéne");

        String inputJson = this.mapToJson(customerDTO);
        String uri = "/api/customer";

        HttpEntity<CustomerDTO> entity = new HttpEntity<CustomerDTO>(customerDTO, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(formURL(uri), HttpMethod.POST,entity,String.class);

        String responseInJson = response.getBody();
        assertThat(responseInJson).isEqualTo(inputJson);
    }



    //@WithMockUser(value="eteo", password = "eteo")
    @Test
    public void testGetById() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO(1L, "Géza", "06309876655", "5432. Emőd");
        String inputJson = mapToJson(customerDTO);

        HttpEntity<CustomerDTO> entity = new HttpEntity<CustomerDTO>(customerDTO, headers);
        testRestTemplate.exchange(
                formURL("/api/customer"),
                HttpMethod.POST, entity , String.class);

        String uri = "/api/customer/1";

        String bodyJsonResponse = testRestTemplate.getForObject(formURL(uri), String.class);
        assertThat(bodyJsonResponse).isEqualTo(inputJson);
    }


    @Test
    public void testGetAll() throws Exception{
        List<CustomerDTO> customerDTOList = new ArrayList<>();

        CustomerDTO cus1 = new CustomerDTO(1L, "Géza", "06309876655", "5432. Emőd");
        CustomerDTO cus2 = new CustomerDTO(2L, "Elemér", "06111111111", "9999. Alma");
        customerDTOList.add(cus1);
        customerDTOList.add(cus2);

        String inputJson = this.mapToJson(customerDTOList);


        HttpEntity<List<CustomerDTO>> entity = new HttpEntity<>(customerDTOList, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(formURL("/api/customer"), HttpMethod.GET, entity, String.class);

        //String expected = mapToJson(customerDTO);
        String bodyJsonResponse = response.getBody();
        assertThat(bodyJsonResponse).isEqualTo(inputJson);
        //JSONAssert.assertEquals(expected, response.getBody(), false);
    }





    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


/*    private <T> T mapFromJson(String json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }*/


    private String formURL(String uri){
        return "http://localhost:" + port + uri;
    }
}
