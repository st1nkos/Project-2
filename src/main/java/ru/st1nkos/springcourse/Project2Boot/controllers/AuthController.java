package ru.st1nkos.springcourse.Project2Boot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.st1nkos.springcourse.Project2Boot.models.Users;
import ru.st1nkos.springcourse.Project2Boot.services.RegistrationService;
import ru.st1nkos.springcourse.Project2Boot.util.UsersValidator;



@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UsersValidator usersValidator;

    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UsersValidator usersValidator, RegistrationService registrationService) {
        this.usersValidator = usersValidator;
        this.registrationService = registrationService;
    }


    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String loginPage(@ModelAttribute("user") Users users) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid Users users, BindingResult bindingResult) {
        usersValidator.validate(users, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        registrationService.register(users);

        return "redirect:/auth/login";
    }
}
