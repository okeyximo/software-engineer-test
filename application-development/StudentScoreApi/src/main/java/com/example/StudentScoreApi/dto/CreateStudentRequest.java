package com.example.StudentScoreApi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Map;

@Schema(description = "Request object for creating a new student with scores in 5 subjects")
public record CreateStudentRequest(
    @Schema(
        description = "Student's first name",
        example = "John",
        required = true
    )
    @NotBlank
    String firstName,

    @Schema(
        description = "Student's last name",
        example = "Doe",
        required = true
    )
    @NotBlank
    String lastName,

    @Schema(
        description = "Scores in 5 subjects with subject names as keys and scores as values",
        example = "{\"Math\": 85, \"Science\": 90, \"English\": 78, \"History\": 92, \"Geography\": 88}",
        required = true,
        implementation = Map.class
    )
    @NotNull
    @Size(min = 5, max = 5)
    Map<@NotBlank String, @Min(0) @Max(100) Integer> scores
) {}