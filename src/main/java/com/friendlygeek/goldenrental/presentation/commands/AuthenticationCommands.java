package com.friendlygeek.goldenrental.presentation.commands;

import com.friendlygeek.goldenrental.domain.model.User;
import com.friendlygeek.goldenrental.services.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.component.StringInput;
import org.springframework.shell.standard.*;

@ShellComponent
public class AuthenticationCommands extends AbstractShellComponent {
    private final AuthenticationService authService;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationCommands.class);

    @Autowired
    public AuthenticationCommands(AuthenticationService authenticationService) {
        authService = authenticationService;
    }

    @ShellMethod("Log in to your account")
    @ShellMethodAvailability("isNotLoggedIn")
    public void login() {
        //
    }

    @ShellMethod("Create a new account")
    @ShellMethodAvailability("isNotLoggedIn")
    public String register(
            @ShellOption(value={"-u", "--username"}, defaultValue = "") String username,
            @ShellOption(defaultValue = "") String email,
            @ShellOption(defaultValue = "") String password,
            @ShellOption(defaultValue = "") String confirmPassword,
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

        authService.register(new User(username, email, password));
        return "Registration successful!";
    }

    @ShellMethod(key = "component string", value = "String input", group = "Components")
    public String stringInput(boolean mask) {
        StringInput component = new StringInput(getTerminal(), "Enter value", "myvalue");
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        if (mask) {
            component.setMaskCharater('*');
        }
        StringInput.StringInputContext context = component.run(StringInput.StringInputContext.empty());
        return context.getInput();
        // return "Got value " + context.getResultValue();
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

    private String getInputManually(String fieldName) {
        return getInputManually(fieldName, false);
    }

    private String getInputManually(String fieldName, boolean hidden) {
        StringInput component = new StringInput(getTerminal(), fieldName, null);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        if (hidden) {
            component.setMaskCharater('*');
        }
        StringInput.StringInputContext context = component.run(StringInput.StringInputContext.empty());
        return context.getResultValue();
    }
}
