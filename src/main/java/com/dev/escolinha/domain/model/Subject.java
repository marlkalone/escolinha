package com.dev.escolinha.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subject")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_subject_id")
    private Subject parent;
}

