package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.domain.model.User;
import com.friendlygeek.goldenrental.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AuthenticationCommands extends GoldenShellComponent {
    private final AuthenticationService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationCommands.class);

    @Autowired
    public AuthenticationCommands(AuthenticationService authenticationService) {
        authService = authenticationService;
    }

    @ShellMethod("Log in to your account")
    @ShellMethodAvailability("isNotLoggedIn")
    public String login(
            @ShellOption(value = {"-u", "--username"}, defaultValue = "") String username,
            @ShellOption(value = {"-p", "--password"}, defaultValue = "") String password
    ) {
        if (username.equals("")) {
            username = getInputManually("username: ");
        }

        if (password.equals("")) {
            password = getInputManually("password: ", true);
        }

        return authService.authenticateUser(username, password)
                ? String.format("Log in successful%nWelcome, %s", authService.getLoggedInUser().getUsername())
                : "Incorrect username or password";
    }

    @ShellMethod("Create a new account")
    @ShellMethodAvailability("isNotLoggedIn")
    public String register(
            @ShellOption(value = {"-u", "--username"}, defaultValue = "") String username,
            @ShellOption(value = {"-e", "--email"}, defaultValue = "") String email,
            @ShellOption(value = {"-p", "--password"}, defaultValue = "") String password,
            @ShellOption(value = {"-c", "--confirm-password"}, defaultValue = "") String confirmPassword,
            @ShellOption(defaultValue = "false") boolean noConfirm
    ) {

        if (username.equals("")) {
            username = getInputManually("username: ");
        }

        if (email.equals("")) {
            email = getInputManually("email: ");

        }

        if (password.equals("")) {
            password = getInputManually("password: ", true);
        }

        if (confirmPassword.equals("") && !noConfirm) {
            confirmPassword = getInputManually("confirm password: ", true);
        }

        if (!noConfirm && !password.equals(confirmPassword)) {
            // ERROR!
            System.err.println("Failed to validate password...");
            return "Failed to register: Passwords did not match";
        }

        return authService.register(new User(username, email, password)) ? "Registration successful!" : "Failed to register your user";
    }

    @ShellMethod("Log out of your account")
    @ShellMethodAvailability("isLoggedIn")
    public void logout() {
        authService.logout();
    }

    public Availability isLoggedIn() {
        return authService.isAuthenticated() ? Availability.available() : Availability.unavailable("You are not logged in");
    }

    public Availability isNotLoggedIn() {
        return !authService.isAuthenticated() ? Availability.available() : Availability.unavailable("You are already logged in");
    }


}
