package com.amardairy.controller;

import com.amardairy.dto.DashboardStatsDTO;
import com.amardairy.dto.SalesReportDTO;
import com.amardairy.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public DashboardStatsDTO getDashboardStats() {
        return analyticsService.getDashboardStats();
    }

    @GetMapping("/sales")
    public SalesReportDTO getSalesReport(
            @RequestParam(required = false, defaultValue = "TODAY") String period) {
        return analyticsService.getSalesReport(period);
    }
}