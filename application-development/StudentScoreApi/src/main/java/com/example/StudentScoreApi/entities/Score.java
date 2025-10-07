
package com.example.StudentScoreApi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scores",
       uniqueConstraints = @UniqueConstraint(columnNames = {"student_id","subject"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private Integer score;

}
