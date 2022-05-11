package com.friendlygeek.goldenrental.services;

import com.friendlygeek.goldenrental.domain.model.Bike;
import com.friendlygeek.goldenrental.services.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BrowserService {
    private final BikeRepository bikes;

    @Autowired
    public BrowserService(BikeRepository repository){
        bikes = repository;
    }

    public List<Bike> getBikes(LocalDate startDate, LocalDate endDate, String manufacturer, String type){
        return bikes.findAllByDateRange(startDate, endDate);
    }
}
