package com.example.cinema_booking.bin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
@RequiredArgsConstructor
public class CartController {

/*    @ModelAttribute("cart")
    public Cart cart() {
        return new Cart();
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Cart> addToCart(@RequestBody SeatSelectionDto dto, @ModelAttribute("cart") Cart cart) {
        CartItem item = new CartItem(dto.getShowtimeId(), dto.getRow(), dto.getSeat());
        cart.addItem(item);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity<Cart> deleteFromCart(@RequestBody SeatSelectionDto dto, @ModelAttribute("cart") Cart cart) {
        CartItem item = new CartItem(dto.getShowtimeId(), dto.getRow(), dto.getSeat());
        cart.removeItem(item);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/clear")
    @ResponseBody
    public ResponseEntity<Cart> clearCart(@ModelAttribute("cart") Cart cart) {
        cart.clear();
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Cart> getCart(@ModelAttribute("cart") Cart cart) {
        return ResponseEntity.ok(cart);
    }*/
}
