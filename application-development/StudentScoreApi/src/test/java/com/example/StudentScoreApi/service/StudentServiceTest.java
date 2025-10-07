package com.example.StudentScoreApi.service;

import com.example.StudentScoreApi.dto.CreateStudentRequest;
import com.example.StudentScoreApi.dto.StudentReportDTO;
import com.example.StudentScoreApi.entities.Score;
import com.example.StudentScoreApi.entities.Student;
import com.example.StudentScoreApi.repository.ScoreRepository;
import com.example.StudentScoreApi.repository.StudentRepository;
import com.example.StudentScoreApi.util.StatisticsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepo;

    @Mock
    private ScoreRepository scoreRepo;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent_SavesStudentWithScores() {
        Map<String, Integer> scores = Map.of(
            "Math", 90,
            "English", 85,
            "Science", 70,
            "History", 60,
            "Geography", 75
        );

        CreateStudentRequest request = new CreateStudentRequest("John", "Doe", scores);

        when(studentRepo.save(any(Student.class))).thenAnswer(invocation -> {
            Student s = invocation.getArgument(0);
            s.setId(1L);
            return s;
        });

        Student student = studentService.createStudent(request);
        assertNotNull(student);
        assertEquals("John", student.getFirstName());
        assertEquals(5, student.getScores().size());
    }

    @Test
    void testReport_ConvertsStudentToDTO() {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Jane");
        student.setLastName("Doe");

        // Fixed Score creation: use full constructor (id, student, subject, score)
        student.addScore(new Score(null, student, "Math", 90));
        student.addScore(new Score(null, student, "English", 80));
        student.addScore(new Score(null, student, "Science", 70));
        student.addScore(new Score(null, student, "History", 60));
        student.addScore(new Score(null, student, "Geography", 100));

        Page<Student> page = new PageImpl<>(List.of(student), PageRequest.of(0, 10), 1);
        when(studentRepo.findAll(any(Pageable.class))).thenReturn(page);

        Page<StudentReportDTO> result = studentService.report(0, 10, null);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());

        StudentReportDTO dto = result.getContent().get(0);
        assertEquals("Jane", dto.firstName());
        assertEquals(5, dto.scores().size());
        assertTrue(dto.mean() > 0);
    }

    @Test
    void testStatisticsUtil_MeanMedianMode() {
        var values = List.of(10, 20, 20, 30, 40);
        assertEquals(24.0, StatisticsUtil.mean(values));
        assertEquals(20.0, StatisticsUtil.median(values));
        assertEquals(20, StatisticsUtil.mode(values));
    }
}
