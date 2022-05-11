package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.domain.model.Bike;
import com.friendlygeek.goldenrental.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ManagerCommands extends GoldenShellComponent{
    private final ManagerService manager;

    @Autowired
    ManagerCommands(ManagerService managerService){
        manager = managerService;
    }

    @ShellMethod("Add a new bike to the database")
    public String addBike(
            @ShellOption(value = {"-m", "--manufacturer"}) String manufacturer,
            @ShellOption(value = {"-t", "--type"}) String type,
            @ShellOption(value = {"-r", "--rate"}) Float rentalRate
    ){
        manager.addNewBike(new Bike(manufacturer, type, rentalRate));
        return "Added new bike";
    }
}
