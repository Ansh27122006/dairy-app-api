package com.amardairy.converter;

import com.amardairy.entity.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.name().toUpperCase();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return OrderStatus.valueOf(value.toUpperCase());
    }
}