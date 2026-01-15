package com.example.cinema_booking.model;

import java.util.HashSet;
import java.util.Set;

public class Cart {
    private final Set<CartItem> items = new HashSet<>();

    public void addItem(CartItem item){
        items.add(item);
    }

    public void removeItem(CartItem item){
        items.remove(item);
    }

    public Set<CartItem> getItems(){
        return items;
    }

    public void clear(){
        items.clear();
    }
}
