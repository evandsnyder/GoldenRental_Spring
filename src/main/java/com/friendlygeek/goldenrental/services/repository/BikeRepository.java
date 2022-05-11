package com.friendlygeek.goldenrental.services.repository;

import com.friendlygeek.goldenrental.domain.model.Bike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BikeRepository extends CrudRepository<Bike, Long> {

    @Query(value = "select * from bike WHERE id NOT IN" +
            "(select BR.bike_id from reservation R JOIN bike_reservation BR ON R.id " +
            "WHERE (R.start_date <= :startDate AND R.end_date >= :endDate) " +
            "OR (R.end_date < :endDate AND R.end_date >= :startDate) " +
            "OR (:startDate <= R.start_date AND :endDate >= R.start_date))", nativeQuery = true)
    List<Bike> findAllByDateRange(LocalDate startDate, LocalDate endDate);
}
