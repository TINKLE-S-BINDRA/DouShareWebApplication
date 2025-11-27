package com.example.lendingplatform;

import com.example.lendingplatform.model.User;
import com.example.lendingplatform.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(properties = {
        "JWT_SECRET=test-secret"
})
class
UserControllerIntegration {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void resetDB() {
        userRepository.deleteAll();
    }


    @Test
    void testRegisterUser_EmailExists() throws Exception {
        // Insert a user first
        User existing = new User();
        existing.setEmail("exists@example.com");
        existing.setPassword("pass");
        userRepository.save(existing);

        mockMvc.perform(post("/api/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"exists@example.com\",\"password\":\"pass\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already exists"));
    }



    @Test
    void testLoginUser_InvalidPassword() throws Exception {
        User user = new User();
        user.setEmail("wrong@test.com");
        user.setPassword("correct");
        userRepository.save(user);

        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"wrong@test.com\",\"password\":\"wrong\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }


    @Test
    void testGetAllUsers() throws Exception {
        User u1 = new User();
        u1.setEmail("a@test.com");
        u1.setPassword("x");

        User u2 = new User();
        u2.setEmail("b@test.com");
        u2.setPassword("x");

        userRepository.save(u1);
        userRepository.save(u2);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
    /*@Test
    void testLoginUser_Success() throws Exception {
        // Save user directly to DB so login succeeds
        User user = new User();
        user.setEmail("login@test.com");
        user.setPassword("password");
        userRepository.save(user);


        mockMvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"login@test.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.startsWith("Bearer ")));
    }*/

}