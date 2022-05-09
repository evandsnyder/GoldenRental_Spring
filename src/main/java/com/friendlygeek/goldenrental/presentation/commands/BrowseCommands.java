package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.services.AuthenticationService;
import com.friendlygeek.goldenrental.services.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class BrowseCommands {
    private final BrowserService browser;
    private final AuthenticationService authService;

    @Autowired
    public BrowseCommands(BrowserService browserService, AuthenticationService authenticationService){
        browser = browserService;
        authService = authenticationService;
    }

    @ShellMethod("Search for bikes available to rent")
    public void search(){
        browser.getAllBikes();
    }

    @ShellMethod("Add a given bike to your cart")
    @ShellMethodAvailability("isAuthenticated")
    public void addToCart(){

    }


    public Availability isAuthenticated(){
        return authService.isAuthenticated() ? Availability.available() : Availability.unavailable("You are not logged in");
    }
}
