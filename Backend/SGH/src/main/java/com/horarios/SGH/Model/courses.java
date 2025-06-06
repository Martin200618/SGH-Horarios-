package com.horarios.SGH.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="courses")
@Data
public class courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseId")
        private int courseId;
    @Column(name="courseName")
        private String courseName;
}