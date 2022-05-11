package com.friendlygeek.goldenrental.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationCart {
    private LocalDate startDate;
    private LocalDate endDate;
    private User user;
    private ArrayList<Bike> bikes;

    public ReservationCart(){
        bikes = new ArrayList<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

}
