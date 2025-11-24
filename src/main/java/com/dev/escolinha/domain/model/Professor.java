package com.dev.escolinha.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "professor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Professor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    private Title title;

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @Builder.Default
    private List<Class> classes = new ArrayList<>();
}

