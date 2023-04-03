package ru.st1nkos.springcourse.Project2Boot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.st1nkos.springcourse.Project2Boot.models.Person;
import ru.st1nkos.springcourse.Project2Boot.services.PeopleService;


@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;


    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;


        if (peopleService.getPersonByFullName(person.getFullName()).isPresent()) {
            errors.rejectValue("fullName", "", "Данный человек уже заведен в базу");
        }


        if (person.getBornYear()<=1900){
            errors.rejectValue("bornYear","", "Год рождения должен быть больше 1900");
        }
    }
}
