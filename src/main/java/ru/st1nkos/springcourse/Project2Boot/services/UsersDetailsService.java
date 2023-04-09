package ru.st1nkos.springcourse.Project2Boot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.st1nkos.springcourse.Project2Boot.models.Users;
import ru.st1nkos.springcourse.Project2Boot.repositories.UsersRepository;
import ru.st1nkos.springcourse.Project2Boot.security.UsersDetails;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService( UsersRepository usersRepository) {
        this.usersRepository = usersRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      Optional<Users> users = usersRepository.findByUsername(s);
      if (users.isEmpty()){
          throw new UsernameNotFoundException("User not found!");
      }else {
          return new UsersDetails(users.get());
      }
    }

    public Optional<Users> getPersonByUserName(String username){
        return usersRepository.findByUsername(username);
    }
}
