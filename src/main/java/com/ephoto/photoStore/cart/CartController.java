package com.ephoto.photoStore.cart;

import com.ephoto.photoStore.photo.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final Cart cart;

    CartController(Cart cart) {

        this.cart = cart;
    }

    @PostMapping
    String addToCart(@RequestParam String sku) {
        this.cart.add(sku);
        return "redirect:/";
    }

    @GetMapping
    ModelAndView showCart() {
        return new ModelAndView("cart", Map.of("cartTotal", cart.getTotalItems(), "items", cart.getItems()));
    }

    @PostMapping("/delete")
    String removeFromCart(@RequestParam String sku) {
        this.cart.remove(sku);
        return "redirect:/cart";
    }
}
