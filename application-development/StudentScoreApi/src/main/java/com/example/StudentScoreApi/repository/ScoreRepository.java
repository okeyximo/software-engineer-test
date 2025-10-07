package com.example.StudentScoreApi.repository;

import com.example.StudentScoreApi.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByStudentId(Long studentId);
}