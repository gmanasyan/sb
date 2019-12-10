package ru.smartsoft.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;

/**
 * Репозиторий для товара.
 */
public interface ItemRepo extends JpaRepository<Item, Integer> {

  Item findByName(String name);

  /**
   * Самый покупаемый товар за текущий месяц.
   */
  @Query(nativeQuery = true, value = "SELECT * FROM items WHERE id = ("
      + "SELECT item_id FROM purchases WHERE EXTRACT(month FROM date) = EXTRACT(month FROM now())"
      + "group by item_id ORDER BY SUM(count) DESC limit 1 )")
  Item getBestsellerOfMonth();

  /**
   * Самый покупаемый товар за людей с заданным возрастом.
   */
  @Query(nativeQuery = true, value = "SELECT * FROM items WHERE id = ("
      + "SELECT item_id FROM purchases p JOIN users u on p.user_id = u.id WHERE u.age = :age"
      + " group by item_id ORDER BY SUM(count) DESC LIMIT 1)")
  Item getBestsellerOfAge(@Param("age") Integer age);

}
