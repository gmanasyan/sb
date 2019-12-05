package ru.smartsoft.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.smartsoft.model.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer> {

  @Query(name = Purchase.ALL_SORTED)
  List<Purchase> getAll();

}
