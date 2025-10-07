
package com.example.StudentScoreApi.service;

import com.example.StudentScoreApi.dto.CreateStudentRequest;
import com.example.StudentScoreApi.dto.StudentReportDTO;
import com.example.StudentScoreApi.entities.Score;
import com.example.StudentScoreApi.entities.Student;
import com.example.StudentScoreApi.repository.ScoreRepository;
import com.example.StudentScoreApi.repository.StudentRepository;
import com.example.StudentScoreApi.util.StatisticsUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final ScoreRepository scoreRepo;

    public StudentService(StudentRepository studentRepo, ScoreRepository scoreRepo) {
        this.studentRepo = studentRepo;
        this.scoreRepo = scoreRepo;
    }

    @Transactional
    public Student createStudent(CreateStudentRequest req) {
        Student s = new Student();
        s.setFirstName(req.firstName());
        s.setLastName(req.lastName());
        req.scores().forEach((subject, score) -> {
            Score sc = new Score();
            sc.setSubject(subject);
            sc.setScore(score);
            s.addScore(sc);
        });
        return studentRepo.save(s);
    }

    public Page<StudentReportDTO> report(int page, int size, String nameFilter) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students;
        if (nameFilter == null || nameFilter.isBlank()) {
            students = studentRepo.findAll(pageable);
        } else {
            students = studentRepo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                nameFilter, nameFilter, pageable);
        }

        return students.map(this::toReport);
    }

    private StudentReportDTO toReport(Student s) {
        Map<String, Integer> scores = s.getScores().stream()
                .collect(Collectors.toMap(Score::getSubject, Score::getScore));
        Collection<Integer> vals = scores.values();
        double mean = StatisticsUtil.mean(vals);
        double median = StatisticsUtil.median(vals);
        Integer mode = StatisticsUtil.mode(vals);
        return new StudentReportDTO(s.getId(), s.getFirstName(), s.getLastName(), scores, mean, median, mode);
    }
}
