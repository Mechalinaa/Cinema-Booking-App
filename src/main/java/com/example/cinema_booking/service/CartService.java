package com.example.cinema_booking.service;

import com.example.cinema_booking.model.Cart;
import com.example.cinema_booking.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartService {

    public void addSeat(Cart cart, Long showId, int row, int seat) {
        CartItem item = new CartItem(showId, row, seat);
        cart.addItem(item);
    }

    public void removeSeat(Cart cart, Long showId, int row, int seat) {
        CartItem item = new CartItem(showId, row, seat);
        cart.removeItem(item);
    }

    public void clearCart(Cart cart) {
        cart.clear();
    }

    public Set<CartItem> getItems(Cart cart) {
        return cart.getItems();
    }
}
