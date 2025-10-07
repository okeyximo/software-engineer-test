package com.example.StudentScoreApi.repository;

import com.example.StudentScoreApi.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}