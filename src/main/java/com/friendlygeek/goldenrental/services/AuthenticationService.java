package com.friendlygeek.goldenrental.services;

import com.friendlygeek.goldenrental.domain.model.User;
import com.friendlygeek.goldenrental.services.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IService{
    private boolean isAuthenticated = false;
    private final UserRepository userRepository;
    private User loggedInUser;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    public AuthenticationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean isAuthenticated(){
        return isAuthenticated;
    }

    public boolean authenticateUser(String username, String password){
        User user = userRepository.findByUsername(username);

        if(user.getPassword().equals(password)){
            isAuthenticated = true;
            loggedInUser = user;
        }

        return isAuthenticated;
    }

    public void logout() {
        isAuthenticated = false;
        loggedInUser = null;
    }

    public boolean register(User user){
        // if user is invalid return
        try {
            userRepository.save(user);
        } catch(IllegalArgumentException ex){
            logger.error("Failed to register new user");
            return false;
        }

        return true;
    }

    public User getLoggedInUser(){
        return isAuthenticated ? loggedInUser : null;
    }
}
