package com.example.demo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"age\": 30}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void shouldGetUsers() throws Exception {
        // Perform the GET request to /api/users
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Extract the response content as a String
        String content = result.getResponse().getContentAsString();

        // Use ObjectMapper to convert JSON string to List<User>
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(content, new TypeReference<List<User>>() {});

        // Verify that the response is a list and each element has the expected structure
        assertNotNull(users);
    }
}
