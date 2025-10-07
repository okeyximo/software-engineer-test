package com.example.StudentScoreApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

@Schema(description = "Student report with statistical data")
public record StudentReportDTO(
    @Schema(description = "Unique identifier of the student", example = "1")
    Long studentId,

    @Schema(description = "Student's first name", example = "John")
    String firstName,

    @Schema(description = "Student's last name", example = "Doe")
    String lastName,

    @Schema(
        description = "Scores in different subjects",
        example = "{\"Math\": 85, \"Science\": 90, \"English\": 78, \"History\": 92, \"Geography\": 88}"
    )
    Map<String, Integer> scores,

    @Schema(description = "Mean (average) score", example = "86.5")
    double mean,

    @Schema(description = "Median score", example = "88.0")
    double median,

    @Schema(description = "Mode (most frequent score)", example = "85")
    Integer mode
) {}