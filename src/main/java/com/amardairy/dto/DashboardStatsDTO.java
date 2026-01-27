package com.amardairy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDTO {
    private Long totalOrders;
    private Long pendingOrders;
    private Long deliveredOrders;
    private Long totalCustomers;
    private Double totalRevenue;
    private Double todayRevenue;
    private Double weekRevenue;
    private Double monthRevenue;
}