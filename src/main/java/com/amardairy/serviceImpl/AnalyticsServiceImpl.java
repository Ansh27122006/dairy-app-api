package com.amardairy.serviceImpl;

import com.amardairy.dto.DashboardStatsDTO;
import com.amardairy.dto.SalesReportDTO;
import com.amardairy.entity.OrderStatus;
import com.amardairy.repository.OrderRepository;
import com.amardairy.repository.UserRepository;
import com.amardairy.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public DashboardStatsDTO getDashboardStats() {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime weekAgo = today.minusWeeks(1);
        LocalDateTime monthAgo = today.minusMonths(1);

        return DashboardStatsDTO.builder()
                .totalOrders(orderRepository.count())
                .pendingOrders(orderRepository.countByStatus(OrderStatus.PENDING))
                .deliveredOrders(orderRepository.countByStatus(OrderStatus.DELIVERED))
                .totalCustomers(userRepository.count())
                .totalRevenue(orderRepository.sumTotalRevenue())
                .todayRevenue(orderRepository.sumRevenueByDateRange(today, LocalDateTime.now()))
                .weekRevenue(orderRepository.sumRevenueByDateRange(weekAgo, LocalDateTime.now()))
                .monthRevenue(orderRepository.sumRevenueByDateRange(monthAgo, LocalDateTime.now()))
                .build();
    }

    public SalesReportDTO getSalesReport(String period) {
        LocalDateTime startDate;
        switch (period.toUpperCase()) {
            case "TODAY":
                startDate = LocalDate.now().atStartOfDay();
                break;
            case "WEEK":
                startDate = LocalDate.now().minusWeeks(1).atStartOfDay();
                break;
            case "MONTH":
                startDate = LocalDate.now().minusMonths(1).atStartOfDay();
                break;
            default:
                startDate = LocalDate.now().atStartOfDay();
        }

        Double totalSales = orderRepository.sumRevenueByDateRange(startDate, LocalDateTime.now());
        Long totalOrders = orderRepository.countByCreatedAtBetween(startDate, LocalDateTime.now());

        // TODO: Implement popular products calculation from JSONB items field
        Map<String, Integer> popularProducts = new HashMap<>();

        return SalesReportDTO.builder()
                .period(period)
                .totalSales(totalSales != null ? totalSales : 0.0)
                .totalOrders(totalOrders)
                .popularProducts(popularProducts)
                .build();
    }
}