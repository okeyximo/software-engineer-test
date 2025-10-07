package com.example.StudentScoreApi.dto;

import jakarta.validation.constraints.*;
import java.util.Map;

public record CreateStudentRequest(
    @NotBlank String firstName,
    @NotBlank String lastName,
    @NotNull @Size(min = 5, max = 5) Map<@NotBlank String, @Min(0) @Max(100) Integer> scores
) {}