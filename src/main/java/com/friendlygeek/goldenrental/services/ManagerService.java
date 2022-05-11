package com.friendlygeek.goldenrental.services;

import com.friendlygeek.goldenrental.domain.model.Bike;
import com.friendlygeek.goldenrental.services.repository.BikeRepository;
import com.friendlygeek.goldenrental.services.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
    private final UserRepository userRepository;
    private final BikeRepository bikeRepository;


    @Autowired
    public ManagerService(UserRepository uRepo, BikeRepository bRepo){
        userRepository = uRepo;
        bikeRepository = bRepo;
    }

    public void addNewBike(Bike bike){
        bikeRepository.save(bike);
    }
}
