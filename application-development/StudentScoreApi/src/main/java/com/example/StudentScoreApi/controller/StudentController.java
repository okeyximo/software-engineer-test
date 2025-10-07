
package com.example.StudentScoreApi.controller;

import com.example.StudentScoreApi.dto.CreateStudentRequest;
import com.example.StudentScoreApi.dto.StudentReportDTO;
import com.example.StudentScoreApi.entities.Student;
import com.example.StudentScoreApi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students and their scores")
public class StudentController {
    private final StudentService svc;

    public StudentController(StudentService svc) { this.svc = svc; }

    @Operation(
        summary = "Create a new student",
        description = "Create student with scores in 5 subjects"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Student created successfully",
            content = @Content(schema = @Schema(implementation = Long.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data"
        )
    })
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequest req) {
        Student created = svc.createStudent(req);
        return new ResponseEntity<>(created.getId(), HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get student report",
        description = "Get paginated report of students with optional name filtering"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Report retrieved successfully",
            content = @Content(schema = @Schema(implementation = StudentReportDTO.class))
        )
    })
    @GetMapping("/report")
    public ResponseEntity<Page<StudentReportDTO>> report(
        @Parameter(description = "Page number (0-based)", example = "0")
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Number of items per page", example = "10")
        @RequestParam(defaultValue = "10") int size,

        @Parameter(description = "Filter by student name (optional)", example = "John")
        @RequestParam(required = false) String name) {
        Page<StudentReportDTO> result = svc.report(page, size, name);
        return ResponseEntity.ok(result);
    }
}