package ru.smartsoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.User;

/**
 * Репозиторий для пользователей.
 */
public interface UserRepo extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.name = ?1 and u.lastName = ?2 and u.age = ?3")
  User findByAllFields(String  name, String lastName, Integer age);

  /**
   * Человек купивший больше всего покупок по кличеству товара (не по стоимости!) .
   */
  @Query(nativeQuery = true, value = "SELECT * FROM users WHERE id = ("
      + "    SELECT user_id FROM purchases WHERE EXTRACT(month FROM date) >= EXTRACT(month FROM now()) - 6"
      + "    group by user_id ORDER BY SUM(count) DESC limit 1 )")
  User getBestsellerOfHalthYear();


}
