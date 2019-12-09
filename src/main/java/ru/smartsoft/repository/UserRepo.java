package ru.smartsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.name = ?1 and u.lastName = ?2 and u.age = ?3")
  User findByAllFields(String  name, String lastName, Integer age);

  @Query(nativeQuery = true, value = "SELECT * FROM users WHERE id = ("
      + "    SELECT user_id FROM purchases WHERE EXTRACT(month FROM date) > EXTRACT(month FROM now()) - 6"
      + "    group by user_id ORDER BY SUM(amount) DESC limit 1 )")
  User getBestsellerOfHalthYear();


}
