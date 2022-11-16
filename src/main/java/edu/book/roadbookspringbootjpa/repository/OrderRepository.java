package edu.book.roadbookspringbootjpa.repository;

import edu.book.roadbookspringbootjpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
