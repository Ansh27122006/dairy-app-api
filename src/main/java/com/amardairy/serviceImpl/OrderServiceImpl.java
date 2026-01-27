package com.amardairy.serviceImpl;

import com.amardairy.dto.OrderRequestDTO;
import com.amardairy.entity.Order;
import com.amardairy.entity.OrderStatus;
import com.amardairy.repository.OrderRepository;
import com.amardairy.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Order placeOrder(OrderRequestDTO orderDto) {
        try {
            String jsonItems = objectMapper.writeValueAsString(orderDto.getItems());

            Order order = Order.builder()
                    .customerName(orderDto.getCustomerName())
                    .phone(orderDto.getPhone())
                    .address(orderDto.getAddress())
                    .items(jsonItems)
                    .total(orderDto.getTotal())
                    .status(OrderStatus.PENDING)  // ADD THIS
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())  // ADD THIS
                    .build();

            return orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException("Error saving order");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
