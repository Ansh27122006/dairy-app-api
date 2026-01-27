package com.amardairy.service;

import com.amardairy.dto.OrderRequestDTO;
import com.amardairy.entity.Order;
import com.amardairy.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(OrderRequestDTO orderDto);
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order updateOrderStatus(Long id, OrderStatus status);
    void deleteOrder(Long id);
}

