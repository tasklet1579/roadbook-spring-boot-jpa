package edu.book.roadbookspringbootjpa.repository;

import edu.book.roadbookspringbootjpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}