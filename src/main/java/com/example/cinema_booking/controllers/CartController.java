package com.example.cinema_booking.controllers;

import com.example.cinema_booking.dto.CartDto;
import com.example.cinema_booking.models.Cart;
import com.example.cinema_booking.models.Ticket;
import com.example.cinema_booking.models.TicketType;
import com.example.cinema_booking.services.CartService;
import com.example.cinema_booking.services.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final ReservationService reservationService;

    private CartDto getSessionCart(HttpSession session) {
        return (CartDto) session.getAttribute("CART");
    }

    private void saveCart(HttpSession session, CartDto cart) {
        session.setAttribute("CART", cart);
    }

    @PostMapping("/add")
    public String addSeats(@RequestParam List<UUID> seatIds,
                           @RequestParam UUID showtimeId,
                           HttpSession session) {

        CartDto cart = cartService.getOrCreateCart(getSessionCart(session), showtimeId);
        cartService.addSeats(cart, seatIds);

        saveCart(session, cart);
        return "redirect:/cart/view";
    }

    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
        CartDto cart = getSessionCart(session);
        if (cart == null) cart = new CartDto();

        model.addAttribute("cart", cart);
        model.addAttribute("types", TicketType.values());
        model.addAttribute("pageTitle", "Koszyk");

        return "cart";
    }

    @PostMapping("/updateTicket")
    public String updateTicket(@RequestParam UUID seatId,
                               @RequestParam TicketType type,
                               HttpSession session) {

        CartDto cart = getSessionCart(session);
        if (cart != null) {
            cartService.updateTicketType(cart, seatId, type);
            saveCart(session, cart);
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/remove")
    public String removeSeat(@RequestParam UUID seatId, HttpSession session) {
        CartDto cart = getSessionCart(session);
        if (cart != null) {
            cartService.removeSeat(cart, seatId);
            saveCart(session, cart);
        }
        return "redirect:/cart/view";
    }

    @PostMapping("/confirm")
    public String confirm(HttpSession session) {

        CartDto cartDto = getSessionCart(session);
        if (cartDto == null || cartDto.getItems().isEmpty())
            return "redirect:/cart/view";

        Cart cart = new Cart();
        cart.setShowtimeId(cartDto.getShowtimeId());

        cartDto.getItems().forEach(item -> {
            Ticket ticket = new Ticket();
            ticket.setSeatId(item.getSeatId());
            ticket.setSeatLabel("Rząd " + item.getRowNum() + ", miejsce " + item.getSeatNum());
            ticket.setType(TicketType.valueOf(item.getTicketType()));
            ticket.setPrice(item.getPrice());
            cart.getTickets().add(ticket);
        });

        var reservation = reservationService.createFromCart(cart);

        session.removeAttribute("CART");

        return "redirect:/reservation/" + reservation.getId();
    }
}
