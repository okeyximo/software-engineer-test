package com.example.StudentScoreApi.controller;

import com.example.StudentScoreApi.dto.CreateStudentRequest;
import com.example.StudentScoreApi.dto.StudentReportDTO;
import com.example.StudentScoreApi.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateStudentRequest validRequest;

    @BeforeEach
    void setup() {
        validRequest = new CreateStudentRequest(
            "Jane",
            "Doe",
            Map.of(
                "Math", 80,
                "English", 90,
                "Science", 70,
                "History", 60,
                "Geography", 100
            )
        );
    }

    @Test
    void testAddStudent_ValidRequest_ReturnsCreated() throws Exception {
        StudentReportDTO mockResponse = new StudentReportDTO(
            1L,
            "Jane",
            "Doe",
            validRequest.scores(),
            80.0,
            80.0,
            80
        );

        Mockito.when(studentService.createStudent(any(CreateStudentRequest.class)))
            .thenReturn(new com.example.StudentScoreApi.entities.Student());

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validRequest)))
            .andExpect(status().isCreated());
    }

    @Test
    void testAddStudent_InvalidScore_ReturnsBadRequest() throws Exception {
        Map<String, Integer> invalidScores = Map.of(
            "Math", 120,
            "English", 90,
            "Science", 70,
            "History", 60,
            "Geography", 100
        );

        CreateStudentRequest invalidRequest = new CreateStudentRequest("Jane", "Doe", invalidScores);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testGetStudentReport_ReturnsOk() throws Exception {
        StudentReportDTO mockReport = new StudentReportDTO(
            1L,
            "Jane",
            "Doe",
            validRequest.scores(),
            80.0,
            80.0,
            80
        );

        Page<StudentReportDTO> mockPage = new PageImpl<>(List.of(mockReport), PageRequest.of(0, 10), 1);

        Mockito.when(studentService.report(eq(0), eq(10), isNull()))
            .thenReturn(mockPage);

        mockMvc.perform(get("/api/students/report")
                .param("page", "0")
                .param("size", "10"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].firstName").value("Jane"))
            .andExpect(jsonPath("$.content[0].mean").value(80.0));
    }
}
