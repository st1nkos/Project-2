package ru.st1nkos.springcourse.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.st1nkos.springcourse.Project2Boot.models.Users;
import ru.st1nkos.springcourse.Project2Boot.services.UsersDetailsService;

@Component
public class UsersValidator implements Validator {

    private final UsersDetailsService usersDetailsService;

    @Autowired
    public UsersValidator(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users users = (Users) target;

        if(usersDetailsService.getPersonByUserName(users.getUsername()).isPresent()){
            errors.rejectValue("username","","Пользователь с таким именем пользователя уже зарегестрирован!");
        }
    }
}
