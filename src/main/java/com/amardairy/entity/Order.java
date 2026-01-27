package com.amardairy.entity;

import com.amardairy.converter.OrderStatusConverter;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String phone;
    private String address;

    @Column(columnDefinition = "TEXT")
    private String items;    // Store RAW JSON String

    private Double total;

    @Column(nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status = OrderStatus.PENDING;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
