package ru.st1nkos.springcourse.Project2Boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.st1nkos.springcourse.Project2Boot.models.Person;
import ru.st1nkos.springcourse.Project2Boot.models.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
}
