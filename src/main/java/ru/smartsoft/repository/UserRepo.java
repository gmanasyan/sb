package ru.smartsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartsoft.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
