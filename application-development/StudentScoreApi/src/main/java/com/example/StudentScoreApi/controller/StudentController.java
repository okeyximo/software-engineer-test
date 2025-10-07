
package com.example.StudentScoreApi.controller;

import com.example.StudentScoreApi.dto.CreateStudentRequest;
import com.example.StudentScoreApi.dto.StudentReportDTO;
import com.example.StudentScoreApi.entities.Student;
import com.example.StudentScoreApi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService svc;

    public StudentController(StudentService svc) { this.svc = svc; }

    /**
     * Create student with scores in 5 subjects.
     */
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody CreateStudentRequest req) {
        Student created = svc.createStudent(req);
        return ResponseEntity.ok(created.getId());
    }

    /**
     * Report of students (paginated + optional name filter)
     * GET /api/students/report?page=0&size=10&name=John
     */
    @GetMapping("/report")
    public ResponseEntity<Page<StudentReportDTO>> report(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name) {
//        Page<StudentReportDTO> result = svc.report(page, size, name);
        Page<StudentReportDTO> result = svc.report(page, size, name);
        return ResponseEntity.ok(result);
    }
}
