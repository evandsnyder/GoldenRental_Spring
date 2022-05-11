package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.domain.model.Bike;
import com.friendlygeek.goldenrental.domain.model.ReservationCart;
import com.friendlygeek.goldenrental.services.ReservationCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.format.DateTimeFormatter;

@ShellComponent
public class CartCommands extends GoldenShellComponent {
    private final ReservationCartService cartService;

    @Autowired
    public CartCommands(ReservationCartService service) {
        cartService = service;
    }

    @ShellMethod("View the contents of your cart")
    public String viewCart() {
        ReservationCart cart = cartService.getCart();
        StringBuilder builder = new StringBuilder();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        builder.append(String.format("You have %d item%s in your cart%n", cart.getBikes().size(), cart.getBikes().size() == 1 ? "s" : ""));
        builder.append(String.format("Your reservation is from %s to %s%n", dateTimeFormatter.format(cart.getStartDate()), dateTimeFormatter.format(cart.getEndDate())));

        builder.append(String.format("Id\tManufacturer\tType\tRate per Day%n--------------------------------------------------------%n"));

        for(Bike bike : cart.getBikes()){
            builder.append(String.format("%s%n", bike.toString()));
        }
        builder.append("\n");

        return builder.toString();
    }

    @ShellMethod("Start the checkout process")
    public String checkout() {
        return "";
    }

    @ShellMethod("Add a bike to your cart")
    public String addToCart(long id) {
        if (cartService.isInCart(id))
            return "Bike is already in your cart";
        cartService.addToCart(id);
        return "Bike added to cart";
    }

    @ShellMethod("Remove a bike from your cart")
    public String removeFromCart(long id) {
        if(cartService.isInCart(id)){
            cartService.removeFromCart(id);
        }
        return "Bike removed from your cart";
    }

    @ShellMethod("Empty out your cart")
    public String clearCart() {
        cartService.resetCart();
        return "Your cart has been emptied";
    }
}
