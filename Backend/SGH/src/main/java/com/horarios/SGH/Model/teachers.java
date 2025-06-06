package com.horarios.SGH.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="teachers")
@Data
public class teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="teacherId",nullable = false)
        private int teacherId;
    @Column(name="teacherName",nullable = false)
        private String teacherName;
}
