package com.example.cinema_booking.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CartItemDtoTest {

    @Test
    void shouldCreateCartItemDtoUsingConstructor() {
        UUID seatId = UUID.randomUUID();
        BigDecimal price = new BigDecimal("25.00");

        CartItemDto dto = new CartItemDto(
                seatId,
                3,
                7,
                "NORMAL",
                price
        );

        assertThat(dto.getSeatId(), equalTo(seatId));
        assertThat(dto.getRowNum(), equalTo(3));
        assertThat(dto.getSeatNum(), equalTo(7));
        assertThat(dto.getTicketType(), equalTo("NORMAL"));
        assertThat(dto.getPrice(), equalTo(price));
    }
}
