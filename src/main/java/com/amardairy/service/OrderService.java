package com.amardairy.service;

import com.amardairy.dto.OrderRequestDTO;
import com.amardairy.entity.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(OrderRequestDTO orderDto);
    List<Order> getAllOrders();
}

