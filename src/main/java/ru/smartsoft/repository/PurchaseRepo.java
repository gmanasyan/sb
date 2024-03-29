package ru.smartsoft.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;

/**
 * Репозиторий для покупок.
 */
public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

  @Query(name = Purchase.ALL_SORTED)
  List<Purchase> getAll();

  /**
   * Все товары за текущую неделю с понедельника до воскресенья.
   */
  @Query(nativeQuery = true, value = "SELECT * FROM purchases "
      + "WHERE EXTRACT(week FROM date) = EXTRACT(week FROM now())")
  List<Purchase> getAllLastWeek();


}
