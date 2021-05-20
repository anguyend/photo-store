package com.ephoto.photoStore.cart;

import com.ephoto.photoStore.client.BrowserClient;
import com.ephoto.photoStore.photo.Cart;
import com.ephoto.photoStore.photo.PhotoItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class)
class CartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Cart cart;

    @Test
    void addsItemsToCart() throws Exception {
        String expectedSku = "d1";

        mockMvc.perform(MockMvcRequestBuilders.post("/cart").param("sku", expectedSku))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/"));

        verify(cart).add(expectedSku);
    }

    @Test
    void removeItemsFromCart() throws Exception {
        String expectedSku = "rv";

        mockMvc.perform(MockMvcRequestBuilders.post("/cart/delete").param("sku", expectedSku))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "/cart"));

        verify(cart).remove(expectedSku);
    }

    @Test
    void showCart() {
        CartItem cartItem1 = new CartItem(new PhotoItem("test1", "Test 1", BigDecimal.valueOf(3)), 2);
        CartItem cartItem2 = new CartItem(new PhotoItem("test2", "Test 2", BigDecimal.valueOf(5)), 1);
        when(cart.getItems()).thenReturn(Arrays.asList(cartItem1, cartItem2));

        BrowserClient client = new BrowserClient(mockMvc);
        client.goToCart();

        assertThat(client.getCartItemQtyLabel("Test 1")).isEqualTo("2");
        assertThat(client.getCartItemQtyLabel("Test 2")).isEqualTo("1");
    }

}