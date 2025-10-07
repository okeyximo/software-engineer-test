package com.example.StudentScoreApi.dto;

import java.util.Map;

public record StudentReportDTO(
    Long studentId,
    String firstName,
    String lastName,
    Map<String, Integer> scores,
    double mean,
    double median,
    Integer mode
) {}
