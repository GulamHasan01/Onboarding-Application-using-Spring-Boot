package com.gulamhasan.onboarding_app.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DashboardService {

    public Map<String, Integer> getDashboardSummary() {
        // Dummy data
        return Map.of(
                "teamMembersCount", 12,
                "activeProjects", 5,
                "notifications", 7
        );
    }
}
