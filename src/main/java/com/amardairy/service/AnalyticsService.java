package com.amardairy.service;


import com.amardairy.dto.DashboardStatsDTO;
import com.amardairy.dto.SalesReportDTO;

public interface AnalyticsService {
    public DashboardStatsDTO getDashboardStats();
    public SalesReportDTO getSalesReport(String period);
}

