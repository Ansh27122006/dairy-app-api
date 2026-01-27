package com.amardairy.repository;

import com.amardairy.entity.Order;
import com.amardairy.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPhone(String phone);

    List<Order> findAllByOrderByCreatedAtDesc();

    Long countByStatus(OrderStatus status);

    @Query("SELECT SUM(o.total) FROM Order o")
    Double sumTotalRevenue();

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.createdAt BETWEEN :start AND :end")
    Double sumRevenueByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    Long countByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
