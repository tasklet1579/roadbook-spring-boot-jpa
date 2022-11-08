package edu.book.roadbookspringbootjpa.repository;

import edu.book.roadbookspringbootjpa.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
