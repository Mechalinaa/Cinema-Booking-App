package com.example.cinema_booking.controllers;

import com.example.cinema_booking.dto.CartDto;
import com.example.cinema_booking.dto.CartItemDto;
import com.example.cinema_booking.models.Seat;
import com.example.cinema_booking.models.Ticket;
import com.example.cinema_booking.models.TicketType;
import com.example.cinema_booking.services.ReservationService;
import com.example.cinema_booking.services.SeatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final SeatService seatService;
    private final ReservationService reservationService;

    @PostMapping("/add")
    public String addToCart(@RequestParam List<UUID> seatIds,
                            @RequestParam UUID showtimeId,
                            HttpSession session) {

        CartDto cart = (CartDto) session.getAttribute("CART");
        if (cart == null) {
            cart = new CartDto();
            cart.setShowtimeId(showtimeId);
        }

        for (UUID seatId : seatIds) {
            boolean exists = cart.getItems().stream()
                    .anyMatch(i -> i.getSeatId().equals(seatId));

            if (!exists) {
                Seat seat = seatService.findById(seatId);

                BigDecimal price = BigDecimal.valueOf(25); // domyślna cena NORMALNY

                cart.getItems().add(new CartItemDto(
                        seat.getId(),
                        seat.getRowNum(),
                        seat.getSeatNum(),
                        "NORMALNY",
                        price
                ));
            }
        }

        session.setAttribute("CART", cart);
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        CartDto cart = (CartDto) session.getAttribute("CART");
        if (cart == null) cart = new CartDto();

        model.addAttribute("cart", cart);
        model.addAttribute("types", List.of("NORMALNY", "ULGOWY", "RODZINNY"));
        model.addAttribute("pageTitle", "Koszyk");
        return "cart";
    }

    @PostMapping("/updateTicket")
    public String updateTicket(@RequestParam UUID seatId,
                               @RequestParam String ticketType,
                               HttpSession session) {

        CartDto cart = (CartDto) session.getAttribute("CART");
        if (cart != null) {
            for (CartItemDto item : cart.getItems()) {
                if (item.getSeatId().equals(seatId)) {
                    item.setTicketType(ticketType);

                    BigDecimal price = switch (ticketType) {
                        case "ULGOWY" -> BigDecimal.valueOf(18);
                        case "RODZINNY" -> BigDecimal.valueOf(15);
                        default -> BigDecimal.valueOf(25);
                    };
                    item.setPrice(price);
                    break;
                }
            }
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam UUID seatId, HttpSession session) {
        CartDto cart = (CartDto) session.getAttribute("CART");
        if (cart != null) {
            cart.getItems().removeIf(i -> i.getSeatId().equals(seatId));
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/confirm")
    public String confirmReservation(HttpSession session) {
        CartDto cart = (CartDto) session.getAttribute("CART");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart/view";
        }

        com.example.cinema_booking.models.Cart serviceCart = new com.example.cinema_booking.models.Cart();
        serviceCart.setShowtimeId(cart.getShowtimeId());

        for (CartItemDto item : cart.getItems()) {
            Ticket ticket = new Ticket();
            ticket.setSeatId(item.getSeatId());
            ticket.setSeatLabel("Rząd " + item.getRowNum() + ", Miejsce " + item.getSeatNum());
            ticket.setType(TicketType.valueOf(item.getTicketType()));
            ticket.setPrice(item.getPrice());
            serviceCart.getTickets().add(ticket);
        }

        var reservation = reservationService.createFromCart(serviceCart);

        session.removeAttribute("CART");

        return "redirect:/reservation/" + reservation.getId();
    }
}
