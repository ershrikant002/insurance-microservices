package com.app.service;

import com.app.entity.Address;
import com.app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc   // enables MockMvc injection
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // for JSON serialization

    @Test
    void testCreateAndGetUser() throws Exception {
        // Create request JSON
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setMobile("9876543210");

        Address address = new Address();
        address.setState("221B Baker Street");
        //address.setCity("London");
        address.setState("England");
        address.setZip("NW16XE");
        address.setCity("UK");
        user.setAddress(address);

        // POST /users
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
               .andExpect(status().is(201))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.address.city").value("UK"));

        // GET /users/{email}
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.address.city").value("UK"));
    }

    @Test
    void testGetUser_NotFound() throws Exception {
        mockMvc.perform(get("/users/unknown@example.com"))
                .andExpect(status().is4xxClientError()); // RuntimeException -> 500 or 404 depending on handler
    }
}

