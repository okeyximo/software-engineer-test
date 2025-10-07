// src/main/java/com/test/studentapi/entity/Student.java
package com.example.StudentScoreApi.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores = new ArrayList<>();

    public void addScore(Score s) {
        s.setStudent(this);
        scores.add(s);
    }
}
