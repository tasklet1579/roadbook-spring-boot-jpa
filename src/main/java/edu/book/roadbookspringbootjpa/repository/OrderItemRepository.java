package edu.book.roadbookspringbootjpa.repository;

import edu.book.roadbookspringbootjpa.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
