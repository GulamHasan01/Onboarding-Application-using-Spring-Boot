package com.gulamhasan.onboarding_app.controller;

import com.gulamhasan.onboarding_app.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard-summary")
    public Map<String, Integer> getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }
}
