package com.amardairy.serviceImpl;

import com.amardairy.dto.OrderRequestDTO;
import com.amardairy.entity.Order;
import com.amardairy.repository.OrderRepository;
import com.amardairy.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                    .items(jsonItems)  // Save JSON
                    .total(orderDto.getTotal())
                    .createdAt(LocalDateTime.now())
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
}
