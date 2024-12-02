package com.onebox.oneboxchallenge.product.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openapitools.model.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIT {

    private static final String PRODUCT_PATH = "/products";
    private static final Long VALID_ID_FOR_GET = 1L;
    private static final Long VALID_ID_FOR_PUT = 2L;
    private static final Long VALID_ID_FOR_DEL = 3L;
    private static final String VALID_DESCRIPTION = "description_test";
    private static final Long VALID_AMOUNT = 250L;
    public static final String UPDATED_DESCRIPTION = "Updated Description";
    public static final String INVALID_ID = "9999";
    public static final long INVALID_AMOUNT = -10L;
    public static final String INVALID_DESCRIPTION = "";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnProductDetails_WhenExists() throws Exception {
        MvcResult result =
                mockMvc.perform(get(PRODUCT_PATH + "/" + VALID_ID_FOR_GET)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ProductDTO response = objectMapper.readValue(jsonResponse, ProductDTO.class);

        assertNotNull(response);

        assertEquals(VALID_ID_FOR_GET, response.getId());
        assertEquals(VALID_DESCRIPTION, response.getDescription());
        assertEquals(VALID_AMOUNT, response.getAmount());
    }

    @Test
    void shouldReturnAllProducts() throws Exception {
        MvcResult result = mockMvc.perform(get(PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ProductDTO[] products = objectMapper.readValue(jsonResponse, ProductDTO[].class);

        assertTrue(products.length > 0);
        assertEquals(VALID_ID_FOR_GET, products[0].getId());
    }

    @Test
    void shouldCreateProductSuccessfully() throws Exception {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription(VALID_DESCRIPTION);
        productDTO.setAmount(VALID_AMOUNT);

        String jsonRequest = objectMapper.writeValueAsString(productDTO);

        MvcResult result = mockMvc.perform(post(PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ProductDTO response = objectMapper.readValue(jsonResponse, ProductDTO.class);

        assertNotNull(response.getId());
        assertEquals(VALID_DESCRIPTION, response.getDescription());
        assertEquals(VALID_AMOUNT, response.getAmount());
    }

    @Test
    @Disabled
    void shouldUpdateProductSuccessfully() throws Exception {
        ProductDTO updatedProduct = new ProductDTO();
        updatedProduct.setDescription(UPDATED_DESCRIPTION);
        updatedProduct.setAmount(VALID_AMOUNT);

        String jsonRequest = objectMapper.writeValueAsString(updatedProduct);

        MvcResult result = mockMvc.perform(put(PRODUCT_PATH + "/" + VALID_ID_FOR_PUT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        ProductDTO response = objectMapper.readValue(jsonResponse, ProductDTO.class);

        assertEquals(VALID_ID_FOR_PUT, response.getId());
        assertEquals(UPDATED_DESCRIPTION, response.getDescription());
        assertEquals(VALID_AMOUNT, response.getAmount());
    }

    @Test
    void shouldDeleteProductSuccessfully() throws Exception {
        mockMvc.perform(delete(PRODUCT_PATH + "/" + VALID_ID_FOR_DEL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturnNotFound_WhenProductDoesNotExist() throws Exception {
        mockMvc.perform(get(PRODUCT_PATH + "/" + INVALID_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBadRequest_WhenCreatingProductWithInvalidData() throws Exception {
        ProductDTO invalidProduct = new ProductDTO();
        invalidProduct.setDescription(INVALID_DESCRIPTION);
        invalidProduct.setAmount(INVALID_AMOUNT);

        String jsonRequest = objectMapper.writeValueAsString(invalidProduct);

        mockMvc.perform(post(PRODUCT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
