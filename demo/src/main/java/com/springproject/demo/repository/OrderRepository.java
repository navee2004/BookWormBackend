package com.springproject.demo.repository;

import com.springproject.demo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Custom query to find orders by user ID
    @Query("SELECT o FROM Order o WHERE o.user.id = ?1")
    List<Order> findByUserId(Long userId);
}
