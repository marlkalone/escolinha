package com.dev.escolinha.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Class {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private Integer year;
    private Integer semester;
    private String code;

    @OneToMany(mappedBy = "courseClass", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ClassSchedule> schedules = new ArrayList<>();
}

