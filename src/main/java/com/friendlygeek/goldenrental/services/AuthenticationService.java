package com.friendlygeek.goldenrental.services;

import com.friendlygeek.goldenrental.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IService{

    public boolean isAuthenticated(){
        return false;
    }

    public boolean authenticateUser(String username, String password){
        return false;
    }

    public void logout() {}

    public void register(User user){}
}
