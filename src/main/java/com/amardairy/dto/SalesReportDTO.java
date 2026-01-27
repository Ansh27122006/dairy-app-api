package com.amardairy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesReportDTO {
    private String period;
    private Double totalSales;
    private Long totalOrders;
    private Map<String, Integer> popularProducts; // productName -> quantity sold
}