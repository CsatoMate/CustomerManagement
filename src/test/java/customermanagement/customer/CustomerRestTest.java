package customermanagement.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import customermanagement.customer.dto.CustomerDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


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

    @Test
    public void testCreate() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO( 99L, "Endre", "06309876655", "5432. Nem kéne");

        String inputJson = this.mapToJson(customerDTO);
        String uri = "/api/customer";

        HttpEntity<CustomerDTO> entity = new HttpEntity<CustomerDTO>(customerDTO, headers);
        ResponseEntity<String> response = testRestTemplate.exchange(formURL(uri), HttpMethod.POST,entity,String.class);

        String responseInJson = response.getBody();
        assertThat(responseInJson).isEqualTo(inputJson);
    }



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
