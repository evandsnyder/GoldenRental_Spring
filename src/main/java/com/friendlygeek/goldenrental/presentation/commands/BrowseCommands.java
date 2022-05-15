package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.domain.model.Bike;
import com.friendlygeek.goldenrental.services.AuthenticationService;
import com.friendlygeek.goldenrental.services.BrowserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDate;
import java.util.ArrayList;

@ShellComponent
public class BrowseCommands extends GoldenShellComponent {
    private final BrowserService browser;
    private final AuthenticationService authService;

    @Autowired
    public BrowseCommands(BrowserService browserService, AuthenticationService authenticationService) {
        browser = browserService;
        authService = authenticationService;
    }

    @ShellMethod("Search for bikes available to rent")
    public String search(
            @ShellOption(defaultValue = "") String startDate,
            @ShellOption(defaultValue = "") String duration,
            @ShellOption(defaultValue = "") String manufacturer,
            @ShellOption(defaultValue = "") String type
    )
    {
        if(startDate.equals("")){
            startDate = getInputManually("Please enter a start date (YYYY-mm-dd): ");
        }

        if(duration.equals("")){
            duration = getInputManually("How long would you like to rent to rent these bikes? (in days): ");
        }

        // convert startDate to actual date object..
        // convert duration to int and then end date...
        int iDuration = Integer.parseInt(duration);
        LocalDate _startDate = LocalDate.parse(startDate);
        LocalDate _endDate = _startDate.plusDays(iDuration);

        // Need to build some kinda  query...
        ArrayList<Bike> availableBikes = new ArrayList<>(browser.getBikes(_startDate, _endDate, manufacturer, type));

        // Need to do some fancy string building..
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Bikes available from %s to %s:%n%n", _startDate.toString(), _endDate.toString()));
        builder.append(String.format("Id\tManufacturer\tType\tRate per Day%n--------------------------------------------------------%n"));

        for(Bike bike : availableBikes){
            builder.append(String.format("%s%n", bike.toString()));
        }
        builder.append("\n");

        return builder.toString();
    }

    public Availability isAuthenticated() {
        return authService.isAuthenticated() ? Availability.available() : Availability.unavailable("You are not logged in");
    }
}
