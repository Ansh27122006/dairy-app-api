package com.amardairy.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private String customerName;
    private String phone;
    private String address;

    private List<OrderItemDTO> items;
    private Double total;
}
