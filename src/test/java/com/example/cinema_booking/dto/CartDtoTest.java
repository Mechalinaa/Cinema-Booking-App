package com.example.cinema_booking.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CartDtoTest {

    @Test
    void shouldCalculateTotalPriceCorrectly() {
        CartItemDto item1 = new CartItemDto(
                UUID.randomUUID(), 1, 1, "NORMALNY", new BigDecimal("25")
        );
        CartItemDto item2 = new CartItemDto(
                UUID.randomUUID(), 1, 2, "RODZINNY", new BigDecimal("15")
        );

        CartDto cart = new CartDto();
        cart.setShowtimeId(UUID.randomUUID());
        cart.setItems(List.of(item1, item2));

        BigDecimal totalPrice = cart.getTotalPrice();

        assertThat(totalPrice, equalTo(new BigDecimal("40")));
    }

    @Test
    void shouldReturnZeroWhenCartIsEmpty() {
        CartDto cart = new CartDto();

        BigDecimal totalPrice = cart.getTotalPrice();

        assertThat(totalPrice, equalTo(BigDecimal.ZERO));
    }
}
