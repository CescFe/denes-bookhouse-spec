package com.onebox.oneboxchallenge.cart.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openapitools.model.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

    public static final String CARTS_PATH = "/carts";
    public static final long VALID_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Sql(scripts = {"/schema.sql", "/data.sql"})
    @Test
    void shouldCreateCartSuccessfully() throws Exception {
        String requestBody = """
                    {
                        "products": [
                            {"id": 1, "description": "Product A", "amount": 10},
                            {"id": 2, "description": "Product B", "amount": 5}
                        ]
                    }
                """;

        mockMvc.perform(post("/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.products", hasSize(2)));
    }

    @Sql(scripts = {"/schema.sql", "/data.sql"})
    @Test
    void shouldGetCartSuccessfully() throws Exception {
        Long cartId = 1L;

        MvcResult result =
                mockMvc.perform(get(CARTS_PATH + "/{id}", cartId)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        CartDTO response = objectMapper.readValue(jsonResponse, CartDTO.class);

        assertNotNull(response);

        assertEquals(cartId, response.getId());
        assertNotNull(response.getProducts());
        assertEquals(2, response.getProducts().size());
    }

    @Test
    void shouldDeleteCartSuccessfully() throws Exception {
        Long cartId = 1L;

        mockMvc.perform(delete(CARTS_PATH + "/{id}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFound_WhenCartDoesNotExist() throws Exception {
        Long nonExistentCartId = 99L;

        mockMvc.perform(get(CARTS_PATH + "/{id}", nonExistentCartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
