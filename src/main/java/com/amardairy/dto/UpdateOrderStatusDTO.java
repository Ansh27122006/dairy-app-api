package com.amardairy.dto;

import com.amardairy.entity.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusDTO {
    private OrderStatus status;
}