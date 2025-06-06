package com.horarios.SGH.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="subjects")
@Data
public class subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subjectId")
        private int subjectId;
    @Column(name="subjectName")
        private String subjectName;
}