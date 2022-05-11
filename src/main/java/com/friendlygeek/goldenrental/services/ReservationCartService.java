package com.friendlygeek.goldenrental.services;

import com.friendlygeek.goldenrental.domain.model.ReservationCart;
import com.friendlygeek.goldenrental.services.repository.BikeRepository;
import com.friendlygeek.goldenrental.services.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationCartService implements IService {
    private ReservationCart cart;
    private final AuthenticationService authenticationService;
    private final BikeRepository bikeRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationCartService(AuthenticationService authService, BikeRepository bikeRepo, ReservationRepository reservationRepo){
        authenticationService= authService;
        bikeRepository = bikeRepo;
        reservationRepository = reservationRepo;
        resetCart();
    }

    public void checkout(){
        // Convert cart to reservation and submit...
    }

    public ReservationCart getCart(){
        return cart;
    }

    public void addToCart(long id){
        bikeRepository.findById(id).ifPresent(cart.getBikes()::add);
    }

    public void removeFromCart(long id){
        cart.getBikes().removeIf(b -> b.getId() == id);
    }

    public void resetCart() {
        cart = new ReservationCart();
        cart.setUser(authenticationService.getLoggedInUser());
    }

    public boolean isInCart(long id){
        return cart.getBikes().stream().anyMatch(b -> b.getId() == id);
    }
}
