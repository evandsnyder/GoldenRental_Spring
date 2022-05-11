package com.friendlygeek.goldenrental.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Reservation {
    // all the same info as a cart...
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private User user;
}
