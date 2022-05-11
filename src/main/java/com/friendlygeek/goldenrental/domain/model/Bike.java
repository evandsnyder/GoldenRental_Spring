package com.friendlygeek.goldenrental.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private Float rentalRate;
    private String manufacturer;
    private String type;

    @OneToMany
    private List<Reservation> reservation;

    public Bike(String manufacturer, String type, Float rentalRate) {
        this.rentalRate = rentalRate;
        this.manufacturer = manufacturer;
        this.type = type;
    }

    public Bike() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return String.format("%d.\t%s\t%s\t%.2f", id, manufacturer, type, rentalRate);
    }
}
