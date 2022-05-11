package com.friendlygeek.goldenrental.services.repository;

import com.friendlygeek.goldenrental.domain.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
