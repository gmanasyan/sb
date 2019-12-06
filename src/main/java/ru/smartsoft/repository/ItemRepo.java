package ru.smartsoft.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;

public interface ItemRepo extends JpaRepository<Item, Integer> {

  Item findByName(String name);

}
